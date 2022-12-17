package ke.co.branch.core.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import ke.co.branch.core.network.models.Chat
import ke.co.branch.core.utils.formatDate

@Entity( tableName = "messages" )
data class MessageEntity(
    @PrimaryKey
    val id: Int? = null,
    val threadId: Int?,
    val userId : String?,
    val body: String?,
    val timeStamp: String?,
    val agentId: String?
)

fun MessageEntity.toChat(): Chat = Chat(
    id = id!!,
    userId = userId,
    latestMessage = body,
    timeStamp = formatDate(
        timeStamp,
        "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
        "E ,MM yyyy HH:mm a"
    ),
    oTimeStamp = timeStamp,
    agentId = agentId,
    threadId = threadId
)