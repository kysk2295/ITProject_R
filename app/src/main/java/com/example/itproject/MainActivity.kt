package com.example.itproject

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import de.hdodenhof.circleimageview.CircleImageView

class MainActivity : AppCompatActivity() {

    private lateinit var mainButton : CircleImageView
    private lateinit var fragmentTransaction : FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val permissionListener = object : PermissionListener {

            override fun onPermissionGranted() {
            }

            override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                finish()
            }
        }

        TedPermission.with(applicationContext)
            .setPermissionListener(permissionListener)
            .setPermissions(android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .check()

        mainButton = findViewById(R.id.circleImgview_main)

        mainButton.setOnClickListener {

            fragmentTransaction =supportFragmentManager.beginTransaction()
            fragmentTransaction.add(R.id.framelayout_main, MainFragment()).commit()

            val sf : SharedPreferences = getSharedPreferences("count_fragment", Context.MODE_PRIVATE)
            val sf1 : SharedPreferences = getSharedPreferences("count_mainFragment", Context.MODE_PRIVATE)

            val editor : SharedPreferences.Editor = sf.edit()
            val editor1 : SharedPreferences.Editor = sf1.edit()

            editor.putInt("count", 1)
            editor1.putInt("count", 0)

            editor.apply()
            editor1.apply()
        }
    }

    //뒤로가기를 눌렀을 때 어떤 프래그먼트인지 체크해서 애니메이션을 보여줌

    override fun onBackPressed() {

        val fragment : Fragment? = supportFragmentManager.findFragmentById(R.id.framelayout_main)

        if(fragment != null) {

            val sf : SharedPreferences = getSharedPreferences("count_fragment", Context.MODE_PRIVATE)
            val count : Int = sf.getInt("count", 0)

            if(count == 1) {
                val mainFragment : MainFragment = supportFragmentManager.findFragmentById(R.id.framelayout_main) as MainFragment
                mainFragment.back()
            }

            if(count == 2) {
                val pictureFragment : PictureFragment = supportFragmentManager.findFragmentById(R.id.framelayout_main) as PictureFragment
                pictureFragment.back()
            }

        }

        else {
            super.onBackPressed()
        }

    }

}
