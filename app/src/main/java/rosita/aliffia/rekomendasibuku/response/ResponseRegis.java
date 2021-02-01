package rosita.aliffia.rekomendasibuku.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import rosita.aliffia.rekomendasibuku.data.Errors;

public class ResponseRegis {
    @SerializedName("message")
    String message;


    public String getMessage() {
        return message;
    }


}
