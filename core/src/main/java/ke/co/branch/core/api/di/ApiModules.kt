package ke.co.branch.core.api.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ke.co.branch.core.api.ApiService
import ke.co.branch.core.repository.LoginRepository
import ke.co.branch.core.repository.MessagesRepository
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModules {

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideLoginRepository(apiService: ApiService): LoginRepository =
        LoginRepository(apiService)

    @Provides
    @Singleton
    fun provideMessagesRepository(apiService: ApiService): MessagesRepository =
        MessagesRepository(apiService)
}