package com.example.rtireviews.data

import com.example.rtireviews.data.ApiClient.client
import com.example.rtireviews.model.Review
import com.example.rtireviews.model.User
import com.example.rtireviews.model.UserCreation
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post

class ApiRepository {
    val BASE_URL = "http://rtireviews-api-env.eba-wp43m9p3.eu-west-2.elasticbeanstalk.com/"

    suspend fun addNewUser(user: UserCreation): User = client
        .post("${BASE_URL}api/v1/user/add").body()

    suspend fun getUser(username: String): User = client
        .get("${BASE_URL}api/v1/user/email?email=${username}").body()

    suspend fun getAllReviews(): List<Review> = client
        .get("${BASE_URL}api/v1/reviews/")
        .body()

    suspend fun getSingleReview(id: Long = 2): Review = client
        .get("${BASE_URL}api/v1/reviews/${id}")
        .body()
}