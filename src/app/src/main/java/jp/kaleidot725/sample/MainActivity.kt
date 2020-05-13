package jp.kaleidot725.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(Dispatchers.IO).launch {
            test()
        }
    }

    private fun test() {
        val database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "database-name"
        ).fallbackToDestructiveMigration().build()

        val userDao = database.userDao()
        val newUser = User(0, Date().time.toString(), Date().time.toString())
        userDao.insert(newUser)
        Log.v("TAG", "after insert ${userDao.getAll()}")

        userDao.deleteAll()
        Log.v("TAG", "after deleteAll ${userDao.getAll()}")
    }
}
