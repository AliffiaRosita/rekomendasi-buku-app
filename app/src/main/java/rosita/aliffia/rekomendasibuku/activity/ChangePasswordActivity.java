package rosita.aliffia.rekomendasibuku.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rosita.aliffia.rekomendasibuku.R;
import rosita.aliffia.rekomendasibuku.api.ApiClient;
import rosita.aliffia.rekomendasibuku.api.ApiInterface;
import rosita.aliffia.rekomendasibuku.response.ResponseUpdatePass;

public class ChangePasswordActivity extends AppCompatActivity {

    TextInputLayout etOldPass,etNewPass,etConfirm;
    Button btnUpdate;
    String newPass, oldPass, confirmPass;
    ApiInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        etOldPass = findViewById(R.id.old_pass);
        etNewPass = findViewById(R.id.new_pass);
        etConfirm = findViewById(R.id.confirm_pass);
        btnUpdate = findViewById(R.id.btn_update_pass);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newPass = etNewPass.getEditText().getText().toString().trim();
                oldPass = etOldPass.getEditText().getText().toString().trim();
                confirmPass = etConfirm.getEditText().getText().toString().trim();
                if (newPass.isEmpty()){
                    etNewPass.setError("Password baru wajib diisi");
                }if (oldPass.isEmpty()){
                    etOldPass.setError("Password lama wajib diisi");
                }if (confirmPass.isEmpty()){
                    etConfirm.setError("password konfirmasi wajib diisi");
                }if (newPass.length() <6){
                    etNewPass.setError("Password minimal 6 karakter");
                }
                if (!newPass.equals(confirmPass)){
                    Toast.makeText(ChangePasswordActivity.this, "Password Konfirmasi Tidak Cocok", Toast.LENGTH_SHORT).show();
                }
                    Call<ResponseUpdatePass> call = apiInterface.updatePass(newPass,oldPass);
                    call.enqueue(new Callback<ResponseUpdatePass>() {
                        @Override
                        public void onResponse(Call<ResponseUpdatePass> call, Response<ResponseUpdatePass> response) {
                            if (response.isSuccessful()){
                                Toast.makeText(ChangePasswordActivity.this, "Berhasil mengubah password"+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseUpdatePass> call, Throwable t) {
                            Toast.makeText(ChangePasswordActivity.this, ""+t, Toast.LENGTH_SHORT).show();
                        }
                    });
                }


        });
    }

}