package papered.startupweekend.Activity

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_profile_input.*
import papered.startupweekend.R

class ProfileInputActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_input)
        val dataBase = FirebaseFirestore.getInstance()
//        val dataBase  = FirebaseFirestore.

        val user = HashMap<String, Any>()
        val sp = getSharedPreferences("userKey", Activity.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sp.edit()

        profile_submit.setOnClickListener {
            user.put("email", profile_editText_email.text.toString())
            user.put("name", profile_editText_name.text.toString())
            user.put("phone_number", profile_editText_phoneNumber.text.toString())
            Log.d("ProfileInputActivity", "Email:${profile_editText_email.text}, Name: ${profile_editText_name.text}, phone_number: ${profile_editText_phoneNumber.text}")
            dataBase.collection("user")
                    .add(user)
                    .addOnSuccessListener {
                        Toast.makeText(baseContext, "ID  ${it.id}", Toast.LENGTH_SHORT).show()
                        editor.putString("key", it.id)
                        editor.commit()
                    }
                    .addOnFailureListener {
                        Toast.makeText(baseContext, "언되는데?", Toast.LENGTH_SHORT).show()
                    }
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }
}
