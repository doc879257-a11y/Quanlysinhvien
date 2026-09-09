fun main() {

    val students = mutableListOf(
        Student("SV001", "Nguyen Van An", 20, "Cong nghe thong tin", 8.5),
        Student("SV002", "Tran Thi Binh", 21, "Ke toan", 7.2),
        Student("SV003", "Le Van Cuong", 22, "Cong nghe thong tin", 9.1),
        Student("SV004", "Pham Thi Dung", 19, "Marketing", 4.5),
        Student("SV005", "Hoang Van Nam", 23, "Cong nghe thong tin", 6.8)
    )
    while (true) {
        println("\n===== QUAN LY SINH VIEN =====")
        println("1. Them sinh vien")
        println("2. Hien thi danh sach")
        println("3. Tim sinh vien theo ID")
        println("4. Tinh GPA trung binh")
        println("5. Tim sinh vien GPA cao nhat")
        println("6. Xoa sinh vien")
        println("7. Dem GPA >= 8.0")
        println("8. Dem GPA < 5.0")
        println("9. GPA trung binh theo nganh")
        println("10. Tim sinh vien lon tuoi nhat")
        println("11. Tim GPA tu 7.0 den 8.5")
        println("12. Tim sinh vien theo nganh")
        println("13. Tim theo mot phan ten")
        println("14. Sap xep GPA giam dan")
       println("15. Hien thi Top 3 GPA")
        println("16. Sap xep theo tuoi")
        println("17. Sap xep theo ten")
        println("0. Thoat")
        println("============================")

        print("Chon: ")
        val choice = readLine()?.toIntOrNull()
        when (choice) {
            1 -> addStudent(students)
            2 -> displayStudents(students)
            3 -> searchStudent(students)
            4 -> calculateAverageGPA(students)
            5 -> findHighestGPA(students)
            6 -> removeStudent(students)
            7 -> countGPA8(students)
            8 -> countGPA5(students)
            9 -> averageGPAByMajor(students)
            10 -> findOldestStudent(students)
            11 -> findStudentsByGPARange(students)
            12 -> findStudentsByMajor(students)
            13 -> searchByName(students)
            14 -> sortByGpaDescending(students)
            15 -> displayTop3(students)
            16 -> sortByAge(students)
            17 -> sortByName(students)
            0 -> {
                println("Da thoat chuong trinh!")
                return
            }
            else -> println("Lua chon khong hop le!")
        }
    }
}


fun addStudent(students: MutableList<Student>) {

    var id = ""

    while (id.isEmpty() || students.any { it.studentId.equals(id, true) }) {
        print("Nhap Student ID: ")
        id = readLine()?.trim() ?: ""

        if (id.isEmpty()) {
            println("ID khong duoc de trong!")
        } else if (students.any { it.studentId.equals(id, true) }) {
            println("ID da ton tai!")
            id = ""
        }
    }

    var name = ""
    while (name.isEmpty()) {
        print("Nhap Full Name: ")
        name = readLine()?.trim() ?: ""

        if (name.isEmpty()) {
            println("Ho ten khong duoc de trong!")
        }
    }
    var age = 0
    while (age <= 0 || age > 150) {
        print("Nhap Age: ")
        val input = readLine()?.toIntOrNull()

        if (input == null) {
            println("Tuoi phai la so nguyen!")
        } else if (input <= 0 || input > 150) {
            println("Tuoi phai tu 1 den 150!")
        } else {
            age = input
        }
    }
    var major = ""
    while (major.isEmpty()) {
        print("Nhap Major: ")
        major = readLine()?.trim() ?: ""

        if (major.isEmpty()) {
            println("Major khong duoc de trong!")
        }
    }

    var gpa = -1.0
    while (gpa < 0.0 || gpa > 10.0) {
        print("Nhap GPA: ")
        val input = readLine()?.toDoubleOrNull()

        if (input == null) {
            println("GPA phai la so!")
        } else if (input < 0.0 || input > 10.0) {
            println("GPA phai tu 0.0 den 10.0!")
        } else {
            gpa = input
        }
    }

    students.add(Student(id, name, age, major, gpa))
    println("Them sinh vien thanh cong!")
}


fun displayStudents(students: List<Student>) {

    if (students.isEmpty()) {
        println("Danh sach sinh vien rong!")
        return
    }

    println("\n===== DANH SACH SINH VIEN =====")

    for (student in students) {
        println(
            "${student.studentId} | " +
                    "${student.fullName} | " +
                    "${student.age} | " +
                    "${student.major} | " +
                    "GPA: ${student.gpa}"
        )
    }
}


fun searchStudent(students: List<Student>) {

    print("Nhap Student ID can tim: ")
    val id = readLine()?.trim() ?: ""

    val student = students.find {
        it.studentId.equals(id, true)
    }

    if (student != null) {
        println("${student.studentId} | ${student.fullName} | ${student.age} | ${student.major} | GPA: ${student.gpa}")
    } else {
        println("Khong tim thay sinh vien!")
    }
}


fun calculateAverageGPA(students: List<Student>) {

    if (students.isEmpty()) {
        println("Danh sach sinh vien rong!")
        return
    }

    var total = 0.0

    for (student in students) {
        total += student.gpa
    }

    println("GPA trung binh: %.2f".format(total / students.size))
}


fun findHighestGPA(students: List<Student>) {

    if (students.isEmpty()) {
        println("Danh sach sinh vien rong!")
        return
    }

    val student = students.maxBy { it.gpa }

    println(
        "Sinh vien GPA cao nhat: " +
                "${student.studentId} | ${student.fullName} | GPA: ${student.gpa}"
    )
}


fun removeStudent(students: MutableList<Student>) {

    print("Nhap Student ID can xoa: ")
    val id = readLine()?.trim() ?: ""

    val student = students.find {
        it.studentId.equals(id, true)
    }

    if (student == null) {
        println("Khong tim thay sinh vien!")
    } else {
        students.remove(student)
        println("Xoa sinh vien thanh cong!")
    }
}


fun countGPA8(students: List<Student>) {

    var count = 0

    for (student in students) {
        if (student.gpa >= 8.0) {
            count++
        }
    }

    println("So sinh vien co GPA >= 8.0: $count")
}


fun countGPA5(students: List<Student>) {

    var count = 0

    for (student in students) {
        if (student.gpa < 5.0) {
            count++
        }
    }

    println("So sinh vien co GPA < 5.0: $count")
}


fun averageGPAByMajor(students: List<Student>) {

    print("Nhap nganh: ")
    val major = readLine()?.trim() ?: ""

    val list = students.filter {
        it.major.equals(major, true)
    }

    if (list.isEmpty()) {
        println("Khong co sinh vien thuoc nganh nay!")
        return
    }

    var total = 0.0

    for (student in list) {
        total += student.gpa
    }

    println("GPA trung binh: %.2f".format(total / list.size))
}


fun findOldestStudent(students: List<Student>) {

    if (students.isEmpty()) {
        println("Danh sach sinh vien rong!")
        return
    }

    val student = students.maxBy { it.age }

    println(
        "Sinh vien lon tuoi nhat: " +
                "${student.studentId} | ${student.fullName} | " +
                "Age: ${student.age} | GPA: ${student.gpa}"
    )
}


fun findStudentsByGPARange(students: List<Student>) {

    val list = students.filter {
        it.gpa >= 7.0 && it.gpa <= 8.5
    }

    if (list.isEmpty()) {
        println("Khong co sinh vien!")
        return
    }

    println("Sinh vien co GPA tu 7.0 den 8.5:")

    for (student in list) {
        println("${student.studentId} | ${student.fullName} | GPA: ${student.gpa}")
    }
}


fun findStudentsByMajor(students: List<Student>) {

    print("Nhap nganh can tim: ")
    val major = readLine()?.trim() ?: ""

    val list = students.filter {
        it.major.equals(major, true)
    }

    if (list.isEmpty()) {
        println("Khong co sinh vien thuoc nganh nay!")
        return
    }

    for (student in list) {
        println(
            "${student.studentId} | " +
                    "${student.fullName} | " +
                    "${student.major} | GPA: ${student.gpa}"
        )
    }
}


fun searchByName(students: List<Student>) {

    print("Nhap mot phan ten can tim: ")
    val keyword = readLine()?.trim() ?: ""

    val list = students.filter {
        it.fullName.contains(keyword, true)
    }

    if (list.isEmpty()) {
        println("Khong tim thay sinh vien!")
        return
    }

    for (student in list) {
        println(
            "${student.studentId} | " +
                    "${student.fullName} | " +
                    "${student.major} | GPA: ${student.gpa}"
        )
    }
}


fun sortByGpaDescending(students: MutableList<Student>) {

    students.sortByDescending { it.gpa }

    println("Da sap xep GPA giam dan!")
    displayStudents(students)
}


fun displayTop3(students: List<Student>) {

    val top3 = students
        .sortedByDescending { it.gpa }
        .take(3)

    println("===== TOP 3 GPA =====")

    for ((index, student) in top3.withIndex()) {
        println(
            "${index + 1}. ${student.studentId} | " +
                    "${student.fullName} | GPA: ${student.gpa}"
        )
    }
}


fun sortByAge(students: MutableList<Student>) {

    students.sortBy { it.age }

    println("Da sap xep theo tuoi!")
    displayStudents(students)
}


fun sortByName(students: MutableList<Student>) {

    students.sortWith(
        compareBy<Student> {
            val name = it.fullName.trim()
            name.substringAfterLast(" ").lowercase()
        }.thenBy {
            val name = it.fullName.trim()
            name.substringBeforeLast(" ").lowercase()
        }.thenBy {
            it.studentId.lowercase()
        }
    )

    println("Da sap xep sinh vien theo ten!")
    displayStudents(students)
}