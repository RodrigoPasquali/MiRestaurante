package com.example.mirestaurante.domain.action.user

import com.example.mirestaurante.domain.model.user.User
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

class RegisterUserTest {
    private lateinit var register: RegisterUser
    private val repository = mockk<UserRepository>()
    private var result: Response<UserResponse>? = null

    @Before
    fun setup() {
        register = RegisterUser(repository)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `should return a success response when user registers`() = runTest() {
        setupRegistration()

        whenRegister()

        thenRegistrationIsSuccessful()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `should return an error response when user registration fails`() = runTest() {
        setupFailRegistration()

        whenRegister()

        thenRegistrationFails()
    }

    private suspend fun setupRegistration() {
        coEvery { repository.register(USER) } returns RESPONSE
    }

    private suspend fun setupFailRegistration() {
        coEvery { repository.register(USER) } returns ERROR_RESPONSE
    }

    private suspend fun whenRegister() {
        result = register(USER)
    }

    private fun thenRegistrationIsSuccessful() {
        assertEquals(RESPONSE, result)
    }

    private fun thenRegistrationFails() {
        assertEquals(ERROR_RESPONSE, result)
    }

    private companion object {
        val USER = User(
            "goku@mail.com",
            "Goku123",
            "Goku",
            "Kakaroto",
            "Dragon Ball",
            66,
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