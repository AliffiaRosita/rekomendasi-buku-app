package rosita.aliffia.rekomendasibuku.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rosita.aliffia.rekomendasibuku.R;
import rosita.aliffia.rekomendasibuku.api.ApiClient;
import rosita.aliffia.rekomendasibuku.api.ApiInterface;
import rosita.aliffia.rekomendasibuku.fragment.UserFragment;
import rosita.aliffia.rekomendasibuku.preference.AppPreference;
import rosita.aliffia.rekomendasibuku.preference.UserModel;
import rosita.aliffia.rekomendasibuku.response.ResponseUpdateProfile;

public class ChangeProfileActivity extends AppCompatActivity implements View.OnClickListener {

    FloatingActionButton fabUpload;
    ImageView imgUpload;
    private int IMG_REQUEST = 21;
    private Bitmap bitmap;
    Button btnSave;
    ApiInterface apiInterface;
    AppPreference appPreference;
    UserModel userModel;
    String nama,fakultas,angkatan,newfakultas;
    TextInputLayout etName,etAngkatan,etNim;
    Spinner spinnerFakultas;
    Uri path;
    String TAG = "ChangeProfileActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_profile);
        fabUpload = findViewById(R.id.fab_image);
        imgUpload = findViewById(R.id.image_upload);
        btnSave = findViewById(R.id.btn_save_profil);
        etName = findViewById(R.id.edit_nama);
        etNim = findViewById(R.id.edit_nim);
        etAngkatan = findViewById(R.id.edit_angkatan);
        spinnerFakultas = findViewById(R.id.edit_fakultas);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        appPreference = new AppPreference(ChangeProfileActivity.this);
        userModel = appPreference.getUser();

        etName.getEditText().setText(userModel.getName());
        etNim.getEditText().setEnabled(false);
        etNim.getEditText().setText(userModel.getNim());
        etAngkatan.getEditText().setText(userModel.getAngkatan());
        Glide.with(this).load(userModel.getFotoProfil()).into(imgUpload);
        fakultas = userModel.getFakultas();

        String[] fakultas_list = getResources().getStringArray(R.array.list_fakultas);
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,fakultas_list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFakultas.setAdapter(adapter);

        if (fakultas != null) {
            int spinnerPosition = adapter.getPosition(fakultas);
            spinnerFakultas.setSelection(spinnerPosition);
        }
        fabUpload.setOnClickListener(this);
        btnSave.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fab_image:
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(i, IMG_REQUEST);
                break;
            case R.id.btn_save_profil:
                if (bitmap != null)
                    uploadImage();
                else {
//                    Toast.makeText(getApplicationContext(), "Bitmap is null. Try again", Toast.LENGTH_SHORT).show();
                    sendResponse(null);
                }
                break;
        }
    }



    private void uploadImage() {

        File filesDir = getApplicationContext().getFilesDir();
        File file = new File(filesDir,"image"+".png");
        OutputStream os;

        try {
            os = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.PNG,100,os);
            os.flush();
            os.close();

        } catch (Exception e) {
            Log.e(getClass().getSimpleName(), "Error writing bitmap", e);
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,0,bos);

        byte[] bitmapdata = bos.toByteArray();

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(bitmapdata);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        sendResponse(file);

    }

    public void sendResponse(File file){
        nama = etName.getEditText().getText().toString().trim();
        newfakultas = spinnerFakultas.getSelectedItem().toString();
        angkatan = etAngkatan.getEditText().getText().toString().trim();
        MultipartBody.Part part;
        if (file != null){
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"),file);
            part = MultipartBody.Part.createFormData("foto_profil",file.getName(),requestBody);
        }else{
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"),"");
            part = MultipartBody.Part.createFormData("foto_profil",null,requestBody);
        }
        RequestBody namaf = RequestBody.create(MediaType.parse("text/plain"),nama);
        RequestBody fakultasf = RequestBody.create(MediaType.parse("text/plain"),newfakultas);
        RequestBody angkatanf = RequestBody.create(MediaType.parse("text/plain"),angkatan);

        Call<ResponseUpdateProfile> responseUpdateProfileCall = apiInterface.updateProfile(namaf,fakultasf,angkatanf,part);
        responseUpdateProfileCall.enqueue(new Callback<ResponseUpdateProfile>() {
            @Override
            public void onResponse(Call<ResponseUpdateProfile> call, Response<ResponseUpdateProfile> response) {
                if (response.isSuccessful()){
                    Toast.makeText(ChangeProfileActivity.this, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                    UserModel userModel =new UserModel();
                    userModel.setName(response.body().getNama());
                    userModel.setAngkatan(response.body().getAngkatan());
                    userModel.setFakultas(response.body().getFakultas());
                    if (response.body().getFotoProfil() != null){
                        userModel.setFotoProfil(response.body().getFotoProfil());
                    }
                    appPreference.setUser(userModel);
//                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//                    fragmentTransaction.replace(R.id.change_profile, new UserFragment()).commit();
                    Toast.makeText(ChangeProfileActivity.this, "Berhasil mengubah profil", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseUpdateProfile> call, Throwable t) {
                Toast.makeText(ChangeProfileActivity.this, ""+t, Toast.LENGTH_SHORT).show();
                Log.d(TAG, "onFailure: "+t);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMG_REQUEST&& resultCode == RESULT_OK && data!= null){

             path = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),path);
            } catch (IOException e) {
                e.printStackTrace();
            }
            imgUpload.setImageBitmap(bitmap);

        }
    }
}