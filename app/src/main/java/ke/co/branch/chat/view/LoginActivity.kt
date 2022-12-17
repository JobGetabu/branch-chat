package ke.co.branch.chat.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import ke.co.branch.chat.R
import ke.co.branch.chat.databinding.ActivityLoginBinding
import ke.co.branch.chat.viewmodel.LoginViewModel
import ke.co.branch.core.utils.Status

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private  lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        proceedIfLoggedIn()

        setViews()
    }

    private fun setViews(){

        binding.buttonLogin.setOnClickListener {

            val email = binding.editEmail.text.toString()
            val password = binding.editPassword.text.toString()

            if(email.isEmpty() || password.isEmpty()) {
                showSnackBar("Email or password is required!", this)
                return@setOnClickListener
            }

            loginViewModel.loginUser(email, password).observe(this) {
                when (it.status) {
                    Status.LOADING -> {
                        showSnackBar("Please wait logging you in !", this)
                    }
                    Status.SUCCESS -> {
                        showSnackBar("Success!", this)
                        goToHome()
                    }
                    Status.ERROR -> {
                        showSnackBar(it.message.toString(), this)
                        Log.e("TAG", it.message.toString())
                    }
                }
            }

        }
    }

    private fun proceedIfLoggedIn(){
        loginViewModel.proceedIfLoggedIn().observe(this) {
            when (it.status) {
                Status.LOADING -> {
                    Log.d("TAG", "Accessing saved token")
                }
                Status.SUCCESS -> {
                    Log.d("TAG", "token: ${it.data.toString()}")
                    goToHome()
                }
                Status.ERROR -> {
                    showSnackBar(it.message.toString(), this)
                    Log.e("TAG", it.message.toString())
                }
            }
        }
    }

    private fun goToHome(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK and Intent.FLAG_ACTIVITY_NEW_TASK)
        finish()
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