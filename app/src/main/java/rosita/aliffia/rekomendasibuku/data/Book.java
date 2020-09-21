package rosita.aliffia.rekomendasibuku.data;

import com.google.gson.annotations.SerializedName;

public class Book {
    @SerializedName("judul")
    String judul;
    @SerializedName("penerbit")
    String penerbit;
    @SerializedName("deskripsi")
    String deskripsi;
    @SerializedName("foto")
    String foto;
    @SerializedName("id_buku")
    int id;

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public String getFoto() {
        return foto;
    }

    public int getId() {
        return id;
    }
}
