package ke.co.branch.chat.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import ke.co.branch.core.repository.LoginRepository
import ke.co.branch.core.utils.DataStore
import ke.co.branch.core.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
    private val dataStore: DataStore
) : ViewModel() {

    fun loginUser(userName: String, password: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            val response = loginRepository.loginUser(userName, password)
            if (response.authToken != null) {
                // store token
                Log.d("LoginViewModel", "${response.authToken}")
                dataStore.saveToken("${response.authToken}")
            }
            emit(Resource.success(data = response))
        } catch (e: java.lang.Exception) {
            emit(
                Resource.error(
                    data = null,
                    message = e.message ?: "Username or password is invalid."
                )
            )
        }
    }

    fun proceedIfLoggedIn() = liveData(Dispatchers.IO) {
        try {
            emit(Resource.loading(data = null))
            val loggedIn = dataStore.getToken().firstOrNull() != null
            if (loggedIn)
                emit(Resource.success(data = dataStore.getToken().firstOrNull()))
            //else emit(Resource.error(data = null, message = "Login to continue!"))

        } catch (e: java.lang.Exception) {
            emit(
                Resource.error(
                    data = null,
                    message = e.message ?: "Username or password is invalid."
                )
            )
        }


    }

}