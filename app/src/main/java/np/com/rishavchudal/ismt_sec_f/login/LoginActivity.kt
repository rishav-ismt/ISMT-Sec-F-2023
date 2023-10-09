package np.com.rishavchudal.ismt_sec_f.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import np.com.rishavchudal.ismt_sec_f.AppConstants
import np.com.rishavchudal.ismt_sec_f.dashboard.DashboardActivity
import np.com.rishavchudal.ismt_sec_f.HomeActivity
import np.com.rishavchudal.ismt_sec_f.R
import np.com.rishavchudal.ismt_sec_f.db.TestDatabase
import np.com.rishavchudal.ismt_sec_f.db.User
import java.lang.Exception

class LoginActivity : AppCompatActivity() {
    private val tag = "LoginActivity"
    private lateinit var btnLogin: Button
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var ibBack: ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Log.i(tag, "onCreate...")

        btnLogin = findViewById(R.id.btn_login)
        etEmail = findViewById(R.id.et_email)
        etPassword = findViewById(R.id.et_password)
        ibBack = findViewById(R.id.img_btn_back)

        ibBack.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

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
                //TODO remote authentication
                val testDatabase = TestDatabase.getInstance(applicationContext)
                val userDao = testDatabase.getUserDao()

                Thread {
                    try {
                        val userInDb = userDao.checkValidUser(enteredEmail, enteredPassword)
                        if (userInDb == null) {
                            runOnUiThread {
                                showToast("Email or Password is incorrect...")
                            }
                        } else {
                            runOnUiThread {
                                showToast("LoggedIn Successfully")
                                onSuccessfulLogin(userInDb)
                            }
                        }
                    } catch (exception: Exception) {
                        exception.printStackTrace()
                        runOnUiThread {
                            showToast("Couldn't Login. Please try again..")
                        }
                    }
                }.start()



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

    private fun showToast(message: String) {
        Toast.makeText(
            this,
            message,
            Toast.LENGTH_LONG
        ).show()
    }

    private fun onSuccessfulLogin(user: User) {
        //Writing to SharedPreferences
        val sharedPreferences = this.getSharedPreferences(
            AppConstants.FILE_SHARED_PREF_LOGIN,
            Context.MODE_PRIVATE
        )
        val sharedPrefEditor = sharedPreferences.edit()
        sharedPrefEditor.putBoolean(
            AppConstants.KEY_IS_LOGGED_IN,
            true
        )
        sharedPrefEditor.putString(AppConstants.KEY_LOGGED_IN_EMAIL, user.email)
        sharedPrefEditor.apply()

        val intent = Intent(
            this,
            DashboardActivity::class.java
        )

        intent.putExtra(
            AppConstants.KEY_LOGIN_DATA,
            user
        )
        startActivity(intent)
        finish()
    }
}