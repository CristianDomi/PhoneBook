package phonebook

class Time(private val timeMilliseconds: Long) {

    companion object {
        fun measureFunction(function: () -> Unit): Long {
            val startTime = System.currentTimeMillis()
            function()
            val endTime = System.currentTimeMillis()
            return endTime - startTime
        }
    }

    fun getMinutes(): Long {
        return if (timeMilliseconds < 60000) 0 else timeMilliseconds / 60000
    }

    fun getSeconds(): Long {
        return if (timeMilliseconds < 1000) 0 else (timeMilliseconds / 1000 ) % 60
    }

    fun getMilliseconds(): Long {
        return timeMilliseconds % 1000
    }

}