package ke.co.branch.core.repository

import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.mockk
import ke.co.branch.core.api.ApiService
import ke.co.branch.core.network.models.messages.MessagesRequest
import ke.co.branch.core.network.models.messages.MessagesResponse
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class MessageRepositoryTest {
    private lateinit var apiService: ApiService
    private lateinit var messagesRepository: MessagesRepository

    @Before
    fun setUp() {
        apiService = mockk()

        messagesRepository = MessagesRepository(apiService)
    }

    @Test
    fun `should return a list of messages when successful`() =
        runBlocking {
            val messagesResponse = MessagesResponse(
                agentId = "100",
                body = "messages",
                id = 100,
                threadId = 900,
                timestamp = "2017-02-03T12:28:31.000Z",
                userId = "400"
            )

            coEvery {
                messagesRepository.getMessages()
            } returns arrayListOf(messagesResponse)
            val res = messagesRepository.getMessages()

            Truth.assertThat(res).isNotEmpty()
        }


    @Test
    fun `should return a message when sending one successful`() =
        runBlocking {
            val messagesRequest = MessagesRequest(
                threadId = "900",
                body = "messages",
            )

            coEvery {
                messagesRepository.createMessage(messagesRequest)
            } returns messagesRequest
            val res = messagesRepository.createMessage(messagesRequest)

            Truth.assertThat(res.threadId).isEqualTo("900")

        }
}