package ke.co.branch.core.api

import ke.co.branch.core.network.models.login.LoginRequestModel
import ke.co.branch.core.network.models.login.LoginResponceModel
import ke.co.branch.core.network.models.messages.MessagesRequest
import ke.co.branch.core.network.models.messages.MessagesResponse
import kotlinx.serialization.json.JsonObject
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    suspend fun loginUser(@Body loginRequestModel: LoginRequestModel): LoginResponceModel

    @GET("messages")
    suspend fun fetchMessages(): List<MessagesResponse>


    @POST("messages")
    suspend fun createMessage(@Body request: MessagesRequest): MessagesRequest
}