package np.com.rishavchudal.ismt_sec_f.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Insert
    fun insertNewUser(user: User)

    @Delete
    fun deleteExistingUser(user: User)

    @Query("Select * from user where email = :emailAddress")
    fun checkUserExist(emailAddress: String): User?

    @Query("Select * from user where email = :userEmail and password = :userPassword")
    fun checkValidUser(
        userEmail: String,
        userPassword: String
    ): User?

    @Query("Select user.email, user.full_name, user.id from user where email = :emailAddress")
    fun getLoggedInUserInformation(emailAddress: String): User?
}