package ke.co.branch.core.repository

import ke.co.branch.core.api.ApiService
import ke.co.branch.core.network.models.LoginRequestModel
import ke.co.branch.core.network.models.LoginResponceModel
import ke.co.branch.core.utils.DataStore
import retrofit2.HttpException
import retrofit2.Response
import javax.inject.Inject



class LoginRepository @Inject constructor(private val apiService: ApiService,
    val dataStore: DataStore
) {
    suspend fun loginUser(userName: String, password: String) = apiService.loginUser(
        LoginRequestModel(userName, password)
    )
}