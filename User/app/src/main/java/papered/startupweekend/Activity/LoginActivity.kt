package papered.startupweekend.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.facebook.FacebookSdk
import kotlinx.android.synthetic.main.activity_login.*
import papered.startupweekend.Presenter.LoginPresenter
import papered.startupweekend.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        FacebookSdk.sdkInitialize(this.applicationContext)
        LoginPresenter(this).apply {
            main_button_fb_login.setOnClickListener {
                facebookLogIn()
            }
        }
    }
}
