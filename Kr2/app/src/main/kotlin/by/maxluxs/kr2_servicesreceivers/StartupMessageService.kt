package by.maxluxs.kr2_servicesreceivers

import android.app.Service
import android.content.Intent
import android.os.*
import android.os.Process.THREAD_PRIORITY_BACKGROUND
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class StartupMessageService : Service() {

    companion object {

        const val ACTION_SERVICE_STARTED = "by.maxluxs.action.SERVICE_STARTED"
        const val KEY_MESSAGE = "by.maxluxs.broadcast.MESSAGE"

        object MESSAGE {
            const val STARTED = "Service started"
            const val ENDED = "Service ended"
            const val TIME = "Service works: 10sec"
        }

    }

    private var serviceLooper: Looper? = null
    private var serviceHandler: ServiceHandler? = null

    private var time = 0

    override fun onBind(intent: Intent): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        //Start message!
        sendStartMessage()
        serviceHandler?.obtainMessage()?.also { msg ->
            msg.arg1 = startId
            serviceHandler?.sendMessage(msg)
        }
        return START_STICKY
    }

    override fun onCreate() {
        HandlerThread("ServiceStartArguments", THREAD_PRIORITY_BACKGROUND).apply {
            start()
            serviceLooper = looper
            serviceHandler = ServiceHandler(looper)
        }
    }

    override fun onDestroy() {
        sendStopMessage()
    }

    private inner class ServiceHandler(looper: Looper) : Handler(looper) {

        override fun handleMessage(msg: Message) {
            try {
                Thread.sleep(10000)
                sendTimeMessage()
            } catch (e: InterruptedException) {
                // Restore interrupt status.
                Thread.currentThread().interrupt()
            }
        }
    }

    private fun sendStartMessage() {
        val intent = Intent()
        intent.action = ACTION_SERVICE_STARTED
        intent.putExtra(KEY_MESSAGE, MESSAGE.STARTED)
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES)
        sendBroadcast(intent)
    }

    private fun sendTimeMessage() {
        val intent = Intent()
        intent.action = ACTION_SERVICE_STARTED
        intent.putExtra(KEY_MESSAGE, MESSAGE.TIME)
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES)
        sendBroadcast(intent)
    }

    private fun sendStopMessage() {
        val intent = Intent()
        intent.action = ACTION_SERVICE_STARTED
        intent.putExtra(KEY_MESSAGE, MESSAGE.ENDED)
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES)
        sendBroadcast(intent)
    }

}