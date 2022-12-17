package ke.co.branch.core.network.models.login

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestModel(
    val username: String, val password: String
)