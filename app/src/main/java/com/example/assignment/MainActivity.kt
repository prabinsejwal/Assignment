package com.example.assignment

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkNotificationPermission()
        val inputAmount: EditText = findViewById(R.id.mainTransferAmount)
        val transferButton: Button = findViewById(R.id.btnTransfer)

        transferButton.setOnClickListener {
            val amount = inputAmount.text.toString()
            transferMoney(amount)
        }
    }

    // Inflate the menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu_item, menu)
        return true
    }

    // Handle menu item selection
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.android -> {
                Toast.makeText(this, "Android selected", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.profile -> {
                Toast.makeText(this, "Profile selected", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.download -> {
                Toast.makeText(this, "Download selected", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.setting -> {
                Toast.makeText(this, "Setting selected", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.exit -> {
                Toast.makeText(this, "Exit selected", Toast.LENGTH_SHORT).show()
                finish() // Close the app
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun checkNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            Toast.makeText(this, "Notification Permission Granted", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Notification Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }

    private fun transferMoney(amount: String) {
        val intent = Intent(this, TransferActivity::class.java)
        intent.putExtra("TransferAmount", amount)
        startActivity(intent)
    }
}
