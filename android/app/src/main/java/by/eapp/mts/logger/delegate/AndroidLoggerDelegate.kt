package by.eapp.mts.logger.delegate

import android.util.Log

class AndroidLoggerDelegate: LoggerDelegate {
    override fun i(
        tag: String,
        msg: String
    ) {
        Log.i(tag, msg)
    }

    override fun d(
        tag: String,
        msg: String
    ) {
        Log.d(tag, msg)
    }

    override fun w(
        tag: String,
        msg: String
    ) {
        Log.w(tag, msg)
    }

    override fun e(
        tag: String,
        msg: String,
        e: Throwable?
    ) {
        Log.e(tag, msg, e)
    }
}