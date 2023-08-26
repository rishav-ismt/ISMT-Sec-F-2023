package np.com.rishavchudal.ismt_sec_f.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @ColumnInfo(name = "full_name") val fullName: String,
    val email: String,
    val password: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
