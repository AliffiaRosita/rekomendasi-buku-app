package rosita.aliffia.rekomendasibuku.response;

import com.google.gson.annotations.SerializedName;

public class ResponseUpdateProfile {
    @SerializedName("message")
    String message;

    @SerializedName("nama")
    String nama;
    @SerializedName("fakultas")
    String fakultas;
    @SerializedName("angkatan")
    String angkatan;
    @SerializedName("foto_profil")
    String fotoProfil;

    public String getFotoProfil() {
        return fotoProfil;
    }

    public String getNama() {
        return nama;
    }

    public String getFakultas() {
        return fakultas;
    }

    public String getAngkatan() {
        return angkatan;
    }

    public String getMessage() {
        return message;
    }
}
