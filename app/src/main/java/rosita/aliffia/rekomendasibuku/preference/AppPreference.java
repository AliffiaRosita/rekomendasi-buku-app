package rosita.aliffia.rekomendasibuku.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class AppPreference {
    public static final String NIM = "NIM";
    public static final String TOKEN = "TOKEN";
    public static final String NAMA = "NAMA";
    public static final String IS_ACTIVE = "IS_ACTIVE";
    private static final String PREFS_NAME = "user_pref";


    private SharedPreferences sharedPreferences;


    public AppPreference(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
    }

    public void setUser(UserModel value){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(NAMA, value.name);
        editor.putString(NIM, value.nim);
        editor.putString(TOKEN, value.token);
        editor.putBoolean(IS_ACTIVE, value.isActive);
        editor.apply();
    }

    UserModel  getUser(){
        UserModel model = new UserModel();
        model.setName(sharedPreferences.getString(NAMA,""));
        model.setNim(sharedPreferences.getString(NIM,""));
        model.setToken(sharedPreferences.getString(TOKEN,""));
        model.setActive(sharedPreferences.getBoolean(IS_ACTIVE,false));

        return model;
    }


}
