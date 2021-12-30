package by.maxluxs.kr1.view

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import by.maxluxs.kr1.databinding.DialogCreateFlowerBinding
import by.maxluxs.kr1.model.vo.HomeFlower
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*

class CreateFlowerDialog private constructor(context: Context) : BottomSheetDialog(context) {

    private lateinit var binding: DialogCreateFlowerBinding

    var addCallBack: ((flower: HomeFlower) -> Unit)? = null

    companion object {

        @JvmStatic
        fun create(context: Context): CreateFlowerDialog = CreateFlowerDialog(context).apply {
            binding = DialogCreateFlowerBinding.inflate(layoutInflater)
            setContentView(binding.root)
        }

        @JvmStatic
        fun create(context: Context, callback: (HomeFlower) -> Unit): CreateFlowerDialog =
            CreateFlowerDialog(context).apply {
                binding = DialogCreateFlowerBinding.inflate(layoutInflater)
                setContentView(binding.root)
                addCallBack = callback
            }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
        behavior.skipCollapsed = true
        behavior.isHideable = true
        binding.addButton.setOnClickListener {
            val flower = validateNameAndCategory(
                noValidatedCategory = binding.category.text.toString(),
                noValidatedInstructions = binding.instructions.text.toString(),
                noValidatedPhoto = "",
                noValidatedName = binding.name.text.toString(),
            )
            flower?.let {
                addCallBack?.invoke(it)
                dismiss()
            } ?: run {
                showEmptyFieldDialog()
            }
        }
    }

    private fun showEmptyFieldDialog() = Toast.makeText(
        context,
        "Все поля должны быть заполнены",
        Toast.LENGTH_SHORT
    ).show()

    private fun validateNameAndCategory(
        noValidatedCategory: String?,
        noValidatedInstructions: String?,
        noValidatedPhoto: String?,
        noValidatedName: String?,
    ): HomeFlower? =
        noValidatedName?.let { name ->
            noValidatedCategory?.let { category ->
                noValidatedInstructions?.let { instructions ->
                    noValidatedPhoto?.let { photo ->
                        HomeFlower(
                            category = category,
                            instructions = instructions,
                            photo = photo,
                            name = name,
                            uuid = UUID.randomUUID().toString()
                        )
                    }
                }
            }
        }

}