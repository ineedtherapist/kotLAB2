fun main() {
    val values = (1..5).toList()
    val squaredValues = values.map { num -> num * num }
    val totalSumOfSquares = squaredValues.reduce { acc, square -> acc + square }

    println("Квадрати чисел у списку: $squaredValues")
    println("Загальна сума квадратів: $totalSumOfSquares")
}
