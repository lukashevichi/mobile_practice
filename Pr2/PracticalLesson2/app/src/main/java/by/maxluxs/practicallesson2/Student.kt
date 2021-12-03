package by.maxluxs.practicallesson2

/**
 * (
 * фамилия,
 * имя,
 * отчество,
 * номер группы,
 * факультет)*/
data class Student(
    val surname: String,
    val name: String,
    val patronymic: String,
    val groupNumber: String,
    val faculty: String
)