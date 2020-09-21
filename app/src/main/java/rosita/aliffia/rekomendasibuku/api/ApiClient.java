package rosita.aliffia.rekomendasibuku.api;

import android.content.Context;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rosita.aliffia.rekomendasibuku.preference.AppPreference;
import rosita.aliffia.rekomendasibuku.preference.UserModel;

public class ApiClient {
    public AppPreference appPreference;
    public static UserModel userModel;
    private static final String BASE_URL = "http://192.168.1.37:8000/api/";
    private static Retrofit retrofit = null;
    public ApiClient(Context context){
        AppPreference appPreference = new AppPreference(context);
        userModel = appPreference.getUser();
    }
    public static Retrofit getClient(){

        if (retrofit== null){
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request newRequest = chain.request().newBuilder()
                            .addHeader("Authorization","Bearer "+userModel.getToken())
                            .build();

                    return chain.proceed(newRequest);
                }
            }).build();
            retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
   return retrofit;
    }
}
