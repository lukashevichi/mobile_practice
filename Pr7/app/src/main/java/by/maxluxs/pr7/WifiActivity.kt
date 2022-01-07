package by.maxluxs.pr7

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.net.wifi.WifiManager
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class WifiActivity : AppCompatActivity() {

    private val REQUEST_ID_WIFI = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wifi)

        if (checkAndRequestPermissions()) {
            findViewById<TextView>(R.id.wifiText).text =
                if (checkWifiOnAndConnected()) "ON" else "OFF"
        }

    }

    private fun checkAndRequestPermissions(): Boolean {
        val wifi = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_WIFI_STATE)
        val listPermissionsNeeded: MutableList<String> = ArrayList()
        if (wifi != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_WIFI_STATE)
        }
        if (listPermissionsNeeded.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                this,
                listPermissionsNeeded.toTypedArray(),
                REQUEST_ID_WIFI
            )
            return false
        }
        return true
    }

    private fun checkWifiOnAndConnected(): Boolean {
        val wifiMgr = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        return if (wifiMgr.isWifiEnabled) {
            val wifiInfo = wifiMgr.connectionInfo
            wifiInfo.networkId != -1
        } else {
            false
        }
    }

}