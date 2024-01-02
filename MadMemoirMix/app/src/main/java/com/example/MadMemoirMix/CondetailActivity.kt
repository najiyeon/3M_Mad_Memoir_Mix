package com.example.MadMemoirMix

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CondetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_condetail)

        val backButton = findViewById<ImageButton>(R.id.icon_back)
        backButton.setOnClickListener {
            finish()
        }

        // intent로부터 전달받은 데이터를 저장할 변수
        var name = intent.getStringExtra("name")
        var phone = intent.getStringExtra("phone")
        var email = intent.getStringExtra("email")
        var organization = intent.getStringExtra("organization")

        // 각 TextView에 데이터를 입력
        val nameTextView = findViewById<TextView>(R.id.person_name)
        val organizationTextView = findViewById<TextView>(R.id.person_institute)
        val phoneTextView = findViewById<TextView>(R.id.person_phone)
        val emailTextView = findViewById<TextView>(R.id.person_email)

        nameTextView.text = name
        organizationTextView.text = organization
        phoneTextView.text = phone
        emailTextView.text = email

        // 전화걸기
        val callButton = findViewById<ImageButton>(R.id.btnCall)
        callButton.setOnClickListener {
            val phoneNumber = phoneTextView.text.toString()
            val callUri = Uri.parse("tel:${phoneNumber}")
            val intentCall = Intent(Intent.ACTION_DIAL, callUri)
            startActivity(intentCall)
        }

        // 메시지 작성
        val messageButton = findViewById<ImageButton>(R.id.btnMessage)
        messageButton.setOnClickListener {
            val phoneNumber = phoneTextView.text.toString()
            val messageUri = Uri.parse("smsto:${phoneNumber}")
            val intentMessage = Intent(Intent.ACTION_SENDTO, messageUri)
            startActivity(intentMessage)
        }

        // bottom navigation
        val bottomNavigationView = findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(R.id.navigationBar)
        bottomNavigationView.selectedItemId = R.id.menu_Contact

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_Contact -> {
                    navigateToPage(ContactActivity::class.java)
                    true
                }
                R.id.menu_Gallery -> {
                    navigateToPage(GalleryActivity::class.java)
                    true
                }
                R.id.menu_MiniGame -> {
                    navigateToPage(MinigameActivity::class.java)
                    true
                }
                else -> false
            }
        }
    }

    private fun navigateToPage(cls: Class<*>) {
        val intent = Intent(this, cls)
        startActivity(intent)
        finish()
    }
}