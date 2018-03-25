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
import android.graphics.Bitmap
import android.net.Uri
import android.widget.Toast
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import papered.startupweekend.Manifest
import java.io.ByteArrayOutputStream
import java.util.ArrayList


class CameraActivity : AppCompatActivity() {

    val resultImage: ImageView by lazy {
        findViewById<ImageView>(R.id.camera_image_result)
    }
    lateinit var imageRef : StorageReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        val intent = intent
        var id = intent.getStringExtra("authId")
        val storage = FirebaseStorage.getInstance()
        val storageRef = storage.reference
        imageRef = storageRef.child("$id.jpg")
        val permissionListener: PermissionListener = object : PermissionListener {
            override fun onPermissionGranted() {

            }

            override fun onPermissionDenied(deniedPermissions: ArrayList<String>?) {

            }
        }

        TedPermission.with(this)
                .setPermissionListener(permissionListener)
                .setRationaleMessage("서비스를 이용하시려면 카메라 권한이 필요해요")
                .setPermissions(android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .check()
        camera_button_shot.setOnClickListener {
            startActivityForResult(Intent(MediaStore.ACTION_IMAGE_CAPTURE), 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val extras = data?.extras

        resultImage.setImageBitmap(extras!!.getParcelable("data"))
        resultImage.isDrawingCacheEnabled= true
        resultImage.buildDrawingCache()

        val bitmap = resultImage.drawingCache

        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos)
        val data = baos.toByteArray()

        val uploadTask = imageRef.putBytes(data)
        uploadTask.addOnSuccessListener {
            var downLoadU: Uri = it.downloadUrl!!
            Toast.makeText(baseContext,"된다",Toast.LENGTH_SHORT).show()
        }
    }

}
