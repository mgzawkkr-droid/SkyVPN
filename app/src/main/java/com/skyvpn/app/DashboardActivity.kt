package com.skyvpn.app

import android.os.Bundle
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

        binding.uuidText.text = uuid
        binding.expireText.text = expire
    }
}