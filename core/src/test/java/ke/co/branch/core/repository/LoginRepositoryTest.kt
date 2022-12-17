package ke.co.branch.core.repository

import com.google.common.truth.Truth
import io.mockk.*
import ke.co.branch.core.api.ApiService
import ke.co.branch.core.network.models.login.LoginResponceModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LoginRepositoryTest {
    private lateinit var apiService: ApiService
    private lateinit var loginRepository: LoginRepository

    @Before
    fun setUp() {
        apiService = mockk()

        loginRepository = LoginRepository(apiService)
    }

    @Test
    fun `should return a token on login`() =
        runBlocking {
            coEvery {
                loginRepository.loginUser(
                    "userName",
                    "password"
                )
            } returns LoginResponceModel("")
            val res = loginRepository.loginUser("userName", "password")

            Truth.assertThat(res).isInstanceOf(LoginResponceModel::class.java)

        }

}