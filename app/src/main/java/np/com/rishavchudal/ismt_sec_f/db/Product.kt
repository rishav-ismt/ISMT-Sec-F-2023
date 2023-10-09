package np.com.rishavchudal.ismt_sec_f.db

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "product")
@Parcelize
data class Product(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    val title: String,
    val price: String,
    val description: String,
    val image: String?,
    val location: String?,
    var isPurchased: Boolean? = false
): Parcelable {
    constructor(
        title: String,
        price: String,
        description: String,
        image: String?,
        location: String?,
        isPurchased: Boolean? = false
    ): this(0, title, price, description, image, location, isPurchased)
}
