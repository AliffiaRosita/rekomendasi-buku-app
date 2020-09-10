package rosita.aliffia.rekomendasibuku.data;

import com.google.gson.annotations.SerializedName;

public class Login {

    @SerializedName("user_id")
    String userId;
    @SerializedName("nama")
    String nama;
    @SerializedName("nim")
    String nim;
    @SerializedName("token")
    String token;
    @SerializedName("fakultas")
    String fakultas;
    @SerializedName("angkatan")
    String angkatan;
    @SerializedName("foto_profil")
    String fotoProfil;

    public String getNim() {
        return nim;
    }

    public String getFakultas() {
        return fakultas;
    }

    public String getAngkatan() {
        return angkatan;
    }

    public String getFotoProfil() {
        return fotoProfil;
    }

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
