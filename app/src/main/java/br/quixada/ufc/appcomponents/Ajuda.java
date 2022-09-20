package br.quixada.ufc.appcomponents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Ajuda extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajuda);

        ListView lista = findViewById(R.id.listview);


        String[] links = new String[]{
                "https://www.youtube.com/",
                "https://www.radio-ao-vivo.com/?gclid=CjwKCAjwpqCZBhAbEiwAa7pXedEM99Fj6RzTiLxvoUHSEKWjLZ2YtV6dZurFaKLfJbIPAOPCg0jYIhoC4tIQAvD_BwE",
                "https://www.tenhomaisdiscosqueamigos.com/"};


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,links);
        lista.setAdapter(adapter);


        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String texto = "" ;
                TextView tv = (TextView)  view;
                texto = tv.getText().toString();
                Toast.makeText(getApplicationContext(), "Clicou no item: " + texto,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(texto));
                startActivity(intent);

            }
        });
    }
}