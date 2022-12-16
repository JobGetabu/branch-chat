package ke.co.branch.core.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chats")
class ChatEntity(
    @PrimaryKey
    val id: Int? = null,
    val threadId: Int?,
    val userId: String?,
    val body: String?,
    val timeStamp: String?,
    val agentId: String?
)