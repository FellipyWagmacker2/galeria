package pereira.silva.lista.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pereira.silva.lista.activity.MainActivity;
import pereira.silva.lista.model.MyItem;
import silva.pereira.galeria.R;

public class MyAdapter extends RecyclerView.Adapter{
    MainActivity mainActivity;
    List<MyItem> itens;

    public  MyAdapter(MainActivity mainActivity, List<MyItem> itens) {
        this.mainActivity = mainActivity;
        this.itens = itens;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mainActivity);
        View v = inflater.inflate(R.layout.item_list,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyItem myItem = itens.get(position);
        View v = holder.itemView;

        ImageView imvPhoto = v.findViewById(R.id.imvPhoto);
        imvPhoto.setImageURI(myItem.photo);




    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
