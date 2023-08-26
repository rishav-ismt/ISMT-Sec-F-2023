package np.com.rishavchudal.ismt_sec_f

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import np.com.rishavchudal.ismt_sec_f.login.Login

class DashboardActivity : AppCompatActivity() {
    private val tag = "DashboardActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val receivedIntent = intent
        val receivedEmail = receivedIntent
            .getStringExtra(AppConstants.KEY_EMAIL)
        val receivedPassword = receivedIntent
            .getStringExtra(AppConstants.KEY_PASSWORD)

        val receivedLoginData = receivedIntent
            .getParcelableExtra<Login>(AppConstants.KEY_LOGIN_DATA)

        Log.i(tag, "Received Email : ".plus(receivedLoginData?.email))
        Log.i(tag, "Received Password : ".plus(receivedLoginData?.password))


    }
}