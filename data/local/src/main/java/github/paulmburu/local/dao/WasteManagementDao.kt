package github.paulmburu.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import github.paulmburu.domain.models.WasteType
import github.paulmburu.local.models.ProgressEntity
import github.paulmburu.local.models.WasteTypeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WasteManagementDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWasteTypes(wasteTypes: List<WasteTypeEntity>)

    @Query("SELECT * FROM waste_type_table")
    fun getWasteTypes(): Flow<List<WasteTypeEntity>>

    suspend fun insertProgress(progress: ProgressEntity)

    @Query("SELECT * FROM progress_table")
    fun getProgress(): Flow<List<ProgressEntity>>
}