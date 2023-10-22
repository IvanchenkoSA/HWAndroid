package ru.netology.nmedia.supFun
fun formatShortened(value: Int): String {
    val appendix: String
    val result: Int = when {
        value >= 1000000 -> {
            appendix = "M"
            value / 1000000
        }
        value >= 1000 -> {
            appendix = "K"
            value / 1000
        }
        else -> {
            appendix = ""
            value
        }
    }
    return "$result${if (value in 1100..9999) "." + value.toString()[1] else ""}$appendix"
}