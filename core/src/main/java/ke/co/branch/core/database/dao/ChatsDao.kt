package ke.co.branch.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ke.co.branch.core.database.entities.ChatEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ChatsDao {
    @Insert
    suspend fun insertAll(messages: List<ChatEntity>)

    @Insert
    suspend fun insert(message: ChatEntity)

    @Query("SELECT * FROM chats")
    fun fetchAllMessages(): Flow<List<ChatEntity>>

    @Query("SELECT * FROM chats WHERE threadId=:threadId")
    fun getMessagesByThread(threadId: String): Flow<List<ChatEntity>>

    @Query("DELETE FROM chats")
    suspend fun deleteAllMessages()
}