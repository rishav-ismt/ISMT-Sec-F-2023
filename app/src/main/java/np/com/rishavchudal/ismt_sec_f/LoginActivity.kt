package np.com.rishavchudal.ismt_sec_f

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    private val tag = "LoginActivity"
    private lateinit var btnLogin: Button
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Log.i(tag, "onCreate...")

        btnLogin = findViewById(R.id.btn_login)
        etEmail = findViewById(R.id.et_email)
        etPassword = findViewById(R.id.et_password)

        btnLogin.setOnClickListener {
            Log.i(tag, "Login Button Clicked...")

            val enteredEmail = etEmail.text.toString()
            val enteredPassword = etPassword.text.toString()

            Log.i(tag, "Entered Email: ".plus(enteredEmail))
            Log.i(tag, "Entered Password: ".plus(enteredPassword))

            if (enteredEmail.isBlank() ||
                !Patterns.EMAIL_ADDRESS.matcher(enteredEmail).matches()) {
                Toast.makeText(
                    this,
                    "Enter a valid email",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (enteredPassword.isBlank()) {
                Toast.makeText(
                    this,
                    "Enter a password",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                //TODO local or remote authentication

                val loginData = Login(enteredEmail, enteredPassword)

                val intent = Intent(
                    this,
                    DashboardActivity::class.java
                )
                intent.putExtra(
                    AppConstants.KEY_EMAIL,
                    enteredEmail
                )
                intent.putExtra(
                    AppConstants.KEY_PASSWORD,
                    enteredPassword
                )

                intent.putExtra(
                    AppConstants.KEY_LOGIN_DATA,
                    loginData
                )


                startActivity(intent)

                finish()
            }
        }

        btnLogin.text = "Sign In"
    }

    override fun onStart() {
        super.onStart()
        Log.i(tag, "onStart...")
    }

    override fun onResume() {
        super.onResume()
        Log.i(tag, "onResume...")
    }

    override fun onPause() {
        super.onPause()
        Log.i(tag, "onPause...")
    }

    override fun onStop() {
        super.onStop()
        Log.i(tag, "onStop...")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(tag, "onDestroy...")
    }
}