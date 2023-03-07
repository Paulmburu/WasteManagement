package github.paulmburu.network.api

import com.google.common.truth.Truth
import github.paulmburu.network.base.BaseTest
import github.paulmburu.network.models.WasteTypeDto
import kotlinx.coroutines.runBlocking
import org.junit.Test

class WasteManagementApiTest : BaseTest() {

    @Test
    fun `When fetchWasteTypes is called, the correct result should be parsed`() =
        runBlocking {
            val response = wasteManagementApi.fetchWasteTypes()
            if (response.isSuccessful) {
                Truth.assertThat(response.body()!!).isInstanceOf(WasteTypeDto::class.java)
            }
        }


}