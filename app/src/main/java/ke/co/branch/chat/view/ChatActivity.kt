package ke.co.branch.chat.view

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import ke.co.branch.chat.R
import ke.co.branch.chat.adapter.MessagesAdapter
import ke.co.branch.chat.databinding.ActivityChatBinding
import ke.co.branch.chat.databinding.ActivityMainBinding
import ke.co.branch.chat.viewmodel.MainViewModel
import ke.co.branch.core.network.models.Chat
import ke.co.branch.core.utils.Status
import ke.co.branch.core.utils.showSnackBar

@AndroidEntryPoint
class ChatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel =  ViewModelProvider(this)[MainViewModel::class.java]

        val adapter = MessagesAdapter()
        binding.listChats.setHasFixedSize(true)

        val userName: String? = intent.getStringExtra("USERNAME_EXTRA")
        val threadId: Int = intent.getIntExtra("THREAD_EXTRA", 0)

        binding.title.text = "$userName"
        binding.backButton.setOnClickListener {
            onBackPressed()
        }

        mainViewModel.getMyChatsByThreadId("$threadId").observe(this){
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