package ke.co.branch.core.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponceModel(
    @SerialName("auth_token") val authToken: String?
)