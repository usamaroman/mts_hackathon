package by.eapp.mts.di

import android.content.Context
import by.eapp.mts.Const
import by.eapp.mts.data.remote.ApiService
import by.eapp.mts.data.repository.ApiInteractionRepositoryImpl
import by.eapp.mts.domain.repository.ApiInteractionRepository
import by.eapp.mts.domain.use_cases.GetContactsUseCase
import by.eapp.mts.domain.use_cases.SendSpeechUseCase
import by.eapp.mts.network.NetworkMonitor
import com.apollographql.apollo3.ApolloClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient):
            Retrofit = Retrofit
        .Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Const.BASE_URL)
        .client(okHttpClient)
        .build()


    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()


    @Singleton
    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)


    @Provides
    @Singleton
    fun providesApiRepository(
        apiService: ApiService,
        apolloClient: ApolloClient):
        ApiInteractionRepository = ApiInteractionRepositoryImpl(apiService, apolloClient)


    @Provides
    @Singleton
    fun providesGetContactsUseCase(apiRepository: ApiInteractionRepository) = GetContactsUseCase(apiRepository)

    @Provides
    @Singleton
    fun providesSendSpeechUseCase(apiRepository: ApiInteractionRepository) = SendSpeechUseCase(apiRepository)

    @Provides
    @Singleton
    fun provideNetworkMonitor(@ApplicationContext context: Context): NetworkMonitor {
        return NetworkMonitor(context)
    }


    @Singleton
    @Provides
    fun providesApolloClient():ApolloClient {
        return ApolloClient.Builder()
            .serverUrl(Const.BASE_URL + "graphql")
            .build()
    }


}