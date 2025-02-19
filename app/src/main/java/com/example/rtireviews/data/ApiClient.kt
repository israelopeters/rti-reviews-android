package com.example.rtireviews.data

import com.example.rtireviews.service.UserViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BasicAuthCredentials
import io.ktor.client.plugins.auth.providers.basic
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.jackson.jackson

object ApiClient {
    private val apiRepository = ApiRepository()
    private val userViewModel = UserViewModel(apiRepository)

    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            jackson()
        }
        install(Auth) {
            basic {
                credentials {
                    BasicAuthCredentials(
                        username = userViewModel.email,
                        password = userViewModel.password
                    )
                }
                realm = "Access to the 'api/v1/' path"

                sendWithoutRequest { request ->
                    true
                }
            }
        }
    }
}