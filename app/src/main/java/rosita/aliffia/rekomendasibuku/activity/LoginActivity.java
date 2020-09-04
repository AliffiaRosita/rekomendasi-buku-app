package rosita.aliffia.rekomendasibuku.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rosita.aliffia.rekomendasibuku.R;
import rosita.aliffia.rekomendasibuku.api.ApiClient;
import rosita.aliffia.rekomendasibuku.api.ApiInterface;
import rosita.aliffia.rekomendasibuku.response.ResponseLogin;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnDaftar, btnMasuk;
    TextInputLayout etNim, etPass;
    private ApiInterface apiInterface;
    private String token,nim,pass, userId, name;
    public String TAG = "loginactivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnDaftar = findViewById(R.id.btn_login_daftar);
        btnMasuk = findViewById(R.id.btn_login_masuk);

        etNim =  findViewById(R.id.et_login_nim);
        etPass = findViewById(R.id.et_login_pass);

        btnDaftar.setOnClickListener(this);
        btnMasuk.setOnClickListener(this);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login_daftar:

                Intent daftar = new Intent(this,RegisterActivity.class);
                startActivity(daftar);
                break;
            case R.id.btn_login_masuk:
                nim = etNim.getEditText().getText().toString().trim();
                pass = etPass.getEditText().getText().toString().trim();
                if (nim.isEmpty()){
                    etNim.setError("Nim wajib diisi");
                }if(pass.isEmpty()){
                etPass.setError("Password wajib diisi");
            }
                login();
                break;
        }
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
                    name = response.body().getLogin().getNama();
                    Intent masuk = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(masuk);
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                Log.d("LoginActivity", "onFailure: "+t);
            }
        });
    }
}