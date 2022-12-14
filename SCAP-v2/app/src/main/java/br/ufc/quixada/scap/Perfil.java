package br.ufc.quixada.scap;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

import br.ufc.quixada.scap.auth.FormLogin;
import de.hdodenhof.circleimageview.CircleImageView;

public class Perfil extends AppCompatActivity {

    boolean cont = false;
    private EditText nome;
    private EditText email;
    private EditText senha;
    private ImageView sair;
    Perfil perfil;
    private CircleImageView btn_add_foto ;
    private AppCompatButton salvar;
    CircleImageView imageView;

    ImageView voltar;


    FirebaseFirestore db;
    Uri filePath;
    FirebaseAuth auth;
    StorageReference storageReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();


        dataInicialize();


        onStart();


        sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(Perfil.this, FormLogin.class);
                startActivity(intent);
                finish();
            }
        });
        
        btn_add_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(Perfil.this);
                String [] opt = {"Câmera", "Galeria"};

                builder.setItems(opt, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(which == 0){
                            tirarFoto();

                        }
                        if(which == 1){
                            pegarFoto();
                            cont = true;

                        }
                    }
                }).create().show();

            }
        });

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cont == false){
                    updUser();
                }
                if(cont == true){
                    updUserFoto();
                    cont = false;
                }

            }
        });
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Perfil.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
    private void dataInicialize(){
        nome = findViewById(R.id.edit_Nome);
        email = findViewById(R.id.edit_email);
        sair = findViewById(R.id.ic_sair);
        imageView = findViewById(R.id.image_user);
        btn_add_foto = findViewById(R.id.btn_add_foto);
        salvar = findViewById(R.id.bt_salvar);
        voltar = findViewById(R.id.ic_voltar_perfil);
    }

    private void tirarFoto() {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},0);
        }
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        startActivityForResult(intent, 1);

    }
    private void pegarFoto() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 0);
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 ) {
            filePath = data.getData();

            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageDrawable(new BitmapDrawable(bitmap));
                btn_add_foto.setAlpha(0);
            } catch (IOException e) {

            }
        }
        if(requestCode == 1 &&  resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imagem = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imagem);
        }

    }

    protected void onStart(){
        super.onStart();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();


        DocumentReference documentReference = db.collection("Usuarios").document(uid);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                if(documentSnapshot != null){
                    nome.setText(documentSnapshot.getString("nome"));
                    email.setText(documentSnapshot.getString("email"));

                }else{
                    Toast.makeText(Perfil.this, "Error", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void  updUserFoto () {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();


        String nome_new = nome.getText().toString();
        String email_new = email.getText().toString();
        String foto = null;

        final StorageReference referencia = FirebaseStorage.getInstance().getReference("/images/" + uid);
        referencia.putFile(filePath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                referencia.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        db.collection("Usuarios").document(uid).update("nome", nome_new,
                                "email", email_new, "url_foto", uri).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(Perfil.this, "Update", Toast.LENGTH_SHORT).show();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Perfil.this, "Error", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }

                });

            }
        });
    }

    public void  updUser (){

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        String nome_new = nome.getText().toString();
        String email_new = email.getText().toString();

        db.collection("Usuarios").document(uid).update("nome", nome_new,
                "email",email_new).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(Perfil.this,"Update",Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Perfil.this,"Error",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
