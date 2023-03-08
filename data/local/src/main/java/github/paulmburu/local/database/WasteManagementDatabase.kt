package github.paulmburu.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import github.paulmburu.local.dao.ProgressDao
import github.paulmburu.local.dao.WasteManagementDao
import github.paulmburu.local.models.ProgressEntity
import github.paulmburu.local.models.WasteTypeEntity

@Database(entities = [WasteTypeEntity::class,ProgressEntity::class], version = 1, exportSchema = false)
abstract class WasteManagementDatabase : RoomDatabase() {

    abstract val wasteManagementDao: WasteManagementDao
    abstract val progressDao: ProgressDao
}