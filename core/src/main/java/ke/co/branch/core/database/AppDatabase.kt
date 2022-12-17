package ke.co.branch.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ke.co.branch.core.database.dao.MessagesDao
import ke.co.branch.core.database.entities.MessageEntity

@Database(version = 1, entities = [MessageEntity::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun provideMessagesDao(): MessagesDao
}