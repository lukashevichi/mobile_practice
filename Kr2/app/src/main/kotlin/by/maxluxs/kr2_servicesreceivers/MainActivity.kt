package by.maxluxs.kr2_servicesreceivers

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import by.maxluxs.kr2_servicesreceivers.databinding.ActivityMainBinding

/**
 * 12.Сообщение генерируется при старте сервиса
 * */

class MainActivity : AppCompatActivity() {

    private val receiver = ServiceMessagesReceiver()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val startupServiceIntent = Intent(this, StartupMessageService::class.java)

        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION).apply {
            addAction("by.maxluxs.action.SERVICE_STARTED")
        }
        registerReceiver(receiver, filter)

        binding.startServiceButton.setOnClickListener {
            startService(startupServiceIntent)
        }

        binding.stopServiceButton.setOnClickListener {
            stopService(startupServiceIntent)
        }

    }
}