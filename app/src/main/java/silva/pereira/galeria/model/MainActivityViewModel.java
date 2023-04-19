package silva.pereira.galeria.model;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;


public class MainActivityViewModel extends ViewModel {
    List<MyItem> itens = new ArrayList<>(); // Lista que armazena os itens

    public List<MyItem> getItens(){ // Método para obter os itens
        return itens; // Retornando os itens
    }
}
