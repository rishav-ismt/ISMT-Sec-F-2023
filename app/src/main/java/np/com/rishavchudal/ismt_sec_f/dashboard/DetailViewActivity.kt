package np.com.rishavchudal.ismt_sec_f.dashboard

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import np.com.rishavchudal.ismt_sec_f.AppConstants
import np.com.rishavchudal.ismt_sec_f.BitmapScalar
import np.com.rishavchudal.ismt_sec_f.R
import np.com.rishavchudal.ismt_sec_f.UiUtility
import np.com.rishavchudal.ismt_sec_f.databinding.ActivityDetailViewBinding
import np.com.rishavchudal.ismt_sec_f.db.Product
import np.com.rishavchudal.ismt_sec_f.db.TestDatabase
import java.io.IOException
import java.lang.Exception

class DetailViewActivity : AppCompatActivity() {
    private lateinit var detailViewBinding: ActivityDetailViewBinding
    private var receivedProduct: Product? = null

    companion object {
        const val RESULT_CODE_CANCEL = 2001
        const val RESULT_CODE_REFRESH = 2002
    }

    private val startAddItemActivity = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == AddOrUpdateItemActivity.RESULT_CODE_COMPLETE) {
            val product = it.data?.getParcelableExtra<Product>(AppConstants.KEY_PRODUCT)
            populateDataToTheViews(product)
        } else {
            // TODO do nothing
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailViewBinding = ActivityDetailViewBinding.inflate(layoutInflater)
        setContentView(detailViewBinding.root)

        receivedProduct = intent.getParcelableExtra(AppConstants.KEY_PRODUCT)
        receivedProduct?.apply {
            populateDataToTheViews(this)
        }
        setUpButtons()
    }

    private fun populateDataToTheViews(product: Product?) {
        detailViewBinding.productTitle.text = product?.title
        detailViewBinding.productPrice.text = product?.price
        detailViewBinding.productDescription.text = product?.description
        detailViewBinding.productImage.post {
            var bitmap: Bitmap?
            try {
                bitmap = MediaStore.Images.Media.getBitmap(
                    applicationContext.contentResolver,
                    Uri.parse(product?.image)
                )
                bitmap = BitmapScalar.stretchToFill(
                    bitmap,
                    detailViewBinding.productImage.width,
                    detailViewBinding.productImage.height
                )
                detailViewBinding.productImage.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun setUpButtons() {
        setUpBackButton()
        setUpEditButton()
        setUpDeleteButton()
        setUpShareButton()
    }

    private fun setUpBackButton() {
        detailViewBinding.ibBack.setOnClickListener {
            setResultWithFinish(RESULT_CODE_REFRESH)
        }
    }

    private fun setUpEditButton() {
        detailViewBinding.ibEdit.setOnClickListener {
            val intent = Intent(
                this,
                AddOrUpdateItemActivity::class.java
            ).apply {
                this.putExtra(AppConstants.KEY_PRODUCT, receivedProduct)
            }
            startAddItemActivity.launch(intent)
        }
    }

    private fun setUpDeleteButton() {
        detailViewBinding.ibDelete.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Alert")
                .setMessage("Do you want to delete this product?")
                .setPositiveButton(
                    "Yes",
                    DialogInterface.OnClickListener {
                            dialogInterface,
                            i -> deleteProduct()
                    })
                .setNegativeButton(
                    "No",
                    DialogInterface.OnClickListener {
                            dialogInterface,
                            i ->  dialogInterface.dismiss()

                    })
                .show()
        }
    }

    private fun setUpShareButton() {
        //TODO
    }

    private fun deleteProduct() {
        val testDatabase = TestDatabase.getInstance(this.applicationContext)
        val productDao = testDatabase.getProductDao()

        Thread {
            try {
                receivedProduct?.apply {
                    productDao.deleteProduct(this)
                    runOnUiThread {
                        UiUtility.showToast(
                            this@DetailViewActivity,
                            "Product deleted successfully"
                        )
                        setResultWithFinish(RESULT_CODE_REFRESH)
                    }
                }
            } catch (exception: Exception) {
                exception.printStackTrace()
                runOnUiThread {
                    UiUtility.showToast(
                        this@DetailViewActivity,
                        "Cannot delete product."
                    )
                }
            }
        }.start()
    }

    private fun setResultWithFinish(resultCode: Int) {
        setResult(resultCode)
        finish()
    }
}