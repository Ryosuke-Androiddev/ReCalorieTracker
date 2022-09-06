package com.plcoding.tracker_data.repository

import com.google.common.truth.Truth.assertThat
import com.plcoding.tracker_data.remote.InValidFoodResponse
import com.plcoding.tracker_data.remote.OpenFoodApi
import com.plcoding.tracker_data.remote.validFoodResponse
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

@ExperimentalCoroutinesApi
class TrackerRepositoryImplTest {

    private lateinit var repository: TrackerRepositoryImpl
    private lateinit var api: OpenFoodApi
    private lateinit var mockWebServer: MockWebServer
    private lateinit var okHttpClient: OkHttpClient

    // Unit Test書くときはここまで用意するんや
    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        okHttpClient = OkHttpClient.Builder()
            .writeTimeout(1, TimeUnit.SECONDS)
            .readTimeout(1, TimeUnit.SECONDS)
            .connectTimeout(1, TimeUnit.SECONDS)
            .build()
        api = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(mockWebServer.url("/"))
            .build()
            .create(OpenFoodApi::class.java)
        repository = TrackerRepositoryImpl(
            dao = mockk(relaxed = true),
            api = api
        )
    }

    @After
    fun tearDown() {
        // テストごとにモックサーバーを更新する??
        mockWebServer.shutdown()
    }

    @Test
    fun `Search Food, valid response, return valid result`() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(validFoodResponse)
        )
        // 実際にリクエスト投げた時のデータを使ってる
        val result = repository.searchFood("banana", 1, 40)
        assertThat(result.isSuccess).isTrue()
    }

    @Test
    fun `Search Food, InValid response, return InValid result`() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(403)
                .setBody(validFoodResponse)
        )
        // 実際にリクエスト投げた時のデータを使ってる
        val result = repository.searchFood("banana", 1, 40)
        assertThat(result.isFailure).isTrue()
    }

    @Test
    fun `Search Food, malformed response, return Failure State`() = runTest {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(InValidFoodResponse)
        )
        val result = repository.searchFood("banana", 1, 40)
        assertThat(result.isFailure).isTrue()
    }
}