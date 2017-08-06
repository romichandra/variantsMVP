package ardnahcimor.orp.swiggyvariants.network;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;

import javax.inject.Singleton;

import ardnahcimor.orp.swiggyvariants.BuildConfig;
import ardnahcimor.orp.swiggyvariants.model.ApiResponse;
import ardnahcimor.orp.swiggyvariants.model.Variants;
import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by romichandra on 02-08-2017.
 */


@Module
public class VariantsNetworkModule {

    File cacheFile;

    public VariantsNetworkModule(File cacheFile) {
        this.cacheFile = cacheFile;
    }

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static final Gson GSON = new Gson();

    @Provides
    @Singleton
    Retrofit provideCall() {
        Cache cache = null;
        try {
            cache = new Cache(cacheFile, 10 * 1024 * 1024);
        } catch (Exception e) {
            e.printStackTrace();
        }

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public okhttp3.Response intercept(Chain chain) throws IOException {
                        Request original = chain.request();

                        Request request = original.newBuilder()
                                .header("Content-Type", "application/json")
                                .removeHeader("Pragma")
                                .header("Cache-Control", String.format("max-age=%d", BuildConfig.CACHETIME))
                                .build();

                        okhttp3.Response response = chain.proceed(request);
                        response.cacheResponse();

                        return response;
                    }
                })
                .cache(cache)

                .build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASEURL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())

                .build();
    }

    @Provides
    @Singleton
    @SuppressWarnings("unused")
    public VariantsNetworkService providesNetworkService(
            Retrofit retrofit) {
        return retrofit.create(VariantsNetworkService.class);
    }
    @Provides
    @Singleton
    @SuppressWarnings("unused")
    public VariantsService providesService(
            VariantsNetworkService networkService) {
        return new VariantsService(networkService);
    }
}