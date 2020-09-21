package rosita.aliffia.rekomendasibuku.response;

import com.google.gson.annotations.SerializedName;

import rosita.aliffia.rekomendasibuku.data.DetailBook;

public class ResponseDetail {
    @SerializedName("data")
    DetailBook detailBook;
    @SerializedName("message")
    String message;

    public DetailBook getDetailBook() {
        return detailBook;
    }

    public String getMessage() {
        return message;
    }
}
