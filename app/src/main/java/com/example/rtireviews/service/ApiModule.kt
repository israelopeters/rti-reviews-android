package com.example.rtireviews.service

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BasicAuthCredentials
import io.ktor.client.plugins.auth.providers.basic
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.HttpHeaders
import io.ktor.serialization.jackson.jackson
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    val BASE_URL = "http://rtireviews-api-env.eba-wp43m9p3.eu-west-2.elasticbeanstalk.com/"

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClient(CIO) {
            install(ContentNegotiation) {
                jackson()
            }

            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.HEADERS
                filter { request ->
                    request.url.host.contains(BASE_URL)
                }
                sanitizeHeader { header -> header == HttpHeaders.Authorization }
            }

//            install(Auth) {
//                basic {
//                    credentials {
//                        BasicAuthCredentials(
//                            username = "",
//                            password = ""
//                        )
//                    }
//                    realm = "Access to the 'api/v1/' path"
//
//                    sendWithoutRequest { request ->
//                        true
//                    }
//                }
//            }
        }
    }
}