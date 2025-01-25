package com.example.rtireviews.data

import com.example.rtireviews.data.ApiClient.client
import com.example.rtireviews.model.Review
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse

class ApiRepository {
    suspend fun getAllReviews(): List<Review> = client
        .get("http://localhost:9090/reviews/")
        .body()

    suspend fun getSingleReview(id: Long): Review = client
        .get("http://localhost:9090/reviews/${id}")
        .body()
}