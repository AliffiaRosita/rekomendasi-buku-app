package rosita.aliffia.rekomendasibuku.data;

import com.google.gson.annotations.SerializedName;

public class BookHome {
    @SerializedName("judul")
    String judul;
    @SerializedName("foto")
    String foto;
    @SerializedName("average")
    String average;
    @SerializedName("id")
    int id;

    public String getAverage() {
        return average;
    }

    public int getId() {
        return id;
    }

    public String getJudul() {
        return judul;
    }

    public String getFoto() {
        return foto;
    }
}
