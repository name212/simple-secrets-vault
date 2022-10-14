package vault

import db.Label
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.lang.Exception
class Labels () {
    fun add(n: String, d: String = "", c: String = "#000000") : Boolean {
            val res = Label.insert {
                it[name] = n
                it[description] = d
                it[color] = c
            }

            return res.insertedCount == 1

    }
    fun edit(name: String, description: String, color: String) : Boolean {
        var ret = false
        transaction {
            val q = Label.select{Label.name eq name}
            if (q.count() == 1L){
                val res = Label.update {
                    it[Label.description] = description
                    it[Label.color] = color
                }

                if (res == 1){
                    commit()
                    ret = true
                }
            }
        }

        return ret
    }
    fun remove(names: List<String>): Boolean {
        var ret = false
        transaction {
            val res = Label.deleteWhere { Label.name inList names }
            if (res == names.size){
                commit()
                ret = true
            }
        }

        return ret
    }
}