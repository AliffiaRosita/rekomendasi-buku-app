package rosita.aliffia.rekomendasibuku.data;

import com.google.gson.annotations.SerializedName;

public class Login {

    @SerializedName("user_id")
    String userId;
    @SerializedName("nama")
    String nama;
    @SerializedName("token")
    String token;

    public String getUserId() {
        return userId;
    }

    public String getNama() {
        return nama;
    }

    public String getToken() {
        return token;
    }
}
