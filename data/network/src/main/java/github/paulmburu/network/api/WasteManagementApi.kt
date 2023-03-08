package github.paulmburu.network.api

import github.paulmburu.network.models.WasteTypeResponse
import retrofit2.Response
import retrofit2.http.*


interface WasteManagementApi {

    @POST("v3/fa130804-0002-4018-9975-07f15cb651a9")
    @Headers("Accept:application/json")
    suspend fun fetchWasteTypes(
    ): Response<WasteTypeResponse>


}