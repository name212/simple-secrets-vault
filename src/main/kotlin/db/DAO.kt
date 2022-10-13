package db

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.statements.api.ExposedBlob

object DbConstants {
    const val LABEL_LEN = 32
}

object Label: Table("label") {
    val name: Column<String> = varchar("name", DbConstants.LABEL_LEN).uniqueIndex();
    val description: Column<String> = varchar("description", 256).default("");
    val color: Column<String> = varchar("color", 7).default("#000000");

    override val primaryKey = PrimaryKey(name, name = "PK_Label_Name")
}

object Secret: Table("secret") {
    val id: Column<Int> = integer("id").autoIncrement();
    val name: Column<String> = varchar("name", 64);
    val value: Column<String?> = varchar("value", 512).nullable();
    val created: Column<String> = varchar("created", 24);
    val updated: Column<String?> = varchar("updated", 24).nullable();

    override val primaryKey = PrimaryKey(id, name = "PK_Secret_Id")
}

object File: Table("secret_file") {
    val secretId = reference("secret_id", Secret.id, ReferenceOption.CASCADE, ReferenceOption.CASCADE)
    val name: Column<String> = varchar("name", 256);
    val content: Column<ExposedBlob> = blob("content");
    val mime: Column<String?> = varchar("mime", 64).nullable();

    override val primaryKey = PrimaryKey(secretId, name)
}

object LabeledSecret: Table("secret_labeled") {
    val secretId = reference("secret_id", Secret.id, ReferenceOption.CASCADE, ReferenceOption.CASCADE);
    val label: Column<String> = reference("label", Label.name, ReferenceOption.CASCADE, ReferenceOption.CASCADE);

    override val primaryKey = PrimaryKey(secretId, label)
}