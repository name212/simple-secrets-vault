package vault

class Labels {
    fun add(name: String, description: String = "", color: String = "#000000") {}
    fun edit(name: String, description: String, color: String) {}
    fun remove(name: Array<String>) {}
}