package rosita.aliffia.rekomendasibuku.data;

import com.google.gson.annotations.SerializedName;

public class Rekomendasi {
    @SerializedName("id_buku")
    int idBuku;
    @SerializedName("judul")
    String judul;
    @SerializedName("deskripsi")
    String desc;
    @SerializedName("foto")
    String foto;

}
