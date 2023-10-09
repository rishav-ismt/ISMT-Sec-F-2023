package np.com.rishavchudal.ismt_sec_f.dashboard.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import np.com.rishavchudal.ismt_sec_f.AppConstants
import np.com.rishavchudal.ismt_sec_f.R
import np.com.rishavchudal.ismt_sec_f.UiUtility
import np.com.rishavchudal.ismt_sec_f.databinding.FragmentHomeBinding
import np.com.rishavchudal.ismt_sec_f.db.TestDatabase
import np.com.rishavchudal.ismt_sec_f.db.User
import java.lang.Exception

class HomeFragment : Fragment() {
    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }

    private lateinit var homeBinding: FragmentHomeBinding
    private var loggedUser: User? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        homeBinding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getLoggedInUserInformation()
    }

    private fun getLoggedInUserInformation() {
        //fetch logged in user email from sharedPref
        val sharedPreferences = requireActivity().getSharedPreferences(
            AppConstants.FILE_SHARED_PREF_LOGIN,
            Context.MODE_PRIVATE
        )

        val loggedInEmail = sharedPreferences.getString(
            AppConstants.KEY_LOGGED_IN_EMAIL,
            ""
        )

        //fetch all information from db with this email
        val testDatabase = TestDatabase.getInstance(requireActivity().applicationContext)
        val userDao = testDatabase.getUserDao()

        Thread {
            try {
                loggedUser = userDao.getLoggedInUserInformation(loggedInEmail!!)
                if (loggedUser == null) {
                    requireActivity().runOnUiThread {
                        UiUtility.showToast(requireActivity(), "No User found...")
                    }
                } else {
                    //logged user fetch successfully. Now use these data

                }
            } catch (exception: Exception) {
                exception.printStackTrace()
                requireActivity().runOnUiThread {
                    UiUtility.showToast(requireActivity(), "Couldn't get user data. Please try again..")
                }
            }
        }.start()
    }
}