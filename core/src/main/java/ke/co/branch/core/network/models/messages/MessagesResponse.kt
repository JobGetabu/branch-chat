package ke.co.branch.core.network.models.messages

import ke.co.branch.core.database.entities.MessageEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MessagesResponse(
    @SerialName("agent_id") val agentId: String?,
    @SerialName("body") val body: String?,
    @SerialName("id") val id: Int?,
    @SerialName("thread_id") val threadId: Int?,
    @SerialName("timestamp") val timestamp: String?,
    @SerialName("user_id") val userId: String?
)

fun MessagesResponse.toMessageEntity(): MessageEntity = MessageEntity(
    id = id,
    threadId = threadId,
    userId = userId,
    body = body,
    timeStamp = timestamp,
    agentId = agentId
)