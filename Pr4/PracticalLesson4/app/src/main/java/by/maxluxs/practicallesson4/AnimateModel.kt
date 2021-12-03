package by.maxluxs.practicallesson4

data class AnimateModel(
    val rotate: Int = 0,
    val group: Group = Group.One,
    val fade: Int = 1
)

enum class Group {
    One,
    Two,
    Together
}