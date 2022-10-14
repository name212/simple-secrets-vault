package vault

import db.Label
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.lang.Exception
class Labels () {
    fun add(n: String, d: String = "", c: String = "#000000") : Boolean {
        return transaction {
            val res = Label.insert {
                it[name] = n
                it[description] = d
                it[color] = c
            }

            res.insertedCount == 1
        }

    }
    fun edit(name: String, description: String, color: String) : Boolean {
        return transaction {
            var updated = false
            val q = Label.select{Label.name eq name}
            if (q.count() == 1L){
                val res = Label.update {
                    it[Label.description] = description
                    it[Label.color] = color
                }

                updated = res == 1

                if (updated){
                    commit()
                }
            }
            updated
        }

    }
    fun remove(names: List<String>): Boolean {
        return transaction {
            var ret = false
            val res = Label.deleteWhere { Label.name inList names }
            ret = res == names.size
            if (ret){
                commit()
            }
            ret
        }
    }
}