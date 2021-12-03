package by.maxluxs.practicallesson6

import android.content.Context
import android.os.Parcel
import android.os.ParcelFileDescriptor
import android.util.Log
import java.io.*

class FileOperations(private val context: Context) {

    fun writeParcelToFile(fileName: String, students: List<StudentModel>): Boolean {
        return try {
            val fos = FileOutputStream("${context.filesDir}$fileName")
            val oos = ObjectOutputStream(fos)
            oos.writeObject(students)
            oos.close()
            true
        } catch (e: IOException) {
            e.printStackTrace()
            false
        }
    }

    fun readStudentFromFile(fileName: String): List<StudentModel> {
        return try {
            val fis = FileInputStream("${context.filesDir}$fileName")
            val ois = ObjectInputStream(fis)
            val content = ois.readObject() as? List<StudentModel>
            ois.close()
            content ?: emptyList()
        } catch (e: IOException) {
            e.printStackTrace()
            emptyList()
        }
    }

    fun write(fileName: String, fileContent: String): Boolean {
        return try {
            val filePath = "${context.filesDir}/$fileName.txt"
            val file = File(filePath)
            // If file does not exists, then create it
            if (!file.exists()) {
                file.createNewFile()
            }
            val fileWriter = FileWriter(file.absoluteFile)
            val bufferedWriter = BufferedWriter(fileWriter)
            bufferedWriter.write(fileContent)
            bufferedWriter.close()
            true
        } catch (e: IOException) {
            e.printStackTrace()
            false
        }
    }

    fun read(fileName: String): String? {
        val response: String?
        return try {
            val output = StringBuffer()
            val filePath = "${context.filesDir}/$fileName.txt"
            val file = File(filePath)
            val bufferReader = BufferedReader(FileReader(file))
            var line: String? = bufferReader.readLine()
            while (line != null) {
                line.let { output.append(it + "n") }
                line = bufferReader.readLine()
            }
            response = output.toString()
            response
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

}
