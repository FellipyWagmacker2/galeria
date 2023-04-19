package silva.pereira.galeria.model;

import android.net.Uri;

import androidx.lifecycle.ViewModel;

public class NewItemActivityViewModel extends ViewModel {
    Uri selectPhotoLocation = null; // Guardando edereço URI

    public Uri getSelectPhotoLocation() { // Get para obter a lista de itens
        return selectPhotoLocation; // Retornando a lista
    }

    public void setSelectPhotoLocation(Uri selectPhotoLocation) { // Método set usado para setar a URI dentro do View model
        this.selectPhotoLocation = selectPhotoLocation;
    }
}
