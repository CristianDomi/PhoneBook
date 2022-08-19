package phonebook

import kotlin.math.floor
import kotlin.math.min
import kotlin.math.sqrt

object Searcher {

    fun linearSearch(nameList: List<String>, directory: List<String>): Int {

        var foundNames = 0

        for (name in nameList) {
            for (directoryItem in directory) {
                val directoryList = directoryItem.split(" ").toMutableList()
                directoryList.removeFirst()
                val directoryName = directoryList.joinToString(" ").trimEnd()
                if (name == directoryName) {
                    foundNames++
                    break
                }
            }
        }

        return foundNames

    }

    fun jumpSearch(nameList: List<String>, directory: List<String>): Int {

        val blockJump = floor(sqrt(directory.size.toDouble())).toInt()

        var found = 0
        var currentIndex = 0
        var startBlock = 0
        var endBlock = startBlock + blockJump
        var nameFound: Boolean

        while (currentIndex <= nameList.lastIndex) {

            nameFound = false

            if (nameList[currentIndex] < getFullNameFromDirectory(directory.first())
                || nameList[currentIndex] > getFullNameFromDirectory(directory.last())) {
                continue
            }

            while (endBlock <= directory.lastIndex) {

                val directoryName = getFullNameFromDirectory(directory[endBlock])

                if (nameList[currentIndex] <= directoryName) {
                    for (i in endBlock downTo startBlock) {
                        if(nameList[currentIndex] == getFullNameFromDirectory(directory[i])) {
                            nameFound = true
                        }
                    }
                    break
                } else if (endBlock == directory.lastIndex) {
                    break
                } else {
                    startBlock = endBlock
                    endBlock = min(endBlock + blockJump, directory.lastIndex)
                }

            }

            if (nameFound) found++

            startBlock = 0
            endBlock = startBlock + blockJump
            currentIndex++

        }

        return found

    }

    fun binarySearch(nameList: List<String>, directory: List<String>): Int {

        var found = 0

        for (name in nameList) {

            val directoryFirst = getFullNameFromDirectory(directory.first())
            val directoryLast = getFullNameFromDirectory(directory.last())

            if (name !in (directoryFirst..directoryLast)) continue

            var left = 0
            var right = directory.lastIndex

            while (left <= right) {

                val middle = (left + right) / 2
                val directoryName = getFullNameFromDirectory(directory[middle])

                if (name == directoryName) {
                    found++
                    break
                } else if (name > directoryName) {
                    left = middle + 1
                } else {
                    right = middle - 1
                }

            }

        }

        return found

    }

    fun hashMapSearch(list: List<String>, directory: HashMap<String, String>): Int {
        var found = 0

        for (item in list) {
            if(directory[item] != null){
                found++
            }
        }

        return found
    }

}