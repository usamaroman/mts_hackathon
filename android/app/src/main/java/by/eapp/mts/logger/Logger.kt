package by.eapp.mts.logger

interface Logger {
    fun i(msg: String)

    fun d(msg: String)

    fun w(msg: String)

    fun e(
        msg: String,
        e: Throwable? = null
    )
}