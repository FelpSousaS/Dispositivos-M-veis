package br.ufc.quixada.scap.auth;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.firebase.auth.FirebaseAuth;

import br.ufc.quixada.scap.DAO.UserDAO;
import br.ufc.quixada.scap.MainActivity;
import br.ufc.quixada.scap.R;

public class FormLogin extends AppCompatActivity {


    private EditText edit_email;
    private EditText edit_senha;
    private TextView txt_senha_nova;
    private ProgressBar progressBar;
    String email,senha;
    private TextView text_tela_de_cadastro;
    private AppCompatButton appCompatButton;
    UserDAO userDAO;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);


        IniciarComponentes();

        auth = FirebaseAuth.getInstance();
        userDAO = UserDAO.getInstance();

        txt_senha_nova.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FormLogin.this, RecuperarSenha.class);
                startActivity(intent);
            }
        });


        text_tela_de_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FormLogin.this, FormCadastro.class);
                startActivity(intent);
            }
        });



        appCompatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = edit_email.getText().toString();
                senha = edit_senha.getText().toString();

                if(email.isEmpty() || senha.isEmpty()){
                    Toast toast = Toast.makeText(getApplicationContext(), "Preencha todos os campos!", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    auth(view);
                }

            }
        });

        ImageView imageViewShowHidePwd = findViewById(R.id.olho_senha);
        imageViewShowHidePwd.setImageResource(R.drawable.ic_baseline_visibility_off_24);
        imageViewShowHidePwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit_senha.getTransformationMethod().equals(HideReturnsTransformationMethod.getInstance())) {

                    //if passaword is visible then hide it
                    edit_senha.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    //change icon
                    imageViewShowHidePwd.setImageResource(R.drawable.ic_baseline_visibility_off_24);
                } else {
                    edit_senha.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    imageViewShowHidePwd.setImageResource(R.drawable.ic_baseline_remove_red_eye_24);
                }
            }

        });
    }
    private void IniciarComponentes(){
        edit_email = findViewById(R.id.edit_email);
        edit_senha = findViewById(R.id.edit_senha);
        text_tela_de_cadastro= findViewById(R.id.text_tela_cadastro);
        appCompatButton = findViewById(R.id.button);
        progressBar = findViewById(R.id.progressBar);
        txt_senha_nova = findViewById(R.id.text_tela_senha);

    }
    public void auth(View view) {
        String email = edit_email.getText().toString();
        String password = edit_senha.getText().toString();

        progressBar.setVisibility(View.VISIBLE);

        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, (task) -> {
                    if(task.isSuccessful()) {
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "E-mail ou senha incorretos",
                                Toast.LENGTH_SHORT).show();
                    }
                    progressBar.setVisibility(View.GONE);
                });
    }

}