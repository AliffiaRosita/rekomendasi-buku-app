package rosita.aliffia.rekomendasibuku.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rosita.aliffia.rekomendasibuku.R;
import rosita.aliffia.rekomendasibuku.api.ApiClient;
import rosita.aliffia.rekomendasibuku.api.ApiInterface;
import rosita.aliffia.rekomendasibuku.preference.AppPreference;
import rosita.aliffia.rekomendasibuku.preference.UserModel;
import rosita.aliffia.rekomendasibuku.response.ResponseLogin;
import rosita.aliffia.rekomendasibuku.response.ResponseRegis;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    TextInputLayout etnama, etNim, etAngkatan, etpassword;
    private String nama,nim,angkatan,pass,token, fakultas,userId,fotoProfil;
    Spinner spinnerFakultas;
    Button btnSave;
    ApiInterface apiInterface;
     UserModel userModel;
    AppPreference appPreference;
    String TAG="RegisterActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etnama = findViewById(R.id.et_regis_nama);
        etNim = findViewById(R.id.et_regis_nim);
        etAngkatan  = findViewById(R.id.et_regis_angkatan);
        etpassword = findViewById(R.id.et_regis_pass);
        spinnerFakultas = findViewById(R.id.et_regis_fakultas);
        btnSave = findViewById(R.id.btn_regis_daftar);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        btnSave.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_regis_daftar:
                nim = etNim.getEditText().getText().toString().trim();
                pass = etpassword.getEditText().getText().toString().trim();
                nama = etnama.getEditText().getText().toString().trim();
                angkatan = etAngkatan.getEditText().getText().toString().trim();
                if (nim.isEmpty()){
                    etNim.setError("Nim wajib diisi");
                }if(pass.isEmpty()){
                etpassword.setError("Password wajib diisi");
                 }
                if(nama.isEmpty()){
                    etnama.setError("Nama wajib diisi");
                }
                if(angkatan.isEmpty()){
                    etAngkatan.setError("Angkatan wajib diisi");
                }
                fakultas = spinnerFakultas.getSelectedItem().toString();
                register();
                break;
        }
    }

    private void register() {
        Call<ResponseRegis> regisCall = apiInterface.registration(nama,nim,fakultas,angkatan,pass);
        regisCall.enqueue(new Callback<ResponseRegis>() {
            @Override
            public void onResponse(Call<ResponseRegis> call, Response<ResponseRegis> response) {
                login();

            }

            @Override
            public void onFailure(Call<ResponseRegis> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, ""+t, Toast.LENGTH_SHORT).show();

            }
        });
    }


    private void login(){
        Call<ResponseLogin> loginCall = apiInterface.login(nim,pass);
        loginCall.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                if (response.code() == 404){
                    Toast.makeText(getApplicationContext(),"pengguna tidak ditemukan", Toast.LENGTH_SHORT).show();
                }else{
                    token = response.body().getLogin().getToken();
                    userId = response.body().getLogin().getUserId();
                    fotoProfil = response.body().getLogin().getFotoProfil();
                    Log.d(TAG, "onResponse: "+fakultas);
                    AppPreference appPreference = new AppPreference(RegisterActivity.this);
                    userModel = new UserModel();
                    userModel.setToken(token);
                    userModel.setName(nama);
                    userModel.setUserId(userId);
                    userModel.setFotoProfil(fotoProfil);
                    userModel.setFakultas(fakultas);
                    userModel.setAngkatan(angkatan);
                    userModel.setNim(nim);
                    userModel.setActive(true);

                    appPreference.setUser(userModel);

                    Intent masuk = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(masuk);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                Log.d("LoginActivity", "onFailure: "+t);
            }
        });
    }
}