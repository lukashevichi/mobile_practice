package by.maxluxs.practicallesson2.model

data class Student(
    val surname: String,
    val name: String,
    val patronymic: String,
    val groupNumber: String,
    val faculty: String
) {
    override fun toString(): String {
        return "----------------------------\n" +
                "Student:\n" +
                "Surname: $surname\n" +
                "Name: $name\n" +
                "Patronymic: $patronymic\n" +
                "GroupNumber: $groupNumber\n" +
                "Faculty: $faculty\n" +
                "----------------------------"
    }
}