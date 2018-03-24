package papered.startupweekend.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_profile_input.*
import papered.startupweekend.R

class ProfileInputActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_input)
        val dataBase  = FirebaseFirestore.getInstance()
        val user = HashMap<String,Any>()

        profile_submit.setOnClickListener {
            user.put("email",profile_editText_email.text.toString())
            user.put("name",profile_editText_name.text.toString())
            user.put("phone_number",profile_editText_phoneNumber.text.toString())

            dataBase.collection("user")
                    .add(user)
                    .addOnSuccessListener {
                        Toast.makeText(baseContext,"ID  " + it.id,Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(baseContext,"언되는데?" ,Toast.LENGTH_SHORT).show()
                    }
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }

    }
}
