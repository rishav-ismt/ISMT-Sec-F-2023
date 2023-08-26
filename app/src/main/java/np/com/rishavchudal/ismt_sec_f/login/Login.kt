package np.com.rishavchudal.ismt_sec_f.login

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Login(
    val email: String?,
    val password: String
): Parcelable
