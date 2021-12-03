package by.maxluxs.practicallesson6

import android.content.Context

class StudentsRepository constructor(val fileOperations: FileOperations) {
    /**
     * Context constructor
     * @param context - [MainActivity] or [Context]
     * */
    constructor(context: Context) : this(FileOperations(context))

    /**
     * @DB_NAME - file name for students database
     * */
    companion object {
        private const val DB_NAME = "StudentCardDb.txt"
    }

    fun writeStudent(student: StudentModel): Boolean {
        val studentsWithCache = readStudents() + student
        return try {
            fileOperations.writeParcelToFile(
                DB_NAME,
                studentsWithCache
            )
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun writeStudent(students: List<StudentModel>): Boolean {
        return try {
            val studentsWithCache = readStudents() + students
            fileOperations.writeParcelToFile(
                DB_NAME,
                studentsWithCache
            )
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun readStudentsByName(name: String): List<StudentModel> =
        readStudents().filter { it.name == name }

    fun readStudents(): List<StudentModel> {
        return fileOperations.readStudentFromFile(DB_NAME)
    }


}