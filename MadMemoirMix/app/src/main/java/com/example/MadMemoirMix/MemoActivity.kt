package com.example.MadMemoirMix

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memo)

        val backButton = findViewById<Button>(R.id.icon_back)
        backButton.setOnClickListener {
            navigateToPage(MinigameActivity::class.java)
        }

        val saveButton = findViewById<Button>(R.id.button_save_memo)
        val memoEditText = findViewById<EditText>(R.id.edit_text_memo)

        saveButton.setOnClickListener {
            val memoText = memoEditText.text.toString()
            // Save the memoText along with the date in your preferred way (e.g., SharedPreferences, database)
            // Here, you might want to associate the memo with a specific date.

            // For simplicity, let's assume you're using SharedPreferences for now
            val sharedPreferences = getSharedPreferences("MyMemoPrefs", MODE_PRIVATE)
            val date = intent.getStringExtra("selectedDate") // Get the date from the intent
            sharedPreferences.edit().putString(date, memoText).apply()

            // Return to the calendar page
            finish()
        }
    }

    private fun navigateToPage(cls: Class<*>) {
        val intent = Intent(this, cls)
        startActivity(intent)
        finish()
    }
}