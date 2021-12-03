package by.maxluxs.practicallesson4

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import by.maxluxs.practicallesson4.databinding.ActivityMainBinding
import java.util.*
import android.view.animation.RotateAnimation
import androidx.core.view.isVisible


class MainActivity : AppCompatActivity() {

    private var animation1: ObjectAnimator? = null
    private var animation2: ObjectAnimator? = null

    private var currentImages: MutableList<ImageView> = mutableListOf()

    private var random: Random = Random()
    private var width = 0
    private var height = 0
    private lateinit var set: AnimatorSet

    private val viewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val image1: ImageView = findViewById(R.id.imageView1)
        val image2: ImageView = findViewById(R.id.imageView2)

        width = windowManager.currentWindowMetrics.bounds.width()
        height = windowManager.currentWindowMetrics.bounds.height()

        image1.setOnClickListener {
            selectImage(it as ImageView)
        }

        image2.setOnClickListener {
            selectImage(it as ImageView)
        }

        binding.animateButton.setOnClickListener {
            startAnimation()
        }
        binding.stopAnimateButton.setOnClickListener {
            stopAnimate()
        }

        binding.fadeButton.setOnClickListener {
            binding.fadeLayout.isVisible = binding.fadeLayout.isVisible.not()
            binding.rotateLayout.isVisible = false
            binding.groupLayout.isVisible = false
        }

        binding.groupButton.setOnClickListener {
            binding.groupLayout.isVisible = binding.groupLayout.isVisible.not()
            binding.fadeLayout.isVisible = false
            binding.rotateLayout.isVisible = false
        }

        binding.rotateButton.setOnClickListener {
            binding.rotateLayout.isVisible = binding.rotateLayout.isVisible.not()
            binding.groupLayout.isVisible = false
            binding.fadeLayout.isVisible = false
            currentImages.let { it1 ->
                it1.forEach {
                    rotateClick(it)
                }
            }
        }
    }

    @SuppressLint("ObjectAnimatorBinding")
    private fun createAnimation(imageView: ImageView): AnimatorSet {
        val nextX: Int = random.nextInt(width / 2)
        val nextY: Int = random.nextInt(height / 2)
        animation1 = ObjectAnimator.ofFloat(imageView, "x", nextX.toFloat())
        animation1?.duration = 1400
        animation2 = ObjectAnimator.ofFloat(imageView, "y", nextY.toFloat())
        animation2?.duration = 1400
        val set = AnimatorSet()
        set.playTogether(animation1, animation2)
        return set
    }

    private fun selectImage(imageView: ImageView) {
        currentImages.forEach {
            it.setBackgroundColor(ContextCompat.getColor(this, R.color.transparent))
        }
        currentImages.clear()
        currentImages.add(imageView)
        currentImages.forEach {
            it.background = ContextCompat.getDrawable(this, R.drawable.shape)
        }
    }

    @SuppressLint("SetTextI18n")
    fun startAnimation() {
        binding.animateButton.let { button ->
            currentImages.forEach {
                it.let { image ->
                    binding.textView1.text =
                        "Animate (${
                            viewModel.getAnimateModel()
                                .value.toString()
                        })"
                    set = createAnimation(image)
                    set.addListener(object : AnimatorListenerAdapter() {
                        @SuppressLint("ObjectAnimatorBinding")
                        override fun onAnimationEnd(animation: Animator) {
                            val nextX: Int = random.nextInt(width)
                            val nextY: Int = random.nextInt(height)
                            animation1 = ObjectAnimator.ofFloat(
                                image, "x",
                                image.x,
                                nextX.toFloat()
                            )
                            animation1?.duration = 1400
                            animation2 = ObjectAnimator.ofFloat(
                                image, "y",
                                image.y,
                                nextY.toFloat()
                            )
                            animation2?.duration = 1400
                            set.playTogether(animation1, animation2)
                            set.start()
                        }
                    })
                    set.start()
                }
            }
        }
    }

    private fun fadeClick() {

    }

    private fun groupClick() {

    }

    private fun rotateClick(imageView: ImageView) {
        val rotateAnim = RotateAnimation(
            0.0f, 100.0f,
            RotateAnimation.RELATIVE_TO_SELF, 0.5f,
            RotateAnimation.RELATIVE_TO_SELF, 0.5f
        )
        rotateAnim.duration = 1000
        imageView.startAnimation(rotateAnim)
    }

    private fun stopAnimate() {
        set.pause()
    }

}