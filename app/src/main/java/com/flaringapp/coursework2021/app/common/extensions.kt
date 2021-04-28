package com.flaringapp.coursework2021.app.common

import android.util.Patterns
import kotlin.math.roundToInt

typealias Action = () -> Unit

fun <T> MutableCollection<T>.clearAndAdd(other: Collection<T>) {
    clear()
    addAll(other)
}

fun <T> MutableList<T>.replaceFirst(value: T, predicate: (T) -> Boolean) {
    val index = indexOfFirst(predicate).takeIf { it >= 0 } ?: return
    this[index] = value
}

fun <T> MutableList<T>.replaceFirstOrAdd(value: T, predicate: (T) -> Boolean) {
    val index = indexOfFirst(predicate).takeIf { it >= 0 }
    if (index == null) { add(value) }
    else this[index] = value
}

fun <T> MutableList<T>.removeFirst(predicate: (T) -> Boolean): Boolean {
    val index = indexOfFirst(predicate).takeIf { it >= 0 } ?: return false
    this.removeAt(index)
    return true
}

inline fun <K, V, M : Map<out K, V>> M.forEachIndexed(action: (index: Int, key: K, value: V) -> Unit) {
    entries.forEachIndexed { index, entry ->
        action(index, entry.key, entry.value)
    }
}

fun <K, V> Map<K, V>.getKey(value: V) =
    entries.firstOrNull { it.value == value }?.key

fun String.takeIfNotEmpty() = takeIf { it.isNotEmpty() }

infix fun String.orIfEmpty(other: String): String {
    return takeIfNotEmpty() ?: other
}

fun String.isCorrectInteger() = isEmpty() || toIntOrNull() != null

fun String.isCorrectFloat() = isEmpty() || toFloatOrNull() != null

fun String.isCorrectDouble() = isEmpty() || toDoubleOrNull() != null

fun Boolean.toInt() = if (this) 1 else 0

fun String.isValidEmail() = Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.toMoney(): Int? = toFloatOrNull()?.let { it * 100 }?.roundToInt()