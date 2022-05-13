package com.easymountain.mhikesvoyage.utils.intentState


/**
 * Not my standard DataState class. I wanted to simplify things for this Hilt example.
 * My standard implementation is much more complex but handles a wide array of use cases.
 * see https://github.com/mitchtabian/Clean-Notes/blob/master/app/src/main/java/com/codingwithmitch/cleannotes/business/domain/state/DataState.kt
 */
sealed class DataState<out R> {
    data class Success<out T>(val data: T, val finished: Boolean = false) : DataState<T>()

    data class Error(val exception: Exception, val cancelled: Boolean = false) : DataState<Nothing>()

    object Loading : DataState<Nothing>()

    object Idle : DataState<Nothing>()


}
