package com.skyvpn.app

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.skyvpn.app.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val api = "http://54.251.150.41:3000"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginBtn.setOnClickListener {

            val username = binding.username.text.toString()
            val password = binding.password.text.toString()

            login(username, password)
        }
    }

    private fun login(username: String, password: String) {

        CoroutineScope(Dispatchers.IO).launch {

            try {

                val url = URL("$api/login")
                val conn = url.openConnection() as HttpURLConnection

                conn.requestMethod = "POST"
                conn.setRequestProperty(
                    "Content-Type",
                    "application/json"
                )

                conn.doOutput = true

                val body = JSONObject()
                body.put("username", username)
                body.put("password", password)

                conn.outputStream.write(
                    body.toString().toByteArray()
                )

                val result =
                    conn.inputStream.bufferedReader().readText()

                withContext(Dispatchers.Main) {

                    val json = JSONObject(result)

                    if(json.has("token")) {

    val uuid = json.getString("uuid")
    val expire = json.getString("expire")

    Toast.makeText(
        this@MainActivity,
        "Login Success\nUUID: $uuid\nExpire: $expire",
        Toast.LENGTH_LONG
    ).show()

} else {

    Toast.makeText(
        this@MainActivity,
        json.getString("error"),
        Toast.LENGTH_LONG
    ).show()

}
                }

            } catch(e: Exception) {

                withContext(Dispatchers.Main) {

                    Toast.makeText(
                        this@MainActivity,
                        "Api server is unavailable",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}