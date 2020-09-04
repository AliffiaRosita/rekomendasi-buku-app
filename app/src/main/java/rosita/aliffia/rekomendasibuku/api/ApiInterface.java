package rosita.aliffia.rekomendasibuku.api;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rosita.aliffia.rekomendasibuku.response.ResponseLogin;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("login")
    Call<ResponseLogin> login(
            @Field("nim") String nim,
            @Field("password") String password
    );
}
