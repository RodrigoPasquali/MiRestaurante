package com.example.mirestaurante.ui.login

import android.content.Context
import com.example.mirestaurante.domain.action.user.Login
import com.example.mirestaurante.domain.model.user.LoginUser
import com.example.mirestaurante.infraestructure.EncryptedSharedPreferencesManager
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest

class LoginViewModelTest {
    private val sharedPreferences = mockk<EncryptedSharedPreferencesManager>(relaxed = true)
    private val login = mockk<Login>()
    private val context = mockk<Context>(relaxed = true)
    private lateinit var loginViewModel: LoginViewModel

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        loginViewModel = LoginViewModel(sharedPreferences, login)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `when try to get login user it should return successfully saved login user`() = runTest {
        givenLoginUserSaved()

        whenGetLoginUser()

        thenGetUserSuccessfully()
    }

    private fun givenLoginUserSaved() {
        every { sharedPreferences.getLoginUser(context) } returns LOGIN_USER
    }

    private fun whenGetLoginUser() {
        loginViewModel.getLoginUser(context)
    }

    private fun thenGetUserSuccessfully() {
        assertEquals(LOGIN_USER, loginViewModel.loginUser.value)
    }

    private companion object {
        val LOGIN_USER = LoginUser(
            "xx@mail.com",
            "pass123",
            true
        )
    }
}