package ke.co.branch.chat.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import ke.co.branch.chat.adapter.MessagesAdapter
import ke.co.branch.chat.databinding.ActivityChatBinding
import ke.co.branch.chat.viewmodel.MainViewModel
import ke.co.branch.core.network.models.newMessage
import ke.co.branch.core.utils.Status
import ke.co.branch.core.utils.showSnackBar
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        val adapter = MessagesAdapter()
        binding.listChats.setHasFixedSize(true)

        val userName: String? = intent.getStringExtra("USERNAME_EXTRA")
        val agentId: String? = intent.getStringExtra("AGENT_EXTRA")
        val threadId: Int = intent.getIntExtra("THREAD_EXTRA", 0)

        binding.title.text = "$userName"
        binding.backButton.setOnClickListener {
            onBackPressed()
        }

        getChats(threadId.toString(), adapter)

        //send message
        binding.sendButton.setOnClickListener {
            val message = binding.editMessage.text.toString()

            if (message.isEmpty()) {
                showSnackBar("Message empty!", this)
                return@setOnClickListener
            }

            lifecycleScope.launch {
                try {

                    adapter.addItem(newMessage(message))
                    mainViewModel.createMessage(threadId.toString(), message, agentId)
                        .observe(this@ChatActivity) {
                            when (it.status) {
                                Status.LOADING -> {}
                                Status.SUCCESS -> {
                                    Log.d("TAG", "Message sent!")

                                }
                                Status.ERROR -> {}
                            }
                        }

                    binding.editMessage.setText("")

                } catch (e: Exception) {
                    Log.e("TAG", e.message.toString())
                }
            }

        }
    }

    private fun getChats(threadId: String, adapter: MessagesAdapter) {
        mainViewModel.getMyChatsByThreadId(threadId).observe(this) {
            when (it.status) {
                Status.LOADING -> {
                    Log.d("TAG", "Accessing chats")
                }
                Status.SUCCESS -> {
                    Log.d("TAG", it.data?.first().toString())

                    adapter.setItems(it.data?.toMutableList())
                    binding.listChats.adapter = adapter
                }
                Status.ERROR -> {
                    showSnackBar(it.message.toString(), this)
                    Log.e("TAG", it.message.toString())
                }
            }
        }
    }
}