package calculator

val numbers = mutableListOf<Int>()

fun Char.isOperator(): Boolean {
    return this == '+' || this == '-'
}

fun start() {
    val input = readln().split(' ').joinToString("")
    when (input) {
        "" -> start()
        "/exit" -> throw Exception()
        "/help" -> println(
            """The program does the following operations:
            sum(+) -> 1 + 3 = 4
            subtract(-) -> 3 - 2 = 1
            mix operations -> -3 -- 1 +++ 3 -15 = -14
        """.trimIndent()
        )

        else -> fixLine(input)
    }
    start()
}

fun computePlusMinus(operator1: Char, operator2: Char): Char {
    if (operator1 == operator2) {
        return '+'
    } else {
        return '-'
    }
}

fun fixLine(line: String) {
    var awryLine = line
    do {
        var operator: Char? = null
        do {
            var first = awryLine[0]
            val second: Char
            if (1 <= awryLine.lastIndex && awryLine[1].isOperator() && first.isOperator()) {
                second = awryLine[1]
                awryLine = awryLine.drop(2)
                awryLine = computePlusMinus(first, second) + awryLine
                first = awryLine[0]
            } else if (first.isOperator()) {
                operator = first
                awryLine = awryLine.removePrefix(first.toString())
                first = awryLine[0]
            }

        } while (first.digitToIntOrNull() == null)

        val numberPattern = Regex("((-?)|(\\+?))?\\d+")
        val positionNumber = numberPattern.find(awryLine)
        if (positionNumber != null) {
            val number = awryLine.substring(positionNumber.range)
            val numberAndOperator = if (operator == '-') '-' + number else number
            numbers.add(numberAndOperator.toInt())
            awryLine = awryLine.removePrefix(number)
        }
    } while (awryLine.length != 0)
    compute()
}

fun compute() {
    println(numbers.sum())
    numbers.clear()
}

fun getNumbers(input: String): MutableList<Int> {
    val numbers = mutableListOf<Int>()
    input.split(' ').forEach { numbers.add(it.toInt()) }
    return numbers
}

fun main() {
    try {
        start()
    } catch (e: Exception) {
        println("Bye!")
    }
}