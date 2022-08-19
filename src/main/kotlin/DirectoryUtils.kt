package phonebook

@JvmName("findAndTimeHash")
fun findAndTime(
    names: List<String>,
    directory: HashMap<String, String>,
    searchFunction: (List<String>, HashMap<String, String>) -> Int
): Pair<Int, Long> {
    var found = 0
    val searchTime = Time.measureFunction {
        found = searchFunction(names, directory)
    }
    return Pair(found, searchTime)
}

@JvmName("findAndTimeList")
fun findAndTime(
    names: List<String>,
    directory: List<String>,
    searchFunction: (List<String>, List<String>) -> Int,
): Pair<Int, Long> {
    var found = 0
    val searchTime = Time.measureFunction {
        found = searchFunction(names, directory)
    }
    return Pair(found, searchTime)
}

fun sortAndTime(
    directory: MutableList<String>,
    sortFunction: (MutableList<String>) -> List<String>
): Pair<List<String>, Long> {
    var list = emptyList<String>()
    val sortTime = Time.measureFunction {
        list = sortFunction(directory)
    }
    return Pair(list, sortTime)
}

fun createAndTime(
    directory: MutableList<String>,
    sortFunction: (MutableList<String>) -> HashMap<String, String>
): Pair<HashMap<String, String>, Long> {
    var hashMap = HashMap<String, String>(0)
    val sortTime = Time.measureFunction {
        hashMap = sortFunction(directory)
    }
    return Pair(hashMap, sortTime)
}

fun getFullNameFromDirectory(string: String): String {
    val directoryArrayName = string.split(" ").toMutableList()
    directoryArrayName.removeFirst()
    return directoryArrayName.joinToString(" ")
}

fun directoryToHashMap(list: List<String>): HashMap<String, String> {
    val hashMap = HashMap<String, String>(list.size)

    for (item in list) {
        hashMap[getFullNameFromDirectory(item)] = getPhoneNumber(item)
    }

    return hashMap
}

fun getPhoneNumber(string: String) = string.split(" ").first()