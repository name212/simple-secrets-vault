package vault

typealias DbId = Int

public interface MasterPasswordSupplier {
    fun get() : String;
}


class Vault (passSupplier: MasterPasswordSupplier) {

    fun addSecret(name: String, vl: String){}
    fun editSecret(id: DbId, newvl: String){}
    fun renameSecret(id: DbId, newvl: String){}

    fun labelSecret(id: DbId, labels: Array<String>){}
    fun unlabelSecret(id: DbId, labels: Array<String>){}
}