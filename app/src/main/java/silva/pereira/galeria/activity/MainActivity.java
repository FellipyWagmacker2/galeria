package silva.pereira.galeria.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


import silva.pereira.galeria.R;
import silva.pereira.galeria.adapter.MyAdapter;
import silva.pereira.galeria.model.MainActivityViewModel;
import silva.pereira.galeria.model.MyItem;
import silva.pereira.galeria.Util.Util;

public class MainActivity extends AppCompatActivity {

    static int NEW_ITEM_REQUEST =1; // Variável do tipo inteiro
    List<MyItem> itens = new ArrayList<>(); // Declarando uma ArrayList

    MyAdapter myAdapter;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_ITEM_REQUEST) { // Verificando se retorno é igual ao esperado
            if (resultCode == Activity.RESULT_OK) { // Se resultCode der true
                MyItem myItem = new MyItem();  // Criando instância MyItem
                myItem.title = data.getStringExtra("title"); // Guadando o título da instancia MyItem
                myItem.description = data.getStringExtra("description");// Guadando o descrição da instancia MyItem
                Uri selectedPhotoURI = data.getData(); // Guadando a foto da instancia MyItem

                try{
                    Bitmap photo = Util.getBitmap(MainActivity.this, selectedPhotoURI,100,100); // Guardando a imagem dentro de um Bitmap
                    myItem.photo = photo; // Guardando a imagem dentro do MyItem
                } catch (FileNotFoundException e){ // Ele só é executado caso a imagem não seja encontrada
                    e.printStackTrace();
                }

                MainActivityViewModel vm = new ViewModelProvider(this).get(MainActivityViewModel.class);// Obtendo o ViewModel referente a MainActivity
                List<MyItem> itens = vm.getItens(); // Guardando o novo item e guardando dentro da lista

                itens.add(myItem); // Adicionando o itens e uma lista
                myAdapter.notifyItemInserted(itens.size()-1); // Notificando o Adapter que um novo item sera mostrado
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fabAddNewItem = findViewById(R.id.fabAddNewItem); // Obtendo o ouvidor de click

        RecyclerView rvItens = findViewById(R.id.rvItens); // Obtendo o RecycleView

        MainActivityViewModel vm = new ViewModelProvider(this).get(MainActivityViewModel.class); // Obtendo o ViewModel referente a MainActivity
        List<MyItem> itens = vm.getItens(); // Obtendo a lista de itens através do ViewModel
        myAdapter = new MyAdapter(this,itens); // Criando myAdapter
        rvItens.setAdapter(myAdapter); // Setando o myAdapter no RecycleView

        rvItens.setHasFixedSize(true); // setHasFixedSize indica que não há variação de tamanho entre os intens da lista

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this); // Criando um gerenciador de layout do tipo linear
        rvItens.setLayoutManager(layoutManager); // Setando o RecycleView

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvItens.getContext(),DividerItemDecoration.VERTICAL); // Criando decorador para lista que separa os itens
        rvItens.addItemDecoration(dividerItemDecoration); // Setando o RecycleView
        fabAddNewItem.setOnClickListener(new View.OnClickListener() { // Obtendo o evento de click
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, NewItemActivity.class); // Criando uma intent que realizará a navegação entre as páginas
                startActivityForResult(i, NEW_ITEM_REQUEST); // Executando a intent
            }
        });
    }
}