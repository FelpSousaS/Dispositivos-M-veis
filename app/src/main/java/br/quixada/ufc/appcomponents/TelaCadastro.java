package br.quixada.ufc.appcomponents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.textview.MaterialTextView;

public class TelaCadastro extends AppCompatActivity {

    private MaterialButton materialButton;
    private MaterialTextView data_cad;
    private AppCompatButton appCompatButton;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);
        getSupportActionBar().hide();
        materialButton = findViewById(R.id.selectData_cad);
        data_cad = findViewById(R.id.data_cad);
        mediaPlayer = MediaPlayer.create(this, R.raw.click);

        InitComponents();

        appCompatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
                Intent intent = new Intent(TelaCadastro.this, TelaLogin.class);
                startActivity(intent);
            }
        });

        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });
    }

    private void InitComponents(){
        appCompatButton = findViewById(R.id.bt_cad);
    }

    private void showDatePickerDialog() {
        MaterialDatePicker<Long> materialDatePicker = MaterialDatePicker.Builder
                .datePicker().setTitleText("Selecione a data").build();

        materialDatePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
            @Override
            public void onPositiveButtonClick(Long selection) {
                data_cad.setText("" + materialDatePicker.getHeaderText());
            }
        });

        materialDatePicker.show(getSupportFragmentManager(), "TAG");
    }

}