package github.paulmburu.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "waste_type_table")
class WasteTypeEntity(
    @PrimaryKey
    var id: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "category")
    val category: String,

    @ColumnInfo(name = "points")
    val points: Int,

    @ColumnInfo(name = "image_url")
    val image_url: String,
)

@Entity(tableName = "progress_table")
class ProgressEntity(
    @PrimaryKey
    var id: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "category")
    val category: String,

    @ColumnInfo(name = "points")
    val points: Int,

    @ColumnInfo(name = "image_url")
    val image_url: String,
)