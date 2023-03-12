package github.paulmburu.network.util

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest
import java.net.HttpURLConnection

internal class WasteManagementApiRequestDispatcher : Dispatcher() {

    override fun dispatch(request: RecordedRequest): MockResponse {
        println(request.path)
        return when (request.path) {
            "/v3/fa130804-0002-4018-9975-07f15cb651a9" -> {
                MockResponse()
                    .setResponseCode(HttpURLConnection.HTTP_OK)
                    .setBody(MockResponseFileReader("json_responses/waste_type_response.json").content)
            }
            else -> throw
            IllegalArgumentException("Unknown Request Path ${request.path.toString()}")
        }
    }

}