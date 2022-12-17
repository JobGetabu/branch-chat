package ke.co.branch.chat.view

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import ke.co.branch.chat.adapter.ChatGroupAdapter
import ke.co.branch.chat.databinding.ActivityMainBinding
import ke.co.branch.chat.viewmodel.MainViewModel
import ke.co.branch.core.utils.Status
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel =  ViewModelProvider(this)[MainViewModel::class.java]

        //init rv
        val adapter = ChatGroupAdapter()
        binding.listChats.setHasFixedSize(true)


        mainViewModel.getMyChats().observe(this){
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

    private fun showSnackBar(message: String?, activity: Activity?) {
        if (null != activity && null != message) {
            Snackbar.make(
                activity.findViewById(android.R.id.content),
                message, Snackbar.LENGTH_SHORT
            ).show()
        }
    }
}