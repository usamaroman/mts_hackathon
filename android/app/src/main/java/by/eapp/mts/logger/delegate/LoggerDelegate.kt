package by.eapp.mts.logger.delegate

interface LoggerDelegate {
    fun i(
        tag: String,
        msg: String
    )

    fun d(
        tag: String,
        msg: String
    )

    fun w(
        tag: String,
        msg: String
    )

    fun e(
        tag: String,
        msg: String,
        e: Throwable?
    )
}