package br.ufc.quixada.scap.DAO;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import br.ufc.quixada.scap.Edit_atividade;
import br.ufc.quixada.scap.FormAddAtividade;
import br.ufc.quixada.scap.Model.Atividades;
import br.ufc.quixada.scap.Model.User;

public class AtividadesDAOFirebase implements SCAPInterface {

    private static AtividadesDAOFirebase atividadesDAOFirebase = null;
    private static FormAddAtividade formAddAtividade;
    private static Edit_atividade edit_atividade;
    private FirebaseFirestore db;
    private static Context context;

    User u;

    ArrayList<Atividades> listaAtividades;



    private AtividadesDAOFirebase(FormAddAtividade formAddAtividade) {
        AtividadesDAOFirebase.formAddAtividade = formAddAtividade;
        listaAtividades = new ArrayList<>();
    }

    private AtividadesDAOFirebase(Context c) {
        AtividadesDAOFirebase.context = c;
    }

    public static SCAPInterface getInstance(Context context) {
        if (atividadesDAOFirebase == null) {
            atividadesDAOFirebase = new AtividadesDAOFirebase(context);
        }

        return atividadesDAOFirebase;
    }

    @Override
    public boolean addAtividade(Atividades a) {


        Map<String, Object> atv = new HashMap<>();

        atv.put("id", a.getId());
        atv.put("idUser", a.getUserId());
        atv.put("Autor", a.getAutor());
        atv.put("Tipo da Atividade", a.getTipo_de_atividade());
        atv.put("Nome da Atividade", a.getNome_da_atividade());
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

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(formAddAtividade, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        return false;
    }



    @Override
    public boolean editAtividade(Atividades a) {
        db.collection("Atividades").document(a.getDocumentID())
                .update("Tipo da Atividade",a.getTipo_de_atividade(),"Nome da Atividade", a.getNome_da_atividade(),
                "Descricao", a.getDescricao_da_atividade(),
                "Objetivo", a.getObjetivo_da_atividade(),
                "Metodologia", a.getMetodologia_da_atividade(),
                "Resultados", a.getResultados_da_atividade(),
                "Avaliacao", a.getAvaliacao_da_atividade()).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(edit_atividade, "Sucess", Toast.LENGTH_SHORT).show();
                edit_atividade.notifyAdapter();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(edit_atividade, "Erro", Toast.LENGTH_SHORT).show();
                edit_atividade.notifyAdapter();
            }
        });

        return false;
    }

    @Override
    public boolean deleteAtividade(int idAtividade) {
        Atividades atv = null;

        for (Atividades a : listaAtividades) {
            if (a.getId().equals(idAtividade)) {
                atv = a;
                break;
            }
        }

        if (atv != null) {

            final Atividades apagar = atv;

            DocumentReference deleteAtividade = db.collection("Atividades").document(atv.getDocumentID());
            deleteAtividade.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(formAddAtividade, "Sucess", Toast.LENGTH_LONG).show();

                            Atividades atividadeApagar = null;
                            for (Atividades atividade : listaAtividades) {

                                if (atividade.getDocumentID().equals(apagar.getDocumentID())) {
                                    atividadeApagar = atividade;
                                    break;
                                }

                            }

                            if (atividadeApagar != null) listaAtividades.remove(atividadeApagar);
                            formAddAtividade.notifyAdapter();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(Exception e) {
                            Toast.makeText(formAddAtividade, "Error", Toast.LENGTH_LONG).show();

                        }
                    });

        }

        return false;
    }

    @Override
    public Atividades getAtividade(int idAtividade) {
        return null;
    }

    @Override
    public ArrayList<Atividades> getListaAtividades() {
        listaAtividades = new ArrayList<Atividades>();

        db.collection("Atividades")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                    public void onComplete(Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String tipoAtividade = document.getString("Tipo da Atividade");
                                String nomeAtividade = document.getString("Nome da Atividade");
                                String descricaoAtividade = document.getString("Descricao");
                                String objetivoAtividade = document.getString("Objetivo");
                                String metodologiaAtividade = document.getString("Metodologia");
                                String resultadosAtividade = document.getString("Resultados");
                                String avaliacaoAtividade = document.getString("Avaliacao");

                                Atividades a = new Atividades(tipoAtividade, nomeAtividade, descricaoAtividade, objetivoAtividade, metodologiaAtividade, resultadosAtividade, avaliacaoAtividade);
                                a.setDocumentID(document.getId());

                                listaAtividades.add(a);

                            }
                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());
                        }

                        formAddAtividade.notifyAdapter();
                    }
                });


        return listaAtividades;
    }

    @Override
    public boolean deleteAll() {
        return false;
    }

    @Override
    public boolean init() {
        if (db == null) db = FirebaseFirestore.getInstance();
        return true;
    }

    @Override
    public boolean close() {
        return false;
    }

    @Override
    public boolean isStarted() {
        return false;
    }

}
