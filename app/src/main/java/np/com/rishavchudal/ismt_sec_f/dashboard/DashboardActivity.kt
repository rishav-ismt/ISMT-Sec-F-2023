package np.com.rishavchudal.ismt_sec_f.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import np.com.rishavchudal.ismt_sec_f.AppConstants
import np.com.rishavchudal.ismt_sec_f.R
import np.com.rishavchudal.ismt_sec_f.databinding.ActivityDashboardBinding
import np.com.rishavchudal.ismt_sec_f.db.User

class DashboardActivity : AppCompatActivity() {
    private val tag = "DashboardActivity"
    private lateinit var dashboardViewBinding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dashboardViewBinding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(dashboardViewBinding.root)

        val receivedIntent = intent
        val receivedLoginData = receivedIntent
            .getParcelableExtra<User>(AppConstants.KEY_LOGIN_DATA)

        Log.i(tag, "Received Email : ".plus(receivedLoginData?.email))
        Log.i(tag, "Received Password : ".plus(receivedLoginData?.password))


        dashboardViewBinding.bnvDashboard.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    //TODO load home fragment
                    true
                }
                R.id.menu_shop -> {
                    //TODO load shop fragment
                    true
                }
                else -> {
                    //TODO load profile fragment
                    true
                }
            }
        }


    }
}