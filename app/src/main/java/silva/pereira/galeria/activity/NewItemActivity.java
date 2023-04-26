package silva.pereira.galeria.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import silva.pereira.galeria.R;
import silva.pereira.galeria.model.NewItemActivityViewModel;

public class NewItemActivity extends AppCompatActivity {
    static int PHOTO_PICKER_REQUEST = 1; // Variável do tipo inteiro

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        NewItemActivityViewModel vm = new ViewModelProvider(this).get(NewItemActivityViewModel.class); // Obtendo o ViewModel referente a NewItemActivity

        Uri photoSelected = vm.getSelectPhotoLocation(); // Obtendo o endereço da URI que esta dentro do ViewModel
        if(photoSelected != null){ // Se o endereço for diferente de nulo
            ImageView imvPhotoPreview = findViewById(R.id.imvPhotoPreview); // Obtendo a imagem
            imvPhotoPreview.setImageURI(photoSelected); // Setando a Imagem no ImageView
        }

        ImageButton imgCI = findViewById(R.id.imbCI); // Obtendo o ImageButton
        imgCI.setOnClickListener(new View.OnClickListener() { // Definindo o ouvidor de clicks
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT); // Abrindo galeria de fotos usando uma intent com ACTION do tipo ACTION_OPEN_DOCUMENT
                photoPickerIntent.setType("image/*"); // Setando que estamos interessados somente em documentos com mimetype "image/*"
                startActivityForResult(photoPickerIntent, PHOTO_PICKER_REQUEST); // Executando a intent usando o metodo startActivityForResult

            }

        });

        Button btnAddItem = findViewById(R.id.btnAddItem); // Obtendo o Button
        btnAddItem.setOnClickListener(new View.OnClickListener() { // Setando ouvidor de clicks
            @Override
            public void onClick(View view) {

                Uri photoSelected = vm.getSelectPhotoLocation(); // Obtendo o endereço da URI que esta dentro do ViewModel

                if (photoSelected == null){ // Verificando se os campos foram preenchidos
                    Toast.makeText(NewItemActivity.this, "È necessário selecionar uma imagem!", Toast.LENGTH_LONG).show(); // Mensagem de erro
                    return;
                }

                EditText etTitle = findViewById(R.id.etTitle); // Verificando se os campos foram preenchidos
                String title = etTitle.getText().toString();
                if (title.isEmpty()){ // Se o campo de título estiver vazio,irá exibir uma mensagem de erro
                    Toast.makeText(NewItemActivity.this, "È necessário inserir um título", Toast.LENGTH_LONG).show(); // Mensagem de erro
                    return;
                }
                EditText etDesc = findViewById(R.id.etDesc); // Verificando se os campos foram preenchidos
                String description = etDesc.getText().toString();
                if (description.isEmpty()) { // Se o campo de descrição estiver vazio,irá exibir uma mensagem de erro
                    Toast.makeText(NewItemActivity.this, "É necessário inserir uma descrição", Toast.LENGTH_LONG).show(); //Mensagem de erro
                    return;
                    }

                Intent i = new Intent(); // Criando intent
                i.setData(photoSelected); // Setando a Uri da imagem
                i.putExtra("title", title); // Setando o título
                i.putExtra("description", description); // Setando a descrição
                setResult(Activity.RESULT_OK, i); // O setResult serve para indicar o resultado da Activity
                finish(); // Finalizando Activity
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) { // Método onActivityResult que recebe 3 parametros resquestCode: inteiro que indica a qual chamada de startActivityForResult, resultCode: código que indica se a Activity de destino retornou com sucesso ou
        // não, data:  Intent que contém os dados retornados pela Activity de destino.
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PHOTO_PICKER_REQUEST) { // Verificando se requestCode é referente ao fornecido na chamada startActiviyForResul
            if(resultCode == Activity.RESULT_OK) { // Verificando se o resultCode é verdadeiro
                Uri photoSelected = data.getData(); // Se as condições forem verdadeiras, obtemos o resultado
                ImageView imvPhotoPreview = findViewById(R.id.imvPhotoPreview); // Obtendo a ImageView
                imvPhotoPreview.setImageURI(photoSelected); // Setando a imagem da URI

                NewItemActivityViewModel vm = new ViewModelProvider(this).get(NewItemActivityViewModel.class); // Obtendo o ViewModel
                vm.setSelectPhotoLocation(photoSelected); // Guardando o endereço URI da imagem

            }
        }
    }
}