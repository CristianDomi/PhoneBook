package phonebook

private const val LEXICOGRAPHIC_EQUALS = 0

object Sorter {

    const val BUBBLE_SORT_TIMEOUT = "-1"
    var linearTime = 0L

    fun bubbleSort(list: MutableList<String>): List<String> {

        var start = true
        var index = 0
        var indexLoop = 1
        var changed = false
        val startTime = System.currentTimeMillis()

        bubble@ while (start) {

            while (indexLoop <= list.lastIndex) {
                val endTime = System.currentTimeMillis()

                if (endTime - startTime >= linearTime ){
                    list[0] = BUBBLE_SORT_TIMEOUT
                    break@bubble
                }

                val indexName = getFullNameFromDirectory(list[index])
                val indexLoopName = getFullNameFromDirectory(list[indexLoop])
                val lexicographicOrder = indexName.compareTo(indexLoopName)

                if (lexicographicOrder <= LEXICOGRAPHIC_EQUALS) {
                    index = indexLoop
                } else {
                    val tempValue = list[index]
                    list[index] = list[indexLoop]
                    list[indexLoop] = tempValue
                    index = indexLoop
                    changed = true
                }
                indexLoop++
            }

            if (changed.not()) {
                start = false
            } else {
                changed = false
                index = 0
                indexLoop = 1
            }
        }

        return list

    }

    fun quickSort(list: MutableList<String>): MutableList<String> {

        val pivotFullValue = list.last()
        val pivot = getFullNameFromDirectory(pivotFullValue)
        list.removeLast()

        var leftArray = mutableListOf<String>()
        var rightArray = mutableListOf<String>()

        for (item in list) {
            if (getFullNameFromDirectory(item) >= pivot){
                rightArray.add(item)
            } else {
                leftArray.add(item)
            }
        }

        if (arrayCanDivide(rightArray.size)) {
            rightArray = quickSort(rightArray)
        }

        if (arrayCanDivide(leftArray.size)) {
            leftArray = quickSort(leftArray)
        }

        val returnList = mutableListOf<String>()
        returnList.addAll(leftArray)
        returnList.add(pivotFullValue)
        returnList.addAll(rightArray)

        return returnList

    }

    private fun arrayCanDivide(size: Int): Boolean = size > 1

}