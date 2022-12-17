package ke.co.branch.core.api

import com.google.common.truth.Truth
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import ke.co.branch.core.network.models.login.LoginRequestModel
import ke.co.branch.core.network.models.messages.MessagesRequest
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit

class ApiServiceTest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: ApiService

    private val client = OkHttpClient.Builder().build()
    private val kotlinJson = Json {
        ignoreUnknownKeys = true
    }

    val sampleLoginResponse = """
    {
      "auth_token": "H7r11xolkz32cJ8Male8cw"
    }
    """.trimIndent()

    val sampleMessagesResponse = """
    [
      {
        "id": 79,
        "thread_id": 39,
        "user_id": "5480",
        "body": "Hi branch,  Yes I have a problem which I thought it could have been through by now but it's not through. I have not been paid yet but kindly allow me to pay by next week please.",
        "timestamp": "2017-02-03T12:28:31.000Z",
        "agent_id": null
      }
    ]
    """.trimIndent()

    val sampleCreateMessageResponse = """
    {
        "id": 79,
        "thread_id": 39,
        "user_id": "5480",
        "body": "Hi branch",
        "timestamp": "2017-02-03T12:28:31.000Z",
        "agent_id": null
      }
    """.trimIndent()

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()

        apiService = Retrofit.Builder().baseUrl(mockWebServer.url("/")).client(client)
            .addConverterFactory(kotlinJson.asConverterFactory("application/json".toMediaType()))
            .build().create(ApiService::class.java)
    }

    @After
    fun shutdownServer() {
        mockWebServer.shutdown()
    }

    @Test
    fun `should login user with token`() = runTest{

        val response = MockResponse().setBody(sampleLoginResponse).setResponseCode(200)
        mockWebServer.enqueue(response)

        val apiResponse = apiService.loginUser(LoginRequestModel("userName", "password"))

        Truth.assertThat( apiResponse.authToken != null).isTrue()
    }

    @Test
    fun `should return list of messages`() = runTest{

        val response = MockResponse().setBody(sampleMessagesResponse).setResponseCode(200)
        mockWebServer.enqueue(response)

        val apiResponse = apiService.fetchMessages()

        Truth.assertThat( apiResponse.count() >= 0).isTrue()
        Truth.assertThat(apiResponse).isNotEmpty()
    }
}