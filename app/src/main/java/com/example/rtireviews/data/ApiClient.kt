package com.example.rtireviews.data

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.jackson.jackson

object ApiClient {
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            jackson()
        }
    }
}