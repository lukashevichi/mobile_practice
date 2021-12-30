package by.maxluxs.pr7

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.maxluxs.pr7.databinding.ActivityBatarryBinding
import com.google.android.material.snackbar.Snackbar

class BatarryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBatarryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBatarryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

}