package rosita.aliffia.rekomendasibuku.api;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rosita.aliffia.rekomendasibuku.response.ResponseAllRate;
import rosita.aliffia.rekomendasibuku.response.ResponseBook;
import rosita.aliffia.rekomendasibuku.response.ResponseBookHome;
import rosita.aliffia.rekomendasibuku.response.ResponseDetail;
import rosita.aliffia.rekomendasibuku.response.ResponseLogin;
import rosita.aliffia.rekomendasibuku.response.ResponseRegis;
import rosita.aliffia.rekomendasibuku.response.ResponseRekomendasi;
import rosita.aliffia.rekomendasibuku.response.ResponseSaveRate;
import rosita.aliffia.rekomendasibuku.response.ResponseUpdatePass;
import rosita.aliffia.rekomendasibuku.response.ResponseUpdateProfile;

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

    @GET("books/mostRated")
    Call<ResponseBookHome> mostRated();

    @GET("books/detail/{buku_id}")
    Call<ResponseDetail> bookDetail(@Path("buku_id") int bukuId);

    @GET("books/{buku_id}/allRate")
    Call<ResponseAllRate> allRate(@Path("buku_id") int bukuId);

    @FormUrlEncoded
    @POST("books/rate/save")
    Call<ResponseSaveRate> saveRate(
        @Field("buku_id") int buku_id,
        @Field("nilai") float nilai,
        @Field("ulasan") String ulasan
    );

    @GET("books/search")
    Call<ResponseBook> searchBook(
            @Query("keyword") String keyword,
            @Query("page") int page
    );

    @GET("recommendation")
    Call<ResponseRekomendasi> getRecommend();

    @Multipart
    @POST("user/update")
    Call<ResponseUpdateProfile>updateProfile(
            @Part("nama_pengunjung") RequestBody nama,
            @Part("fakultas") RequestBody fakultas,
            @Part("angkatan") RequestBody angkatan,
            @Part MultipartBody.Part fotoProfil
    );

    @FormUrlEncoded
    @POST("user/update/password")
    Call<ResponseUpdatePass> updatePass(
            @Field("new_password") String newPass,
            @Field("old_password") String oldPass

    );
}
