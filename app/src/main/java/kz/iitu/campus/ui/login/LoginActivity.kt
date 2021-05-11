package kz.iitu.campus.ui.login

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.view.*
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
            if (viewModel.accessLiveData.value == null) {
                Toast.makeText(
                    this,
                    getString(R.string.warning),
                    Toast.LENGTH_LONG
                ).show()
                return@Observer
            }

            UserSession.saveUserDetails(this, it.access, it.refresh)
            goToMain()
        })
        viewModel.errorLiveData.observe(this, Observer {
            if (!it.isNullOrBlank())
                Toast.makeText(
                    this,
                    it,
                    Toast.LENGTH_LONG
                ).show()
        })
        viewModel.loadingState.observe(this, Observer {
            loading_state.isVisible = it
        })
    }

    private fun initUI() {
        loginBtn.setOnClickListener {
            if (userNameText.text.toString().isBlank()) {
                userName.error = getString(R.string.login_id_empty_input_warning)
                return@setOnClickListener
            } else {
                userName.error = null
            }
            if (passwordText.text.toString().isBlank()) {
                pass.error = getString(R.string.login_pass_empty_input_warning)
                return@setOnClickListener
            } else {
                pass.error = null
            }
            viewModel.login(userNameText.text.toString(), passwordText.text.toString())
            goToMain()
        }
    }

    private fun goToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}