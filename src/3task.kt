// Опис трейдера з його іменем та містом
data class Trader(val fullName: String, val homeCity: String)

// Опис транзакції з трейдером, роком, місяцем, сумою та валютою
data class Transaction(val participant: Trader, val eventYear: Int, val eventMonth: Int, val amount: Int, val moneyType: Currency)

// Перелік валют
enum class Currency { UAH, USD, EUR }

fun main() {
    // Трейдери
    val raoul = Trader("Raoul", "Cambridge")
    val mario = Trader("Mario", "Milan")
    val alan = Trader("Alan", "Cambridge")
    val brian = Trader("Brian", "Cambridge")

    // Список транзакцій
    val transactionHistory = listOf(
        Transaction(brian, 2011, 12, 300, Currency.UAH),
        Transaction(raoul, 2012, 10, 1000, Currency.UAH),
        Transaction(raoul, 2011, 11, 400, Currency.USD),
        Transaction(mario, 2012, 9, 710, Currency.UAH),
        Transaction(mario, 2012, 7, 700, Currency.USD),
        Transaction(alan, 2012, 4, 950, Currency.EUR)
    )

    // Фільтрація транзакцій за 2011 рік, сортування та форматування
    val transactionsIn2011 = transactionHistory.filter { it.eventYear == 2011 }
        .sortedBy { it.amount }
        .joinToString(" | ") { "Year: ${it.eventYear}, Amount: ${it.amount} ${it.moneyType}" }
    println(transactionsIn2011 + "\n")

    // Збір унікальних міст
    val cities = transactionHistory.map { it.participant.homeCity }.toSet()
    println("Unique cities: $cities \n")

    // Трейдери з Кембриджа
    val cambridgeResidents = transactionHistory.map { it.participant }
        .filter { it.homeCity == "Cambridge" }
        .distinctBy { it.fullName }
        .sortedBy { it.fullName }
        .joinToString(" | ") { "Trader: ${it.fullName}, City: ${it.homeCity}" }
    println(cambridgeResidents + "\n")

    // Всі імена трейдерів у порядку
    val traderNamesSorted = transactionHistory.map { it.participant.fullName }
        .distinct()
        .sorted()
        .joinToString(", ")
    println("Trader names: $traderNamesSorted \n")

    // Перевірка, чи є трейдери в Мілані
    val isTraderInMilan = transactionHistory.any { it.participant.homeCity == "Milan" }
    println("Is there a trader in Milan? $isTraderInMilan \n")

    // Транзакції трейдерів з Кембриджа
    val transactionsFromCambridge = transactionHistory.filter { it.participant.homeCity == "Cambridge" }
        .joinToString(" | ") { "Trader: ${it.participant.fullName}, Amount: ${it.amount} ${it.moneyType}" }
    println(transactionsFromCambridge + "\n")

    // Найбільша транзакція
    val largestTransaction = transactionHistory.maxByOrNull { it.amount }
    largestTransaction?.let {
        println("Largest Transaction: Trader ${it.participant.fullName}, City: ${it.participant.homeCity}, Amount: ${it.amount} ${it.moneyType}")
    }

    // Групування транзакцій за валютою
    val groupedByCurrency = transactionHistory.groupBy { it.moneyType }
    groupedByCurrency.forEach { (currency, transactions) ->
        println("\nCurrency: $currency")
        transactions.forEach {
            println("Trader: ${it.participant.fullName}, Year: ${it.eventYear}, Amount: ${it.amount}")
        }
    }

    // Сума всіх транзакцій у гривнях
    val totalUAH = transactionHistory.filter { it.moneyType == Currency.UAH }.sumOf { it.amount }
    println("\nTotal in UAH: $totalUAH \n")

    // Сортування транзакцій за роком і місяцем
    val chronologicalTransactions = transactionHistory.sortedWith(compareBy({ it.eventYear }, { it.eventMonth }))
    val transactionDescriptions = chronologicalTransactions.mapIndexed { idx, t ->
        "${idx + 1}. ${t.eventMonth}-${t.eventYear}: ${t.amount} ${t.moneyType}"
    }.joinToString(" -> ")
    println(transactionDescriptions)
}
