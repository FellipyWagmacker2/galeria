package pereira.silva.lista.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import silva.pereira.galeria.R;

public class MainActivity extends AppCompatActivity {

    static int NEW_ITEM_REQUEST =1; // Variável do tipo inteiro

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fabAddNewItem = findViewById(R.id.fabAddNewItem); // Obtendo o ouvidor de click
        fabAddNewItem.setOnClickListener(new View.OnClickListener() { // Obtendo o evento de click
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, NewItemActivity.class); // Criando uma intent que realizará a navegação entre as páginas
                startActivityForResult(i, NEW_ITEM_REQUEST); // Executando a intent
            }
        });
    }
}