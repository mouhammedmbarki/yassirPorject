package com.example.mbarki_mohamed_yassir_project.di

import android.content.Context
import androidx.room.Room
import com.example.mbarki_mohamed_yassir_project.domaine.Repository.detailMovie.DetailMovieImp
import com.example.mbarki_mohamed_yassir_project.domaine.Repository.detailMovie.DetailMovieRepository
import com.example.mbarki_mohamed_yassir_project.domaine.Repository.dto.detailMovie.DomainDtoUiDetailMovieMappingImp
import com.example.mbarki_mohamed_yassir_project.domaine.Repository.dto.detailMovie.RemoteResponseDtoLocalDetailMoveMappingImp
import com.example.mbarki_mohamed_yassir_project.domaine.Repository.dto.movieList.DomainDtoUiMappingImp
import com.example.mbarki_mohamed_yassir_project.domaine.Repository.dto.movieList.RemoteResponseDtoLocalMappingImp
import com.example.mbarki_mohamed_yassir_project.domaine.Repository.listMovie.ListMovieRepository
import com.example.mbarki_mohamed_yassir_project.domaine.Repository.listMovie.ListMovieRepositoryImpl
import com.example.mbarki_mohamed_yassir_project.domaine.local.DBManager.AppDatabase
import com.example.mbarki_mohamed_yassir_project.domaine.local.localManagers.ListMoveLocal.MovieLocalManager
import com.example.mbarki_mohamed_yassir_project.domaine.local.localManagers.detailMoveLocal.DetailMovieLocalManager
import com.example.mbarki_mohamed_yassir_project.domaine.remote.ListMovieRemote.manager.ApiMovieServices
import com.example.mbarki_mohamed_yassir_project.domaine.remote.detailMovie.manager.ApiDetailMovie
import com.example.mbarki_mohamed_yassir_project.utils.AppConstant.BASE_URL
import com.example.mbarki_mohamed_yassir_project.utils.AppConstant.DETAIL_MOVIE_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideBlogDb(@ApplicationContext context: Context): AppDatabase {
        return Room
            .databaseBuilder(
                context,
                AppDatabase::class.java,
                AppDatabase.DATABASE_NAME
            )
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @ListMovieRetrofit
    @Provides
    fun provideListRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Singleton
    @DetailMovieRetrofit
    @Provides
    fun provideDetailRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(DETAIL_MOVIE_BASE_URL)
        .client(okHttpClient)
        .build()

    @Singleton
    @Provides
    fun provideApiService(@ListMovieRetrofit retrofit: Retrofit): ApiMovieServices =
        retrofit.create(ApiMovieServices::class.java)

    @Singleton
    @Provides
    fun provideDetailApiService(@DetailMovieRetrofit retrofit: Retrofit): ApiDetailMovie =
        retrofit.create(ApiDetailMovie::class.java)


    @Provides
    @Singleton
    fun provideMovieDao(appDatabase: AppDatabase) = appDatabase.movieDao()


    @Provides
    @Singleton
    fun provideDetailMovieDao(appDatabase: AppDatabase) = appDatabase.DetailMovieDao()

    @Provides
    @Singleton
    fun provideListMovieRepository(
        apiMovieServices: ApiMovieServices,
        movieLocalManager: MovieLocalManager,
        domainDtoUiMappingImp: DomainDtoUiMappingImp,
        remoteResponseDtoLocalMappingImp: RemoteResponseDtoLocalMappingImp,
        @IoDispatcher ioDispatcher: CoroutineDispatcher

    ): ListMovieRepositoryImpl = ListMovieRepository(
        apiMovieServices,
        movieLocalManager,
        domainDtoUiMappingImp,
        remoteResponseDtoLocalMappingImp,
        ioDispatcher
    )

    @Provides
    @Singleton
    fun provideDetailMovieRepository(
        apiDetailMovie: ApiDetailMovie,
        detailMovieLocalManager: DetailMovieLocalManager,
        domainDtoUiDetailMovieMappingImp: DomainDtoUiDetailMovieMappingImp,
        remoteResponseDtoLocalDetailMoveMappingImp: RemoteResponseDtoLocalDetailMoveMappingImp

    ): DetailMovieImp = DetailMovieRepository(
        apiDetailMovie,
        detailMovieLocalManager,
        domainDtoUiDetailMovieMappingImp,
        remoteResponseDtoLocalDetailMoveMappingImp
    )

}

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class ListMovieRetrofit

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class DetailMovieRetrofit

