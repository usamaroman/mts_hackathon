package by.eapp.mts.di

import by.eapp.mts.Const
import by.eapp.mts.data.remote.ApiService
import by.eapp.mts.data.repository.ApiInteractionRepositoryImpl
import by.eapp.mts.domain.repository.ApiInteractionRepository
import by.eapp.mts.domain.use_cases.GetContactsUseCase
import by.eapp.mts.domain.use_cases.SendSpeechUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
    fun providesApiRepository(apiService: ApiService): ApiInteractionRepository = ApiInteractionRepositoryImpl(apiService)


    @Provides
    @Singleton
    fun providesGetContactsUseCase(apiRepository: ApiInteractionRepository) = GetContactsUseCase(apiRepository)

    @Provides
    @Singleton
    fun providesSendSpeechUseCase(apiRepository: ApiInteractionRepository) = SendSpeechUseCase(apiRepository)

}