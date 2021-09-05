package by.maxluxs.practicallesson1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import by.maxluxs.practicallesson1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(
                this, R.layout.activity_main)

        listenHelloClick()

    }

    private fun listenHelloClick() {
        binding.button.setOnClickListener {
            if (binding.helloText.alpha == 0f) {
                binding.helloText.animate()
                        .alpha(1f)
                        .duration = 600
            } else {
                binding.helloText.animate()
                        .alpha(0f)
                        .duration = 600
            }
        }
    }

}