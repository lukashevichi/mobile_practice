package by.maxluxs.practicallesson2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StudentAdapter(private val students: MutableList<Student> = mutableListOf()) :
    RecyclerView.Adapter<StudentViewHolder>() {

    fun setData(list: List<Student>) {
        students.clear()
        students.addAll(list)
    }

    fun addStudent(student: Student) {
        students.add(student)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(item)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.fName.text = students[position].name
        holder.lName.text = students[position].surname
        holder.pName.text = students[position].patronymic
        holder.group.text = students[position].groupNumber
        holder.faculty.text = students[position].faculty

    }

    override fun getItemCount(): Int {
        return students.size
    }

}

class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val fName: TextView = itemView.findViewById(R.id.fName)
    val lName: TextView = itemView.findViewById(R.id.lName)
    val pName: TextView = itemView.findViewById(R.id.pName)
    val group: TextView = itemView.findViewById(R.id.groupNumber)
    val faculty: TextView = itemView.findViewById(R.id.faculty)

}
