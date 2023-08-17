package com.example.mirestaurante.domain.action.user

import com.example.mirestaurante.domain.model.user.LoginUser
import com.example.mirestaurante.domain.repository.UserRepository
import com.example.mirestaurante.infraestructure.remote.user.UserResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class LoginTest {
    private lateinit var login: Login
    private val repository = mockk<UserRepository>()
    private var result: Response<UserResponse>? = null

    @Before
    fun setup() {
        login = Login(repository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `should return a success response when user login`() = runTest() {
        setupLogin()

        whenLogin()

        thenLoginIsSuccessful()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `should return a error response when user login fail`() = runTest() {
        setupFailLogin()

        whenLogin()

        thenLoginFails()
    }

    private suspend fun setupLogin() {
        coEvery { repository.login(LOGIN_USER) } returns RESPONSE
    }

    private suspend fun setupFailLogin() {
        ERROR_RESPONSE
        coEvery { repository.login(LOGIN_USER) } returns ERROR_RESPONSE
    }

    private suspend fun whenLogin() {
        result = login(LOGIN_USER)
    }

    private fun thenLoginIsSuccessful() {
        assertEquals(RESPONSE, result)
    }

    private fun thenLoginFails() {
        assertEquals(ERROR_RESPONSE, result)
    }

    private companion object {
        val LOGIN_USER = LoginUser(
            "xx@mail.com",
            "pass123",
            true
        )

        val RESPONSE: Response<UserResponse>? = Response.success(
            UserResponse(
                200,
                "success",
                emptyList()
            )
        )

        val ERROR_RESPONSE: Response<UserResponse>? = Response.error(
            400,
            "Error".toResponseBody()
        )
    }
}