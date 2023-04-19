package silva.pereira.galeria.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import silva.pereira.galeria.R;
import silva.pereira.galeria.activity.MainActivity;
import silva.pereira.galeria.model.MyItem;

public class MyAdapter extends RecyclerView.Adapter{
    MainActivity mainActivity; // Criando instância para classe mainActivity
    List<MyItem> itens; // Criando lista do tipo MyItem

    public  MyAdapter(MainActivity mainActivity, List<MyItem> itens) {
        this.mainActivity = mainActivity; // Chamando a instância
        this.itens = itens; // Chamando lista
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mainActivity); // LayoutInflater usado para ler arquivos xml
        View v = inflater.inflate(R.layout.item_list,parent,false); // Usando o LayoutInflater para criar elementos e gurandando ele denteo de uma View
        return new MyViewHolder(v); //Objeto do tipo View(v) é guardado no objeto MyViewHolder, e retorna o MyViewHolder
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyItem myItem = itens.get(position); // Obtendo item para preencher a UI
        View v = holder.itemView; // Obtendo os objetos do tipo View

        ImageView imvPhoto = v.findViewById(R.id.imvPhoto); // Obtendo imagem
        imvPhoto.setImageBitmap(myItem.photo); // Preenchendo a UI com a imagem

        TextView tvTitle = v.findViewById(R.id.tvTitle); // Obtendo titulo
        tvTitle.setText(myItem.title); // Preenchendo a UI com o titulo

        TextView tvDesc = v.findViewById(R.id.tvDesc); // Obtendo a descrição
        tvDesc.setText(myItem.description); //Preenchendo a UI com a descrição

    }

    @Override
    public int getItemCount() {
        return itens.size();
    }
}
