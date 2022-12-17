package ke.co.branch.chat.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ke.co.branch.core.database.dao.MessagesDao
import ke.co.branch.core.database.entities.toChat
import ke.co.branch.core.network.models.Chat
import ke.co.branch.core.network.models.Message
import ke.co.branch.core.network.models.messages.MessagesRequest
import ke.co.branch.core.network.models.messages.toMessageEntity
import ke.co.branch.core.network.models.toMessage
import ke.co.branch.core.repository.LoginRepository
import ke.co.branch.core.repository.MessagesRepository
import ke.co.branch.core.utils.DataStore
import ke.co.branch.core.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val messagesRepository: MessagesRepository,
    private val messagesDao: MessagesDao,
    private val dataStore: DataStore
) : ViewModel() {

    fun getMyChats() = liveData(Dispatchers.IO) {
        try {
            getMessages()

            getChatsUpdates().collectLatest {
                emit(Resource.success(data = it))
            }

        }catch (e: Exception) {
            emit(Resource.error(message = "Error getting message!", data = null))
        }
    }

    fun getMyChatsByThreadId(threadId: String) = liveData(Dispatchers.IO) {
        try {
            getMessagesByThreadId(threadId).collectLatest {
                emit(Resource.success(data = it))
            }

        }catch (e: Exception) {
            emit(Resource.error(message = "Error getting message!", data = null))
        }
    }

    private suspend fun getMessages() = withContext(Dispatchers.IO)  {
        try {
            val messages = messagesRepository.getMessages()

            if(messages.isNotEmpty()){
                messagesDao.deleteAllMessages()
                val messageEntities = messages.map { it.toMessageEntity() }.toList()
                messagesDao.insertAll(messageEntities)

                Log.d("MainViewModel", messageEntities.count().toString())
            } else {

            }

        } catch (e: Exception) {
            Log.e("MainViewModel", e.message.toString())
        }
    }

    private fun getChatsUpdates(): Flow<List<Chat>> = flow {
        val messages = messagesDao.fetchAllMessages()
        messages.collect { messageEntities ->
            if (messageEntities.isNotEmpty()) {
                val chats = mutableListOf<Chat>()

                val data = messageEntities.groupBy { it.threadId }
                for (threadId in data.keys) {

                    val sortedMessages =
                        data[threadId]?.sortedWith(compareByDescending { it.timeStamp })
                    chats.add(sortedMessages!![0].toChat())
                }
                val sortedChats = chats.sortedWith(compareByDescending { it.oTimeStamp }).toList()
                emit(sortedChats)
            }
        }
    }.flowOn(Dispatchers.IO)


    fun getMessagesByThreadId(threadId: String): Flow<List<Message>> = flow {
        messagesDao.getMessagesByThread(threadId).collect { entities ->
            val messages = entities.sortedBy { it.timeStamp }.map { it.toMessage() }
            emit(messages)
        }
    }

    suspend fun createMessage(threadId: String, message: String, agentId: String) = liveData(Dispatchers.IO)  {
        try {
            messagesRepository.createMessage(
                MessagesRequest(threadId, message)
            )
            getMessages()

            emit(Resource.success(data = "Message sent"))
        } catch (e: Exception) {
            emit(Resource.error(message = "Message not sent!", data = null))
        }
    }


}