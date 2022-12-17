package ke.co.branch.core.repository

import ke.co.branch.core.api.ApiService
import ke.co.branch.core.network.models.login.LoginRequestModel
import ke.co.branch.core.network.models.messages.MessagesRequest
import ke.co.branch.core.utils.DataStore
import java.lang.Exception
import javax.inject.Inject

class MessagesRepository @Inject constructor(private val apiService: ApiService,
                                          val dataStore: DataStore
) {
    suspend fun loginUser(userName: String, password: String) = apiService.loginUser(
        LoginRequestModel(userName, password)
    )

    suspend fun getMessages() = apiService.fetchMessages()

    suspend fun createMessage(request: MessagesRequest) = apiService.createMessage(request)



}