package papered.startupweekend.Activity

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import papered.startupweekend.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val dataBase = FirebaseFirestore.getInstance()
        val sp = getSharedPreferences("userKey", Activity.MODE_PRIVATE)
        val key: String? = sp.getString("key", "not_key")
        val docRef : DocumentReference = dataBase.collection("user").document(key!!)
        docRef.get().addOnCompleteListener {
            if(it.result.data != null){
                startActivity(Intent(this,MainActivity::class.java))
                finish()
            }
            else{
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
    }
}
