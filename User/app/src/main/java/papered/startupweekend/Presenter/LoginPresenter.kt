package papered.startupweekend.Presenter

import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult

/**
 * Created by PaperEd on 2018-03-24.
 */
class LoginPresenter(val activity: AppCompatActivity) {

    fun facebookLogIn() {
        val callbackManager: CallbackManager = CallbackManager.Factory.create()

        LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                Toast.makeText(activity.baseContext, "됐네", Toast.LENGTH_SHORT).show()
            }

            override fun onError(error: FacebookException?) {
                Toast.makeText(activity.baseContext, "에러", Toast.LENGTH_SHORT).show()
            }

            override fun onCancel() {
                Toast.makeText(activity.baseContext, "취소", Toast.LENGTH_SHORT).show()
            }
        })

        LoginManager.getInstance().logInWithPublishPermissions(activity, listOf())
    }
}