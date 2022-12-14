package br.ufc.quixada.scap;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import br.ufc.quixada.scap.Adapters.ListarAtividadesAdapter;
import br.ufc.quixada.scap.DAO.AtividadesDAOFirebase;
import br.ufc.quixada.scap.Model.Atividades;

public class FormAddAtividade extends AppCompatActivity {

    static ArrayList<Atividades> atividadesList;

    RadioGroup radioGroup;
    RadioButton radioButton;

    EditText editNomeAtv, editDescricaoAtv, editObjetivoAtv, editMetodologiaAtv, editResultadosAtv, editAvaliacaoAtv;
    AppCompatButton btnAdd;
    BottomNavigationView bottomNavigationView;
    ListarAtividadesAdapter adapter;
    AtividadesDAOFirebase atividadesDAO;
    String autor;

    ProgressDialog pd;

    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_add_atividade);


        dataInitialize();

        atividadesDAO = (AtividadesDAOFirebase) AtividadesDAOFirebase.getInstance(this);
        atividadesDAO.init();
        adapter = new ListarAtividadesAdapter(this, atividadesList);

        pd = new ProgressDialog(this);

        db = FirebaseFirestore.getInstance();


        bottomNavigationView.setSelectedItemId(R.id.bottom_menu_adicionar);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bottom_menu_atividades:

                        startActivity(new Intent(getApplicationContext(), ListarAtividade.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.bottom_menu_inicio:

                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.bottom_menu_adicionar:

                        return true;

                    case R.id.bottom_menu_pet:

                        startActivity(new Intent(getApplicationContext(), Pet.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addAtividade(v);

                Intent intent = new Intent(FormAddAtividade.this, ListarAtividade.class);
                startActivity(intent);

            }
        });

    }

    public void dataInitialize() {
        bottomNavigationView = findViewById(R.id.bottom_menu);
        atividadesList = new ArrayList<Atividades>();
        editNomeAtv = findViewById(R.id.edit_nome_atividade);
        editDescricaoAtv = findViewById(R.id.edit_descricao);
        editObjetivoAtv = findViewById(R.id.edit_objetivo);
        editMetodologiaAtv = findViewById(R.id.edit_metodologia);
        editResultadosAtv = findViewById(R.id.edit_resultados);
        editAvaliacaoAtv = findViewById(R.id.edit_avaliacao);
        btnAdd = findViewById(R.id.bt_add);
        radioGroup = (RadioGroup) findViewById(R.id.radiogroup_tipo_da_atividade);
    }

    public void notifyAdapter() {
        adapter.notifyDataSetChanged();
    }


    public void addAtividade(View view) {

        // recebendo o botão selecionado
        int selectedId = radioGroup.getCheckedRadioButtonId();
        // buscando e retornando o id
        radioButton = (RadioButton) findViewById(selectedId);

        //id do usuario logado
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();


        // pegando o nome do usuario logado
        DocumentReference documentReference = db.collection("Usuarios").document(uid);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value != null) {



                    String ids = UUID.randomUUID().toString();

                    // setando o nome do usuario
                    autor = value.getString("nome");
                    String id = ids;
                    String userId = uid;
                    String nome_autor_atv = autor;
                    String tipoAtv = radioButton.getText().toString();
                    String nomeAtv = editNomeAtv.getText().toString();
                    String descricaoAtv = editDescricaoAtv.getText().toString();
                    String objetivoAtv = editObjetivoAtv.getText().toString();
                    String metodologiaAtv = editMetodologiaAtv.getText().toString();
                    String resultadosAtv = editResultadosAtv.getText().toString();
                    String avaliacaoAtv = editAvaliacaoAtv.getText().toString();



                    Atividades a = new Atividades(id,userId, nome_autor_atv, tipoAtv, nomeAtv, descricaoAtv, objetivoAtv, metodologiaAtv, resultadosAtv, avaliacaoAtv);

                    Map<String, Object> atv = new HashMap<>();

                    atv.put("id", a.getId());
                    atv.put("idUser", a.getUserId());
                    atv.put("Autor", a.getAutor());
                    atv.put("Tipo da Atividade", a.getTipo_de_atividade());
                    atv.put("Nome da Atividade", a.getNome_da_atividade());
                    atv.put("search", a.getNome_da_atividade().toLowerCase());
                    atv.put("Descricao", a.getDescricao_da_atividade());
                    atv.put("Objetivo", a.getObjetivo_da_atividade());
                    atv.put("Metodologia", a.getMetodologia_da_atividade());
                    atv.put("Resultados", a.getResultados_da_atividade());
                    atv.put("Avaliacao", a.getAvaliacao_da_atividade());



                    //adicionar novo documento com ID gerado
                    db.collection("Atividades").document(a.getId()).set(atv)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    Toast.makeText(FormAddAtividade.this, "Atividade cadastrada com sucesso!", Toast.LENGTH_SHORT).show();
                                    FormAddAtividade.this.notifyAdapter();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(FormAddAtividade.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });

                    //atividadesDAO.addAtividade(a);
                    adapter.notifyDataSetChanged();
                }


            }
        });


    }

}
