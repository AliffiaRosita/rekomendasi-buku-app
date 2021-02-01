package rosita.aliffia.rekomendasibuku.data;

import com.google.gson.annotations.SerializedName;

public class Errors {
    @SerializedName("errors")
    Nim nim;

    public Nim getNim() {
        return nim;
    }

    public class Nim{
        @SerializedName("nim")
        String[] nim;

        public String[] getNim() {
            return nim;
        }
    }
}

