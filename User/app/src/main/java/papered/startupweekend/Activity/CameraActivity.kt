package papered.startupweekend.Activity

import android.content.Context
import android.content.Intent
import android.hardware.Camera
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import papered.startupweekend.R
import android.hardware.Camera.CameraInfo
import android.hardware.Camera.CameraInfo.CAMERA_FACING_BACK
import android.provider.MediaStore
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_camera.*
import android.content.pm.PackageManager
import android.content.pm.PackageInfo
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import papered.startupweekend.Manifest
import java.util.ArrayList


class CameraActivity : AppCompatActivity() {

    val resultImage: ImageView by lazy {
        findViewById<ImageView>(R.id.camera_image_result)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        val permissionListener: PermissionListener = object : PermissionListener {
            override fun onPermissionGranted() {

            }

            override fun onPermissionDenied(deniedPermissions: ArrayList<String>?) {

            }
        }

        TedPermission.with(this)
                .setPermissionListener(permissionListener)
                .setRationaleMessage("서비스를 이용하시려면 카메라 권한이 필요해요")
                .setPermissions(android.Manifest.permission.CAMERA,android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .check()
        camera_button_shot.setOnClickListener {
            startActivityForResult(Intent(MediaStore.ACTION_IMAGE_CAPTURE), 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        resultImage.setImageURI(data?.data)
    }

}
