package by.maxluxs.kr2_servicesreceivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import by.maxluxs.kr2_servicesreceivers.StartupMessageService.Companion.KEY_MESSAGE

class ServiceMessagesReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(
            context, "Обнаружено сообщение: " +
                    intent.getStringExtra(KEY_MESSAGE),
            Toast.LENGTH_LONG
        ).show()
    }
}