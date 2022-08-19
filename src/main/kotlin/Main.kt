package phonebook

private const val SORTING_TIMES_TIMEOUT = 10

private var fileNames = emptyList<String>()
private var fileDirectory = emptyList<String>()

/**
 *  Leer archivos probado desde SO MacOs, podría fallar al usarse desde Windows.
 *
 *  Cambiar small_find por find y small_directory por directory para ordenar y buscar
 *  en archivos grandes
 *
 */

fun main() {

    fileNames = getFile("small_find.txt").readLines()
    fileDirectory = getFile("small_directory.txt").readLines()

    linearSearch()
    println()

    bubbleSortJumpSearch()
    println()

    quickSortBinarySearch()
    println()

    hashTableSearch()

}

private fun linearSearch() {

    Printer.startSearch(Printer.TextEnum.LINEAR_SORT)
    val (foundNamesLinear, timeLinear) = findAndTime(fileNames, fileDirectory, Searcher::linearSearch)

    Printer.foundEntries(foundNamesLinear, fileNames.size)
    Printer.time(text = Printer.TextEnum.TIME, newLine = true, milliseconds = timeLinear)
    Sorter.linearTime = timeLinear * SORTING_TIMES_TIMEOUT

}

private fun bubbleSortJumpSearch() {

    Printer.startSearch(Printer.TextEnum.BUBBLE_JUMP)
    val (sortedDirectory, timeBubbleJump) = sortAndTime(fileDirectory.toMutableList(), Sorter::bubbleSort)
    val stepOut = sortedDirectory.first() == Sorter.BUBBLE_SORT_TIMEOUT

    val (foundNamesJumpLinear, timeJumpLinearSearch) = findAndTime(fileNames, sortedDirectory, stepOutSearch(stepOut))

    Printer.foundEntries(foundNamesJumpLinear, fileNames.size)
    Printer.time(milliseconds = timeBubbleJump + timeJumpLinearSearch, text = Printer.TextEnum.TIME)
    Printer.time(milliseconds = timeBubbleJump, text = Printer.TextEnum.SORTING, newLine = stepOut.not())
    if(stepOut) println(" - STOPPED, moved to linear search")
    Printer.time(milliseconds = timeJumpLinearSearch, text = Printer.TextEnum.SEARCHING)

}

private fun quickSortBinarySearch() {

    Printer.startSearch(Printer.TextEnum.QUICK_BINARY)
    val (quickDirectory, quickTime) = sortAndTime(fileDirectory.toMutableList(), Sorter::quickSort)
    val (binaryFound, binaryTime) = findAndTime(fileNames, quickDirectory, Searcher::binarySearch)

    Printer.summary(
        time = Pair(quickTime, binaryTime),
        found = Pair(binaryFound, fileNames.size),
        text = Printer.TextEnum.SORTING
    )

}

private fun hashTableSearch() {

    Printer.startSearch(Printer.TextEnum.HASH)
    val (hashMap, creationMapTime) = createAndTime(fileDirectory.toMutableList(), ::directoryToHashMap)
    val (foundHashMap, hashSearchTime) = findAndTime(fileNames, hashMap, Searcher::hashMapSearch)

    Printer.summary(
        time = Pair(creationMapTime, hashSearchTime),
        found = Pair(foundHashMap, fileNames.size),
        text = Printer.TextEnum.CREATING
    )

}

private fun stepOutSearch(stepOut: Boolean) = if (stepOut) Searcher::linearSearch else Searcher::jumpSearch