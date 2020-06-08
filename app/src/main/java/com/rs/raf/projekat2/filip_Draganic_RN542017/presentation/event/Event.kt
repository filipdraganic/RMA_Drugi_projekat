package com.rs.raf.projekat2.filip_Draganic_RN542017.presentation.event

open class Event<out T>(
    private val content: T,
    // TODO Should be simplified once the itinerary is refactored
    // event should contain state, not these specifics
    // signals if event is tied to permission checking logic
    val permission: Boolean = false) {

    var hasBeenHandled = false
        private set // Allow external read but not write

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content
}
