package rosita.aliffia.rekomendasibuku.api;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rosita.aliffia.rekomendasibuku.response.ResponseLogin;
import rosita.aliffia.rekomendasibuku.response.ResponseRegis;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("login")
    Call<ResponseLogin> login(
            @Field("nim") String nim,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("register")
    Call<ResponseRegis> registration(
            @Field("nama_pengunjung") String nama,
            @Field("nim") String nim,
            @Field("fakultas") String fakultas,
            @Field("angkatan") String angkatan,
            @Field("password") String password
    );

}
