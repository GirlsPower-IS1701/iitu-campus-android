package kz.iitu.campus.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_login.*
import kz.iitu.campus.MainActivity
import kz.iitu.campus.R
import kz.iitu.campus.repository.AuthRepository
import kz.iitu.campus.services.ApiFactory
import kz.iitu.campus.services.UserSession

class LoginActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProviders.of(
            this, LoginViewModel.AuthFactory(
                AuthRepository(
                    ApiFactory.getApi()
                )
            )
        )[LoginViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        checkForAccessToken()

        initUI()
        initObservers()
    }

    private fun checkForAccessToken() {
        if (UserSession.isLoggedIn(this))
            goToMain()
    }

    private fun initObservers() {
        viewModel.accessLiveData.observe(this, Observer {
            UserSession.saveUserDetails(this, it.access, it.refresh)
        })
    }

    private fun initUI() {
        loginBtn.setOnClickListener {
            if (userNameText.text.toString().isNullOrBlank() || passwordText.text.toString()
                    .isNullOrBlank()
            ) {
                Toast.makeText(this, getString(R.string.warning), Toast.LENGTH_LONG)
                return@setOnClickListener
            }
            viewModel
                .login(userNameText.text.toString(), passwordText.text.toString())
            goToMain()
        }
    }

    private fun goToMain() {
        startActivity(Intent(this, MainActivity::class.java))
    }
}