package rosita.aliffia.rekomendasibuku.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import rosita.aliffia.rekomendasibuku.api.ApiClient;

public class AppPreference {
    public static final String NIM = "NIM";
    public static final String TOKEN = "TOKEN";
    public static final String NAME = "NAME";
    public static final String IS_ACTIVE = "IS_ACTIVE";
    private static final String PREFS_NAME = "user_pref";
    private static final String FAKULTAS = "FAKULTAS";
    private static final String ANGKATAN = "ANGKATAN";
    private static final String USER_ID = "USER_ID";
    private static final String FOTO_PROFIL = "FOTO_PROFIL";



    private SharedPreferences sharedPreferences;


    public AppPreference(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
    }


    public void setUser(UserModel value){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(NAME, value.name);
        editor.putString(NIM, value.nim);
        editor.putString(TOKEN, value.token);
        editor.putString(FAKULTAS, value.fakultas);
        editor.putString(ANGKATAN, value.angkatan);
        editor.putString(FOTO_PROFIL, value.fotoProfil);
        editor.putString(USER_ID, value.userId);
        editor.putBoolean(IS_ACTIVE, value.isActive);
        editor.apply();
    }

    public UserModel getUser() {
        UserModel model = new UserModel();
        model.setName(sharedPreferences.getString(NAME,""));
        model.setNim(sharedPreferences.getString(NIM,""));
        model.setToken(sharedPreferences.getString(TOKEN,""));
        model.setFakultas(sharedPreferences.getString(FAKULTAS,""));
        model.setAngkatan(sharedPreferences.getString(ANGKATAN,""));
        model.setFotoProfil(sharedPreferences.getString(FOTO_PROFIL,""));
        model.setUserId(sharedPreferences.getString(USER_ID,""));
        model.setActive(sharedPreferences.getBoolean(IS_ACTIVE,false));

        return model;
    }


}
