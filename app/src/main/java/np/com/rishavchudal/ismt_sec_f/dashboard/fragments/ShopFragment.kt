package np.com.rishavchudal.ismt_sec_f.dashboard.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import np.com.rishavchudal.ismt_sec_f.R
import np.com.rishavchudal.ismt_sec_f.dashboard.AddOrUpdateItemActivity
import np.com.rishavchudal.ismt_sec_f.databinding.FragmentShopBinding

class ShopFragment : Fragment() {
    private lateinit var shopBinding: FragmentShopBinding

    private val startAddOrUpdateActivityForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == AddOrUpdateItemActivity.RESULT_CODE_COMPLETE) {
            //TODO do nothing
        } else {
            //TODO Do nothing
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        shopBinding = FragmentShopBinding.inflate(layoutInflater, container, false)
        setUpViews()
        return shopBinding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = ShopFragment()
    }

    private fun setUpViews() {
        setUpFloatingActionButton()
        setUpRecyclerView()
    }

    private fun setUpFloatingActionButton() {
        shopBinding.fabAdd.setOnClickListener {
            val intent = Intent(requireActivity(), AddOrUpdateItemActivity::class.java)
            startAddOrUpdateActivityForResult.launch(intent)
        }
    }

    private fun setUpRecyclerView() {
        //TODO RecyclerView
    }
}