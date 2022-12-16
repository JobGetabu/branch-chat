package ke.co.branch.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ke.co.branch.core.database.dao.ChatsDao
import ke.co.branch.core.database.entities.ChatEntity

@Database(version = 1, entities = [ChatEntity::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun provideChatsDao(): ChatsDao
}