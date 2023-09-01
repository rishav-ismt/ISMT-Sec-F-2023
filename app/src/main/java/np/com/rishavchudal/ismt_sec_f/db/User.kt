package np.com.rishavchudal.ismt_sec_f.db

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "user")
@Parcelize
data class User(
    @ColumnInfo(name = "full_name") val fullName: String,
    val email: String,
    val password: String
): Parcelable {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
