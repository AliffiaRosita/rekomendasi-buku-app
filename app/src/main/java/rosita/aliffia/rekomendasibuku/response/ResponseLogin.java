package rosita.aliffia.rekomendasibuku.response;

import com.google.gson.annotations.SerializedName;

import rosita.aliffia.rekomendasibuku.data.Login;

public class ResponseLogin {
    @SerializedName("message")
    String message;

    @SerializedName("data")
    Login login;

    public String getMessage() {
        return message;
    }

    public Login getLogin() {
        return login;
    }
}
