package br.ufc.quixada.scap;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.firestore.FirebaseFirestore;

import br.ufc.quixada.scap.Adapters.ListarAtividadesAdapter;


public class Atividade extends AppCompatActivity {

    TextView editNomeAtv, editDescricaoAtv, editObjetivoAtv, editMetodologiaAtv, editResultadosAtv, editAvaliacaoAtv , tipoAtv;

    String Id,NomeAtv, DescricaoAtv, ObjetivoAtv, MetodologiaAtv, ResultadosAtv, AvaliacaoAtv,TipoATV;

    ImageView voltar;

    FirebaseFirestore db;
    ListarAtividadesAdapter adapter;
    Toolbar toolbar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atividade);

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
        voltar = findViewById(R.id.ic_voltar);

        //set dados
        tipoAtv.setText(TipoATV);
        editNomeAtv.setText(NomeAtv);
        editDescricaoAtv.setText(DescricaoAtv);
        editObjetivoAtv.setText(ObjetivoAtv);
        editMetodologiaAtv.setText(MetodologiaAtv);
        editResultadosAtv.setText(ResultadosAtv);
        editAvaliacaoAtv.setText(AvaliacaoAtv);

        toolbar = (Toolbar) findViewById(R.id.toolbar_atv_listar);
        setSupportActionBar(toolbar);

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Atividade.this,ListarAtividade.class);
                startActivity(intent);
            }
        });
        }



    public void dataInitialize() {
        tipoAtv = findViewById(R.id.txt_tipo_da_atividade);
        editNomeAtv = findViewById(R.id.txt_nome_atividade);
        editDescricaoAtv = findViewById(R.id.txt_descricao);
        editObjetivoAtv = findViewById(R.id.txt_objetivo);
        editMetodologiaAtv = findViewById(R.id.txt_metodologia);
        editResultadosAtv = findViewById(R.id.txt_resultados);
        editAvaliacaoAtv = findViewById(R.id.txt_avaliacao);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_simples,menu);
        return true;
    }




}


