package by.maxluxs.kr2_servicesreceivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import by.maxluxs.kr2_servicesreceivers.StartupMessageService.Companion.KEY_MESSAGE

class ServiceMessagesReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        intent.getStringExtra(KEY_MESSAGE)?.let {
            Toast.makeText(
                context, "Message found: $it",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}