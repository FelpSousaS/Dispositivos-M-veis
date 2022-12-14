package br.ufc.quixada.scap.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

import br.ufc.quixada.scap.R;

public class RecuperarSenha extends AppCompatActivity {

    EditText email;
    AppCompatButton btn_confirmar;
    FirebaseAuth auth;
    String email_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_senha);

        IniciarComponentes();
        auth = FirebaseAuth.getInstance();

        btn_confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recuperarSenha();
            }
        });


    }

    private void recuperarSenha() {
        email_send = email.getText().toString();

        if(!email_send.isEmpty()){
            auth.sendPasswordResetEmail(email_send).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Toast.makeText(RecuperarSenha.this, "Email de redefinição de senha enviado para" +  " " +  email_send +". Por favor, verifique seu e-mail." , Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(RecuperarSenha.this, FormLogin.class);
                    startActivity(intent);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(RecuperarSenha.this, "Falha ao enviar e-mail de redefinição de senha!", Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            Toast.makeText(RecuperarSenha.this, "Informe o seu e-mail!", Toast.LENGTH_SHORT).show();
        }

    }

    private void IniciarComponentes() {
        email = findViewById(R.id.edit_email_recuperar);
        btn_confirmar = findViewById(R.id.button_enviar);
    }


}