package np.com.rishavchudal.ismt_sec_f

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import np.com.rishavchudal.ismt_sec_f.dashboard.DashboardActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            //Read from shared Preferences
            val sharedPreferences = this.getSharedPreferences(
                AppConstants.FILE_SHARED_PREF_LOGIN,
                Context.MODE_PRIVATE
            )
            val defaultValue = false
            val isAlreadyLoggedIn = sharedPreferences.getBoolean(
                AppConstants.KEY_IS_LOGGED_IN,
                defaultValue
            )

            val intent = if (isAlreadyLoggedIn) {
                Intent(this, DashboardActivity::class.java)
            } else {
                Intent(this, HomeActivity::class.java)
            }
            startActivity(intent)
            finish()


        }, 3000)
    }
}