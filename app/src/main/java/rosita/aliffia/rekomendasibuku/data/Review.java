package rosita.aliffia.rekomendasibuku.data;

import com.google.gson.annotations.SerializedName;

public class Review {
    @SerializedName("visitor_name")
    String namaPengunjung;
    @SerializedName("rating")
    String rating;
    @SerializedName("ulasan")
    String ulasan;
    @SerializedName("foto")
    String foto;

    public String getFoto() {
        return foto;
    }

    public String getNamaPengunjung() {
        return namaPengunjung;
    }

    public String getRating() {
        return rating;
    }

    public String getUlasan() {
        return ulasan;
    }
}
