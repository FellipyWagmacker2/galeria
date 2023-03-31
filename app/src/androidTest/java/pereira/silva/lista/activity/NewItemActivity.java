package pereira.silva.lista.activity;

import androidx.appcompat.app.AppCompatActivity;

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

import javax.annotation.Nullable;

import silva.pereira.galeria.R;

public class NewItemActivity extends AppCompatActivity {
    static int PHOTO_PICKER_REQUEST = 1; // Variável do tipo inteiro
    Uri photoSelected = null; // Guardando o endereço da foto selecionada

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        Button btnAddItem = findViewById(R.id.btnAddItem); // Obtendo o Button
        btnAddItem.setOnClickListener(new View.OnClickListener() { // Setando ouvidor de clicks
            @Override
            public void onClick(View view) {

                if (photoSelected == null){ // Verificando se os campos foram preenchidos
                    Toast.makeText(NewItemActivity.this, "È necessário selecionar uma imagem!", Toast.LENGTH_LONG).show();
                    return;
                }

                EditText etTitle = findViewById(R.id.etTitle);
                String title = etTitle.getText().toString();
                if (title.isEmpty()){
                    Toast.makeText(NewItemActivity.this, "È necessário inserir um título", Toast.LENGTH_LONG).show();
                    return;
                }
                EditText etDesc = findViewById(R.id.etDesc);
                String description = etDesc.getText().toString();
                if (description.isEmpty()) {
                    Toast.makeText(NewItemActivity.this, "É necessário inserir uma descrição", Toast.LENGTH_LONG).show();
                    return;
                    }

                Intent i = new Intent();
                i.setData(photoSelected);
                i.putExtra("title", title);
                i.putExtra("description", description);
                setResult(Activity.RESULT_OK, i);
                finish();
            }
        });

        ImageButton imgCI = findViewById(R.id.imbCI); // Obtendo o ImageButton
        imgCI.setOnClickListener(new View.OnClickListener() { // Definindo o ouvidor de clicks
            @Override
            public void onClick(View view) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT); // Abrindo galeria de fotos usando uma intent com ACTION do tipo ACTION_OPEN_DOCUMENT
                photoPickerIntent.setType("image/*"); // Setando que estamos interessados somente em documentos com mimetype "image/*"
                startActivityForResult(photoPickerIntent, PHOTO_PICKER_REQUEST); // Executando a intent usando o metodo startActivityForResult

            }

        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) { // Método onActivityResult que recebe 3 parametros resquestCode: inteiro que indica a qual chamada de startActivityForResult, resultCode: código que indica se a Activity de destino retornou com sucesso ou
        // não, data:  Intent que contém os dados retornados pela Activity de destino.
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PHOTO_PICKER_REQUEST) { // Verificando se requestCode é referente ao fornecido na chamada startActiviyForResul
            if(resultCode == Activity.RESULT_OK) { // Se as condições forem verdadeiras, obtemos o resultado
                photoSelected = data.getData();
                ImageView imvPhotoPreview = findViewById(R.id.imvPhotoPreview); // Obtendo a ImageView e setendo o Uri
                imvPhotoPreview.setImageURI(photoSelected);

                }
            }
        }
}