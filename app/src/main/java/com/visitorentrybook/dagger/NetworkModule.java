
package com.visitorentrybook.dagger;


import com.visitorentrybook.app.Constants;
import com.visitorentrybook.network.VisitorEntryBookApi;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

  @Provides
  String provideBaseUrlString() {
    return Constants.BASE_URL;
  }

  @Provides
  @Singleton
  Converter.Factory provideGsonConverter() {
    return GsonConverterFactory.create();
  }

  @Provides
   OkHttpClient getClient() {
    OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.MINUTES)
            .readTimeout(5, TimeUnit.MINUTES)
            .build();
    return client;
  }


  @Provides
  @Singleton
  Retrofit provideRetrofit(Converter.Factory converter, String baseUrl) {

    return new Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(converter)
        .client(getClient())
        .build();
  }

  @Provides
  @Singleton
  VisitorEntryBookApi provideUsdaApi(Retrofit retrofit) {
    return retrofit.create(VisitorEntryBookApi.class);
  }
}
