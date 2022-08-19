package phonebook

object Printer {

    fun startSearch(text: TextEnum) {
        println("Start searching (${getText(text)})...")
    }

    fun time(text: TextEnum, newLine: Boolean = true, milliseconds: Long) {
        val time = Time(milliseconds)
        print("${getText(text)} ${time.getMinutes()} min. ${time.getSeconds()} sec. ${time.getMilliseconds()} ms.")
        if (newLine) println()
    }

    fun foundEntries(found: Int, total: Int){
        print("Found $found / $total entries. ")
    }

    /**
     * @param time A - tiempo de preparacion, B - tiempo de busqueda
     * @param found A - valores encontrados, B - valores totales
     * @param text texto a mostrar en tiempo de preparacion
     */
    fun summary(time: Pair<Long, Long>, found: Pair<Int, Int>, text: TextEnum){
        foundEntries(found.first, found.second)
        time(milliseconds = time.first + time.second, text = TextEnum.TIME)
        time(milliseconds = time.first, text = text)
        time(milliseconds = time.second, text = TextEnum.SEARCHING)
    }

    private fun getText(text: TextEnum): String {
        return when(text) {
            TextEnum.TIME -> "Time Taken:"
            TextEnum.SEARCHING -> "Searching Time:"
            TextEnum.SORTING -> "Sorting Time:"
            TextEnum.CREATING -> "Creating Time:"
            TextEnum.LINEAR_SORT -> "linear search"
            TextEnum.BUBBLE_JUMP -> "bubble sort + jump search"
            TextEnum.QUICK_BINARY -> "quick sort + binary search"
            TextEnum.HASH -> "hash table"
        }
    }

    enum class TextEnum {
        TIME,
        SORTING,
        SEARCHING,
        CREATING,
        LINEAR_SORT,
        BUBBLE_JUMP,
        QUICK_BINARY,
        HASH
    }

}