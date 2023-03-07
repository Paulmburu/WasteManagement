package github.paulmburu.network.api

import github.paulmburu.network.models.WasteTypeResponse
import retrofit2.Response
import retrofit2.http.*


interface WasteManagementApi {

    @POST("v3/90651c79-10ec-4494-aab4-2b8ea85dcc2d")
    @Headers("Accept:application/json")
    suspend fun fetchWasteTypes(
    ): Response<WasteTypeResponse>


}