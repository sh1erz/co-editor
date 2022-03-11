package com.example.coedit.ui.login


import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.coedit.R
import com.example.coedit.data.model.Note
import com.example.coedit.data.model.User
import com.example.coedit.databinding.ActivityLoginBinding
import com.example.coedit.ui.main.MainActivity
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        startActivity(Intent(this, MainActivity::class.java))

        /*
         val username = binding.username
         val password = binding.password
         val login = binding.login
         val loading = binding.loading

         loginViewModel = ViewModelProvider(this, LoginViewModelFactory())
             .get(LoginViewModel::class.java)

         loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
             val loginState = it ?: return@Observer

             // disable login button unless both username / password is valid
             login.isEnabled = loginState.isDataValid

             if (loginState.usernameError != null) {
                 username.error = getString(loginState.usernameError)
             }
             if (loginState.passwordError != null) {
                 password.error = getString(loginState.passwordError)
             }
         })

         loginViewModel.loginResult.observe(this@LoginActivity, Observer {
             val loginResult = it ?: return@Observer

             loading.visibility = View.GONE
             if (loginResult.error != null) {
                 showLoginFailed(loginResult.error)
             }
             if (loginResult.success != null) {
                 updateUiWithUser(loginResult.success)
             }
             setResult(Activity.RESULT_OK)

             //Complete and destroy login activity once successful
             finish()
         })

         username.afterTextChanged {
             loginViewModel.loginDataChanged(
                 username.text.toString(),
                 password.text.toString()
             )
         }

         password.apply {
             afterTextChanged {
                 loginViewModel.loginDataChanged(
                     username.text.toString(),
                     password.text.toString()
                 )
             }

             setOnEditorActionListener { _, actionId, _ ->
                 when (actionId) {
                     EditorInfo.IME_ACTION_DONE ->
                         loginViewModel.login(
                             username.text.toString(),
                             password.text.toString()
                         )
                 }
                 false
             }

             login.setOnClickListener {
                 loading.visibility = View.VISIBLE
                 loginViewModel.login(username.text.toString(), password.text.toString())
             }
         }*/
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome)
        val displayName = model.displayName
        // TODO : initiate successful logged in experience
        Toast.makeText(
            applicationContext,
            "$welcome $displayName",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }
}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}