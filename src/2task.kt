fun main() {
    val firstList = (1..3).toList()
    val secondList = listOf(5, 6)

    val combinedPairs = mutableListOf<List<Int>>()
    for (x in firstList) {
        for (y in secondList) {
            combinedPairs.add(listOf(x, y))
        }
    }

    println(combinedPairs)
}
