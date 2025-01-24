package com.example.rtireviews.data

import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.serialization.jackson.jackson

class RemoteData {
    companion object {
        val client = HttpClient(CIO) {
            install(ContentNegotiation) {
                jackson()
            }
        }

        suspend fun getReviews(): Review {
            val response: HttpResponse = client.get("http://localhost:9090/reviews/2")
            val review: Review = response.body()
            client.close()
            return review
        }
    }
}