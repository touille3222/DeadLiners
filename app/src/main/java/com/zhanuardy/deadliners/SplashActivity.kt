package com.zhanuardy.deadliners

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {
    private val splashTimeOut: Long = 3000 // Waktu delay Splash Screen dalam milidetik (3 detik)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Setelah Splash Screen ditampilkan, jalankan kode berikut untuk beralih ke Activity berikutnya
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Menutup SplashActivity agar tidak dapat dikembali
        }, splashTimeOut)
    }
}