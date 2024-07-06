package by.eapp.mts.logger

import by.eapp.mts.logger.delegate.LoggerDelegate

class BaseLogger(
    tag: String,
    private val loggerDelegate: LoggerDelegate,
    private val messageFormatter: (msg: String) -> String = ::defaultLogFormatter
): Logger {
    private val tag: String = createLogTag(tag)

    override fun i(msg: String) {
        loggerDelegate.i(tag = tag, msg = messageFormatter.invoke(msg))
    }

    override fun d(msg: String) {
        loggerDelegate.d(tag = tag, msg = messageFormatter.invoke(msg))
    }

    override fun w(msg: String) {
        loggerDelegate.w(tag = tag, msg = messageFormatter.invoke(msg))
    }

    override fun e(
        msg: String,
        e: Throwable?
    ) {
        loggerDelegate.e(tag = tag, msg = messageFormatter.invoke(msg), e = e)
    }
}

internal fun defaultLogFormatter(msg: String): String {
    return "[${Thread.currentThread().name}] $msg"
}

fun createLogTag(tagPostfix: String): String {
    return "FinVoice" + if (tagPostfix.isNotBlank()) "($tagPostfix)" else ""
}