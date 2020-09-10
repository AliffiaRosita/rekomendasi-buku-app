package rosita.aliffia.rekomendasibuku.preference;

import android.os.Parcel;
import android.os.Parcelable;

public class UserModel implements Parcelable {
    String name;
    String nim;
    Boolean isActive;
    String token;
    String fakultas,angkatan,fotoProfil,userId;

    protected UserModel(Parcel in) {
        name = in.readString();
        nim = in.readString();
        byte tmpIsActive = in.readByte();
        isActive = tmpIsActive == 0 ? null : tmpIsActive == 1;
        token = in.readString();
        fakultas = in.readString();
        angkatan = in.readString();
        fotoProfil = in.readString();
        userId = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(nim);
        dest.writeByte((byte) (isActive == null ? 0 : isActive ? 1 : 2));
        dest.writeString(token);
        dest.writeString(fakultas);
        dest.writeString(angkatan);
        dest.writeString(fotoProfil);
        dest.writeString(userId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UserModel> CREATOR = new Creator<UserModel>() {
        @Override
        public UserModel createFromParcel(Parcel in) {
            return new UserModel(in);
        }

        @Override
        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFakultas() {
        return fakultas;
    }

    public void setFakultas(String fakultas) {
        this.fakultas = fakultas;
    }

    public String getAngkatan() {
        return angkatan;
    }

    public void setAngkatan(String angkatan) {
        this.angkatan = angkatan;
    }

    public String getFotoProfil() {
        return fotoProfil;
    }

    public void setFotoProfil(String fotoProfil) {
        this.fotoProfil = fotoProfil;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public UserModel(){}

}
