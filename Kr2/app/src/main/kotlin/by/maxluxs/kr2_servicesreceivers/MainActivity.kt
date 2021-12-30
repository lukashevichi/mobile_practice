package by.maxluxs.kr2_servicesreceivers

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import by.maxluxs.kr2_servicesreceivers.databinding.ActivityMainBinding

/**
 *
 * Познакомиться с назначением сервисов и приемников передач (broadcast)
 * В работе ответить на следующие вопросы.
 * 1. Что такое сервисы. Как они объявляются и используются
 * 2. Привести пример сервиса для проигрывания музыки (записи в файл,
 * подсчете времени и т.п.)
 * 3. Назвать виды сообщений (передач), обрабатываемых в приложениях.
 * Как объявляются приемники передач (сообщений)
 * 4. Привести пример приложения.
 * Создать приложение для обработки сообщений по вариантам.
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
            findViewById<TextView>(R.id.serviceStateText).text = getString(R.string.on)
            startService(startupServiceIntent)
        }

        binding.stopServiceButton.setOnClickListener {
            findViewById<TextView>(R.id.serviceStateText).text = getString(R.string.off)
            stopService(startupServiceIntent)
        }

    }
}