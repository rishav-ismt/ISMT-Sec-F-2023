package np.com.rishavchudal.ismt_sec_f.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import np.com.rishavchudal.ismt_sec_f.AppConstants
import np.com.rishavchudal.ismt_sec_f.R
import np.com.rishavchudal.ismt_sec_f.databinding.ActivityDetailViewBinding
import np.com.rishavchudal.ismt_sec_f.db.Product

class DetailViewActivity : AppCompatActivity() {
    private lateinit var detailViewBinding: ActivityDetailViewBinding
    private var receivedProduct: Product? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailViewBinding = ActivityDetailViewBinding.inflate(layoutInflater)
        setContentView(detailViewBinding.root)

        receivedProduct = intent.getParcelableExtra(AppConstants.KEY_PRODUCT)
        receivedProduct?.apply {
            populateDataToTheViews()
        }

        setUpButtons()
    }

    private fun populateDataToTheViews() {
        detailViewBinding.productTitle.text = receivedProduct!!.title
        detailViewBinding.productPrice.text = receivedProduct!!.price
        detailViewBinding.productDescription.text = receivedProduct!!.description
    }

    private fun setUpButtons() {
        setUpBackButton()
        setUpEditButton()
        setUpDeleteButton()
        setUpShareButton()
    }

    private fun setUpBackButton() {
        //TODO
    }

    private fun setUpEditButton() {
        //TODO
    }

    private fun setUpDeleteButton() {
        //TODO
    }

    private fun setUpShareButton() {
        //TODO
    }
}