package np.com.rishavchudal.ismt_sec_f.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import np.com.rishavchudal.ismt_sec_f.HomeActivity
import np.com.rishavchudal.ismt_sec_f.R
import np.com.rishavchudal.ismt_sec_f.databinding.ActivitySignUpBinding
import np.com.rishavchudal.ismt_sec_f.db.TestDatabase
import np.com.rishavchudal.ismt_sec_f.db.User
import java.lang.Exception

class SignUpActivity : AppCompatActivity() {
    private lateinit var signUpBinding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signUpBinding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(signUpBinding.root)

        signUpBinding.imgBtnBack.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

        signUpBinding.btnSignUp.setOnClickListener {
            /**
             * //TODO Validation
             * Check if fields are empty
             * Check if structure of email is correct
             * Check if full name is input
             * Check password and confirm password are matching
             */
            val fullName = signUpBinding.etFullName.text.toString().trim()
            val email = signUpBinding.etEmail.text.toString().trim()
            val password = signUpBinding.etPassword.text.toString().trim()
            val confirmPassword = signUpBinding.etConfirmPassword.text.toString().trim()


            //After Validation
            val testDatabase = TestDatabase.getInstance(applicationContext)
            val userDao = testDatabase.getUserDao()

            Thread {
                try {
                    val userInDb = userDao.checkUserExist(email)
                    if (userInDb == null) {
                        //Insert into database
                        val user = User(fullName, email, password)
                        userDao.insertNewUser(user)
                        runOnUiThread {
                            clearInputFields()
                            showToast("New user added..")
                        }
                    } else {
                        runOnUiThread {
                            showToast("User already exist with this email...")
                        }
                    }
                } catch (exception: Exception) {
                    exception.printStackTrace()
                    runOnUiThread {
                        showToast("Couldn't insert user...")
                    }
                }
            }.start()
        }
    }

    private fun clearInputFields() {
        signUpBinding.etEmail.text.clear()
        signUpBinding.etFullName.text.clear()
        signUpBinding.etPassword.text.clear()
        signUpBinding.etConfirmPassword.text.clear()
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}