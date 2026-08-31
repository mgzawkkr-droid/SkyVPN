package com.skyvpn.app

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.skyvpn.app.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val uuid = intent.getStringExtra("uuid")
        val expire = intent.getStringExtra("expire")
        val token = intent.getStringExtra("token")

        binding.uuidText.text = uuid
        binding.expireText.text = expire

        binding.connectBtn.setOnClickListener {

            Toast.makeText(
                this,
                "Getting VPN Config...",
                Toast.LENGTH_SHORT
            ).show()

        }
    }
}