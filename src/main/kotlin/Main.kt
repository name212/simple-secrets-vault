import db.connect
import org.jetbrains.exposed.sql.transactions.transaction
import vault.Labels

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")

    connect("/tmp/z-connect.db")

    val l = Labels()
    transaction {
        var added = l.add("aaaaaaaa")
        added = added && l.add("BBBBBd")
        added = added && l.add("CCCCCC")
        if (added) {
            commit()
        }

        println(added)
    }

    l.remove(listOf("aaaaaaaa", "CCCCCC"))

    Thread.sleep(10000)
}