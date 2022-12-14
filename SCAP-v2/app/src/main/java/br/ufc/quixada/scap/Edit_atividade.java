package br.ufc.quixada.scap;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import br.ufc.quixada.scap.Adapters.ListarAtividadesAdapter;
import br.ufc.quixada.scap.DAO.AtividadesDAOFirebase;
import br.ufc.quixada.scap.Model.Atividades;

public class Edit_atividade extends AppCompatActivity {
    EditText editNomeAtv, editDescricaoAtv, editObjetivoAtv, editMetodologiaAtv, editResultadosAtv, editAvaliacaoAtv;
    Button bt_upd;
    RadioGroup radiogroup_tipo_da_atividade;
    RadioButton radioButton;
    String Id,NomeAtv, DescricaoAtv, ObjetivoAtv, MetodologiaAtv, ResultadosAtv, AvaliacaoAtv,TipoATV;
    ListarAtividade listarAtividade;
    Toolbar toolbar;
    FirebaseFirestore db;
    ListarAtividadesAdapter adapter;
    AtividadesDAOFirebase dao;
    ImageView voltar;

    ProgressDialog pd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_atividade);

        db = FirebaseFirestore.getInstance();
        dataInitialize();
        adapter = new ListarAtividadesAdapter(this);
        Bundle bundle = getIntent().getExtras();
        //upd//get dados
        Id = bundle.getString("id");
        TipoATV = bundle.getString("tipo_da_atv");
        NomeAtv = bundle.getString("nome_da_atv");
        DescricaoAtv = bundle.getString("descricao");
        ObjetivoAtv = bundle.getString("objetivo");
        MetodologiaAtv = bundle.getString("metodologia");
        ResultadosAtv = bundle.getString("resultados");
        AvaliacaoAtv = bundle.getString("avaliacao");

        //set dados
        if(TipoATV.equals("Ensino") ){
            radiogroup_tipo_da_atividade.check(R.id.radiobutton_ensino);
        }else if(TipoATV.equals("Pesquisa") ){
            radiogroup_tipo_da_atividade.check(R.id.radiobutton_pesquisa);
        }else{
            radiogroup_tipo_da_atividade.check(R.id.radiobutton_extensao);
        }
        editNomeAtv.setText(NomeAtv);
        editDescricaoAtv.setText(DescricaoAtv);
        editObjetivoAtv.setText(ObjetivoAtv);
        editMetodologiaAtv.setText(MetodologiaAtv);
        editResultadosAtv.setText(ResultadosAtv);
        editAvaliacaoAtv.setText(AvaliacaoAtv);

        bt_upd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                edit_Atividade(v);
                Intent intent = new Intent(Edit_atividade.this, ListarAtividade.class);
                startActivity(intent);

            }
        });

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Edit_atividade.this, ListarAtividade.class);
            }
        });

        toolbar = (Toolbar) findViewById(R.id.toolbar_edit_atv);
        setSupportActionBar(toolbar);




    }

    public void dataInitialize() {

        editNomeAtv = findViewById(R.id.edit_nome_atividade);
        editDescricaoAtv = findViewById(R.id.edit_descricao);
        editObjetivoAtv = findViewById(R.id.edit_objetivo);
        editMetodologiaAtv = findViewById(R.id.edit_metodologia);
        editResultadosAtv = findViewById(R.id.edit_resultados);
        editAvaliacaoAtv = findViewById(R.id.edit_avaliacao);
        bt_upd = findViewById(R.id.bt_upd);
        voltar = findViewById(R.id.ic_voltar_edit);
        radiogroup_tipo_da_atividade = (RadioGroup) findViewById(R.id.radiogroup_tipo_da_atividade);
    }

    public void notifyAdapter() {
        adapter.notifyDataSetChanged();
    }

    public void edit_Atividade(View view) {

        // recebendo o botão selecionado
        int selectedId = radiogroup_tipo_da_atividade.getCheckedRadioButtonId();
        // buscando e retornando o id
        radioButton = (RadioButton) findViewById(selectedId);

        String tipoAtv = radioButton.getText().toString();
        String nomeAtv = editNomeAtv.getText().toString();
        String descricaoAtv = editDescricaoAtv.getText().toString();
        String objetivoAtv = editObjetivoAtv.getText().toString();
        String metodologiaAtv = editMetodologiaAtv.getText().toString();
        String resultadosAtv = editResultadosAtv.getText().toString();
        String avaliacaoAtv = editAvaliacaoAtv.getText().toString();

        Atividades a = new Atividades(tipoAtv,nomeAtv,descricaoAtv,objetivoAtv,metodologiaAtv,resultadosAtv,avaliacaoAtv);
        db.collection("Atividades").document(Id)
                .update("Tipo da Atividade",tipoAtv,"Nome da Atividade", nomeAtv,
                        "search", a.getNome_da_atividade().toLowerCase(),
                        "Descricao",descricaoAtv,
                        "Objetivo", objetivoAtv,
                        "Metodologia", metodologiaAtv,
                        "Resultados", resultadosAtv,
                        "Avaliacao",avaliacaoAtv).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(Edit_atividade.this, "Sucess", Toast.LENGTH_SHORT).show();
                        Edit_atividade.this.notifyAdapter();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Edit_atividade.this, "Erro", Toast.LENGTH_SHORT).show();
                        Edit_atividade.this.notifyAdapter();
                    }
                });
       // dao.editAtividade(a);
        adapter.notifyDataSetChanged();


    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_simples,menu);
        return true;
    }



}


