package br.ufc.quixada.scap.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import br.ufc.quixada.scap.DAO.UserDAO;
import br.ufc.quixada.scap.Model.User;
import br.ufc.quixada.scap.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class FormCadastro extends AppCompatActivity {

    private FirebaseAuth auth;
    CircleImageView imageView;
    EditText edtNome;
    EditText edtEmail;
    EditText edtSenha;
    String url_foto = null;
    private ProgressBar progressBar;

    UserDAO userDAO;

    AppCompatButton appCompatButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cadastro);


        auth = FirebaseAuth.getInstance();
        userDAO = UserDAO.getInstance();

        IniciarComponentes();


        appCompatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nome = edtNome.getText().toString();
                String email = edtEmail.getText().toString();
                String senha = edtSenha.getText().toString();

                progressBar.setVisibility(View.VISIBLE);



                if(nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                    Toast.makeText(
                            FormCadastro.this,
                            "Preencha todos os campos!",
                            Toast.LENGTH_SHORT).show();
                }else{
                    auth.createUserWithEmailAndPassword(email,senha)
                            .addOnCompleteListener(task -> {
                                if(task.isSuccessful()) {
                                    String uid = auth.getUid();
                                    User users = new User(url_foto,uid,nome,email);
                                    FirebaseFirestore.getInstance().collection("Usuarios").document(uid).set(users);
                                    Toast.makeText(getApplicationContext(),
                                            "Cadastro concluído!",
                                            Toast.LENGTH_LONG).show();

                                    startActivity(new Intent(FormCadastro.this, FormLogin.class));
                                    finish();

                                }else {
                                    Toast.makeText(getApplicationContext(),
                                            "Falha ao registrar!",
                                            Toast.LENGTH_LONG).show();
                                }
                                progressBar.setVisibility(View.GONE);
                            });

                }
            }

        });


    }

    private void IniciarComponentes() {

        edtNome = findViewById(R.id.edit_Nome);
        edtEmail = findViewById(R.id.edit_email);
        edtSenha = findViewById(R.id.edit_senha);
        appCompatButton = findViewById(R.id.bt_cadastrar);
        progressBar = findViewById(R.id.progressBarCad);
    }

}


