package rosita.aliffia.rekomendasibuku.preference;

import android.os.Parcel;
import android.os.Parcelable;

public class UserModel implements Parcelable {
    String name;
    String nim;
    Boolean isActive;
    String token;

    protected UserModel(Parcel in) {
        name = in.readString();
        nim = in.readString();
        byte tmpIsActive = in.readByte();
        isActive = tmpIsActive == 0 ? null : tmpIsActive == 1;
        token = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.name);
        parcel.writeString(this.nim);
        parcel.writeString(this.token);
        parcel.writeByte(this.isActive ? (byte) 1 : (byte) 0);;
    }
    public UserModel(){}

}
