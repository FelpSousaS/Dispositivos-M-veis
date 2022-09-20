package br.quixada.ufc.appcomponents;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.res.Resources.Theme;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Configuracao extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener{
    ToggleButton toggleButton;
    Button longclick;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracao);
        imageView = findViewById(R.id.image);

        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.ac_text_view);
        String[] countries = getResources().getStringArray(R.array.configs_array);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, countries);
        textView.setAdapter(adapter);

        toggleButton = findViewById(R.id.toggle);
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                if(toggleButton.isChecked()){
                   imageView.setImageResource(R.drawable.music);
                }
                else{
                    imageView.setImageResource(R.drawable.music_player);
                }
            }
        });
        longclick = (Button)findViewById(R.id.btn_long_click);

        longclick.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                TextView txt = (TextView) findViewById(R.id.txt);
                txt.setText("Obrigado por utilizar meu app :)");
                return false;
            }
        });

    }

    public void  showPopup(View v){
        PopupMenu popupMenu = new PopupMenu(this,v);
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.setOnMenuItemClickListener(this);
        popupMenu.show();
    }
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                Toast.makeText(this, "Você selecionou Português",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item2:
                Toast.makeText(this, "Você selecionou Inglês",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item3:
                Toast.makeText(this, "Você selecionou Espanhol",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return false;
        }
    }
}