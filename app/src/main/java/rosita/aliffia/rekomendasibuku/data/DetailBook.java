package rosita.aliffia.rekomendasibuku.data;

import com.google.gson.annotations.SerializedName;

public class DetailBook {
    @SerializedName("bookId")
    String id;
    @SerializedName("judul")
    String judul;
    @SerializedName("foto")
    String foto;
    @SerializedName("average")
    float average;
    @SerializedName("penerbit")
    String penerbit;
    @SerializedName("isbn")
    String isbn;
    @SerializedName("rating")
    float userRating;
    @SerializedName("ulasan")
    String userUlasan;
    @SerializedName("tempat_terbit")
    String tempatTerbit;

    public String getTempatTerbit() {
        return tempatTerbit;
    }

    public String getId() {
        return id;
    }

    public String getJudul() {
        return judul;
    }

    public String getFoto() {
        return foto;
    }

    public float getAverage() {
        return average;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public String getIsbn() {
        return isbn;
    }

    public float getUserRating() {
        return userRating;
    }

    public String getUserUlasan() {
        return userUlasan;
    }
}
