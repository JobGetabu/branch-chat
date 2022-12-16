package ke.co.branch.core.api.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ke.co.branch.core.api.ApiService
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn( SingletonComponent::class )
object ApiModules {

    @Provides
    @Singleton
    fun providesApiService( retrofit: Retrofit) = retrofit.create( ApiService::class.java )

    //@Provides fun provideLoginRepo(  loginService: LoginService, appDataStore: AppDataStore) : LoginRepo = LoginRepoImple(loginService, appDataStore)
}