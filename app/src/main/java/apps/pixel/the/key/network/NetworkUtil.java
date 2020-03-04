package apps.pixel.the.key.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import apps.pixel.the.key.utilities.Constant;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

public class NetworkUtil {

    public static RetrofitInterface getRetrofitNoHeader() {

        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(30, TimeUnit.SECONDS);
        httpClient.readTimeout(30 , TimeUnit.SECONDS);
        return new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL_HTTP)
                .client(httpClient.build())
                .addCallAdapterFactory(rxAdapter)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(RetrofitInterface.class);

    }


    public static RetrofitInterface getRetrofitByTokenForChat(String token) {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        String newToken = "Bearer " + token;
        httpClient.addInterceptor(chain -> {

            Request original = chain.request();
            Request.Builder builder = original.newBuilder()
                    .addHeader("Authorization", newToken)
                    .method(original.method(), original.body());
            return chain.proceed(builder.build());

        });

        return new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL_HTTP)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(RetrofitInterface.class);
    }

    public static RetrofitInterface getRetrofitByToken(String token) {


        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        String newToken = "Bearer " + token;
        httpClient.addInterceptor(chain -> {

            Request original = chain.request();
            Request.Builder builder = original.newBuilder()
                    .addHeader("Authorization", newToken)
                    .method(original.method(), original.body());
            return chain.proceed(builder.build());

        });

        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());

        return new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL_HTTP)
                .client(httpClient.build())
                .addCallAdapterFactory(rxAdapter)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build().create(RetrofitInterface.class);
    }

}