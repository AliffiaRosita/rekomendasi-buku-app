package rosita.aliffia.rekomendasibuku.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import rosita.aliffia.rekomendasibuku.data.BookHome;

public class ResponseBookHome {
    @SerializedName("message")
    String message;
    @SerializedName("data")
    List<BookHome> bookHomes;

    public String getMessage() {
        return message;
    }

    public List<BookHome> getBookHomes() {
        return bookHomes;
    }
}
