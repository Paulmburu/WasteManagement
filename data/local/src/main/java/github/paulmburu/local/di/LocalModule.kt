package github.paulmburu.local.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import github.paulmburu.local.dao.ProgressDao
import github.paulmburu.local.dao.WasteManagementDao
import github.paulmburu.local.database.WasteManagementDatabase

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    fun providesWasteManagementDatabase(@ApplicationContext context: Context): WasteManagementDatabase {
        return Room.databaseBuilder(
            context,
            WasteManagementDatabase::class.java,
            DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()

    }

    @Provides
    fun providesWasteManagementDao(database: WasteManagementDatabase): WasteManagementDao {
        return database.wasteManagementDao
    }

    @Provides
    fun providesProgressDao(database: WasteManagementDatabase): ProgressDao {
        return database.progressDao
    }

    const val DATABASE_NAME = "waste_management_database"

}