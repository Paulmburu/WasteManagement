package github.paulmburu.local.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.google.common.truth.Truth
import github.paulmburu.local.database.WasteManagementDatabase
import github.paulmburu.local.models.WasteTypeEntity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class WasteManagementDaoTest {

    private lateinit var database: WasteManagementDatabase
    private lateinit var wasteManagementDao: WasteManagementDao

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            WasteManagementDatabase::class.java
        ).build()

        wasteManagementDao = database.wasteManagementDao
    }

    @Test
    fun insertingWasteType_shouldSave_the_correctData() = runBlocking {
        val wasteType = listOf(
            WasteTypeEntity(
                id = "id_1",
                name = "Plastic",
                category = "RECYCLABLE",
                points = 50,
                image_url = "https://media.istockphoto.com/id/1328523682/vector/crushed-plastic-bottle.jpg?s=612x612&w=0&k=20&c=QFnG19RO05TFYrYT9AVcm6akbdKor3JujbgeJz2H-64="
            )
        )

        wasteManagementDao.insertWasteTypes(wasteType)

        val response = wasteManagementDao.getWasteTypes().first()
        Truth.assertThat(response.size).isGreaterThan(0)
        Truth.assertThat(response.first().id)
            .isEqualTo(wasteType.first().id)

    }

    @After
    fun tearDown() {
        database.close()
    }
}