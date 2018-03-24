package papered.startupweekend.Presenter

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import android.support.annotation.NonNull
import android.support.v4.content.ContextCompat.startActivity
import android.util.Log
import com.google.firebase.internal.FirebaseAppHelper.getToken
import com.facebook.AccessToken
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.*
import papered.startupweekend.Activity.LoginActivity
import papered.startupweekend.Activity.ProfileInputActivity


/**
 * Created by PaperEd on 2018-03-24.
 */
class LoginPresenter(val activity: AppCompatActivity) {

    fun facebookLogIn(auth: FirebaseAuth) {
        Toast.makeText(activity.baseContext, "됐네", Toast.LENGTH_SHORT).show()
        val callbackManager: CallbackManager = CallbackManager.Factory.create()

        LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                Toast.makeText(activity.baseContext, "됐네", Toast.LENGTH_SHORT).show()

                val credential = FacebookAuthProvider.getCredential(result!!.accessToken.toString())
                auth.signInWithCredential(credential).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
//                        auth.currentUser
                        Toast.makeText(activity.baseContext, "성공", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(activity.baseContext, "실패", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onError(error: FacebookException?) {
                Toast.makeText(activity.baseContext, "에러", Toast.LENGTH_SHORT).show()
            }

            override fun onCancel() {
                Toast.makeText(activity.baseContext, "취소", Toast.LENGTH_SHORT).show()
            }
        })

        LoginManager.getInstance().logInWithPublishPermissions(activity, listOf())

        activity.startActivity(Intent(activity.baseContext, ProfileInputActivity::class.java))
    }

}