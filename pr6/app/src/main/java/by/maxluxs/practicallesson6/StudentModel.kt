package by.maxluxs.practicallesson6

import java.io.Serializable

data class StudentModel(
    val uuid: String,
    val name: String,
    val surname: String,
    val faculty: String
) : Serializable {
    override fun toString(): String {
        return "Студент номер: $uuid\nИмя: $name\nФамилия: $surname\nФакультет: $faculty\n"
    }
}
