package github.paulmburu.network.mappers

import com.google.common.truth.Truth
import github.paulmburu.domain.models.*
import github.paulmburu.network.models.*
import org.junit.Test

class NetworkToDomainTest {

    @Test
    fun `When WasteTypeDto toDomain is called, the Domain representation should be returned`() {
        val wasteTypeDto = WasteTypeDto(id = "id_1", name = "Plastic", category = "RECYCLABLE", 50, image_url = "https://media.istockphoto.com/id/1328523682/vector/crushed-plastic-bottle.jpg?s=612x612&w=0&k=20&c=QFnG19RO05TFYrYT9AVcm6akbdKor3JujbgeJz2H-64=")
        val wasteType = WasteType(id = "id_1", name = "Plastic", category = "RECYCLABLE", 50, image_url = "https://media.istockphoto.com/id/1328523682/vector/crushed-plastic-bottle.jpg?s=612x612&w=0&k=20&c=QFnG19RO05TFYrYT9AVcm6akbdKor3JujbgeJz2H-64=")

        Truth.assertThat(wasteType.id).isEqualTo(wasteTypeDto.toDomain().id)
    }
}