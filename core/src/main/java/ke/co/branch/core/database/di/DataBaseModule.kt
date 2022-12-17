package ke.co.branch.core.database.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ke.co.branch.core.database.AppDatabase
import ke.co.branch.core.database.dao.MessagesDao
import ke.co.branch.core.utils.DataStore
import ke.co.branch.core.utils.DataStoreImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Singleton
    @Provides
    fun provideDataStore(
        @ApplicationContext context: Context
    ): DataStore = DataStoreImpl(context)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "branchchat").build()

    @Singleton
    @Provides
    fun provideMessagesDao(appDatabase: AppDatabase): MessagesDao = appDatabase.provideMessagesDao()
}