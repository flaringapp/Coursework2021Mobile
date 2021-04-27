package com.flaringapp.coursework2021.app.common

typealias Action = () -> Unit

fun <T> MutableCollection<T>.clearAndAdd(other: Collection<T>) {
    clear()
    addAll(other)
}

fun <T> MutableList<T>.replaceFirst(value: T, predicate: (T) -> Boolean) {
    val index = indexOfFirst(predicate).takeIf { it >= 0 } ?: return
    this[index] = value
}

fun <T> MutableList<T>.removeFirst(predicate: (T) -> Boolean) {
    val index = indexOfFirst(predicate).takeIf { it >= 0 } ?: return
    this.removeAt(index)
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