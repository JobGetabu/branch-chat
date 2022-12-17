package ke.co.branch.core.network.models.messages

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MessagesRequest(
    @SerialName("thread_id") val threadId: String, val body: String
)