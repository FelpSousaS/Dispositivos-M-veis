package br.ufc.quixada.scap;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.imaginativeworld.whynotimagecarousel.ImageCarousel;
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;
import java.util.List;

import br.ufc.quixada.scap.Adapters.MainAdapter;
import br.ufc.quixada.scap.DAO.SCAPInterface;
import br.ufc.quixada.scap.Model.Atividades;

public class MainActivity extends AppCompatActivity{

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    FirebaseFirestore db;
    FirebaseAuth auth;
    MainAdapter atividadesAdapter;

    List<Atividades> lista = new ArrayList<>();
    List<Atividades> lista_reserva = new ArrayList<>();
    String autor;
    ProgressDialog pd;
    BottomNavigationView bottomNavigationView;
    SearchView searchView;

    private Toolbar toolbar;
    SCAPInterface scapInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = FirebaseFirestore.getInstance();

        pd = new ProgressDialog(this);

        recyclerView = findViewById(R.id.recycle_view_listar);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        searchView = findViewById(R.id.action_search);


        showData();

        scapInterface = SCAPInterface.getInstance(MainActivity.this);

        bottomNavigationView = findViewById(R.id.bottom_menu);
        bottomNavigationView.setSelectedItemId(R.id.bottom_menu_inicio );
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId())
                {
                    case R.id.bottom_menu_atividades:
                        startActivity(new Intent(getApplicationContext(), ListarAtividade.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.bottom_menu_inicio:
                        return true;
                    case R.id.bottom_menu_adicionar:
                        startActivity(new Intent(getApplicationContext(),FormAddAtividade.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.bottom_menu_pet:
                        startActivity(new Intent(getApplicationContext(),Pet.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


            ImageCarousel carousel = (ImageCarousel) findViewById(R.id.carousel);

            //create list to store carousel image
            ArrayList<CarouselItem> clist = new ArrayList<>();
            clist.add(new CarouselItem(
                    R.drawable.equipe,
                    "Equipe PET-SI"
            ));

            clist.add(new CarouselItem(
                    R.drawable.campus,
                    "Campus"
            ));

            carousel.setData(clist);


    }




    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    public boolean onOptionsItemSelected( MenuItem item){
        switch (item.getItemId()){
            case R.id.profile2:
                startActivity(new Intent(this,Perfil.class));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + item.getItemId());
        }
        return true;
    }

    private void showData() {

        db.collection("Atividades").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String uid = user.getUid();
                for (DocumentSnapshot documentSnapshot : task.getResult()) {
                        Atividades atividades = new Atividades(documentSnapshot.getString("id"),documentSnapshot.getString("Nome da Atividade"),documentSnapshot.getString("Autor"),
                                documentSnapshot.getString("Tipo da Atividade"), documentSnapshot.getString("Descricao"),documentSnapshot.getString("Objetivo"),documentSnapshot.getString("Metodologia")
                                ,documentSnapshot.getString("Resultados"),documentSnapshot.getString("Avaliacao"));
                        lista.add(atividades);


                }

                atividadesAdapter = new MainAdapter(MainActivity.this, lista);
                recyclerView.setAdapter(atividadesAdapter);
            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }




}