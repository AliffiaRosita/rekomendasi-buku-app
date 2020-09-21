package rosita.aliffia.rekomendasibuku.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import rosita.aliffia.rekomendasibuku.data.Book;
import rosita.aliffia.rekomendasibuku.data.Rekomendasi;

public class ResponseRekomendasi {
    @SerializedName("data")
    List<Book> rekomendasi;
    @SerializedName("message")
    String message;

    public List<Book> getRekomendasi() {
        return rekomendasi;
    }

    public String getMessage() {
        return message;
    }
}
