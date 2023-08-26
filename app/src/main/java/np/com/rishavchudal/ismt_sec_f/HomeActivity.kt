package np.com.rishavchudal.ismt_sec_f

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import np.com.rishavchudal.ismt_sec_f.databinding.ActivityHomeBinding
import np.com.rishavchudal.ismt_sec_f.login.LoginActivity
import np.com.rishavchudal.ismt_sec_f.signup.SignUpActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var homeActivityBinding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeActivityBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(homeActivityBinding.root)

        homeActivityBinding.btnLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        homeActivityBinding.btnSignUp.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}