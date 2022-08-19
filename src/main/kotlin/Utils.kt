package phonebook

import java.io.File

fun getFile(fileName: String): File {
    val fileDir = "${getPath()}$fileName"
    return File(fileDir)
}

fun getPath() = "${System.getProperty("user.dir")}/src/main/resources/"