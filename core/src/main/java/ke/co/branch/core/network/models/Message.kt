package ke.co.branch.core.network.models

import ke.co.branch.core.database.entities.MessageEntity
import ke.co.branch.core.utils.formatDate

data class Message(
    val id: Int?,
    val userId: String?,
    val message: String?,
    val agentId: String?,
    val timeStamp: String?
)

fun MessageEntity.toMessage(): Message = Message(
    id = id,
    userId = userId,
    message = body,
    timeStamp = formatDate(timeStamp, "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", "E,MMM yyyy HH:mm"),
    agentId = agentId
)