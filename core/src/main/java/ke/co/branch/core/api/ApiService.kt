package ke.co.branch.core.api

import ke.co.branch.core.network.models.LoginRequestModel
import ke.co.branch.core.network.models.LoginResponceModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    suspend fun loginUser(@Body loginRequestModel: LoginRequestModel): LoginResponceModel
}