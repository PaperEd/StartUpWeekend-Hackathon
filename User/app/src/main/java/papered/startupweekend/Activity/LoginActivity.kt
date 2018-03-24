package papered.startupweekend.Activity

import android.content.pm.PackageManager
import android.content.pm.Signature
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import com.facebook.FacebookSdk
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import papered.startupweekend.Presenter.LoginPresenter
import papered.startupweekend.R
import java.security.MessageDigest

class LoginActivity : AppCompatActivity() {

    val firebaseAuth: FirebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    override fun onStart() {
        super.onStart()
        val firebaseUser = firebaseAuth.currentUser
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        getHashKey()
//        val firebaseAuth = FirebaseAuth.getInstance()
        FacebookSdk.sdkInitialize(this.applicationContext)
        LoginPresenter(this).apply {
            login_button_fb_login.setOnClickListener {
                facebookLogIn(firebaseAuth)
//                startActivity()
            }
        }
    }

    fun getHashKey() {
        val info = packageManager.getPackageInfo(this.packageName, PackageManager.GET_SIGNATURES)
        for (signature: Signature in info.signatures) {
            val md = MessageDigest.getInstance("SHA")
            md.update(signature.toByteArray())
            Log.d("HASH KEY ", "key_hash=" + Base64.encodeToString(md.digest(), Base64.DEFAULT))
        }
    }
}
