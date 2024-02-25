package com.coffeetime.ctaccount.app.v1

import com.coffeetime.ctaccount.domain.model.UserRegisterRequest
import com.coffeetime.ctaccount.infrastructure.entity.UserInfo
import com.fasterxml.jackson.databind.ObjectMapper
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

@ExtendWith(MockKExtension::class)
@WebMvcTest(UserController::class)
class UserControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    @MockBean
    private lateinit var userController: UserController
    @MockBean
    private lateinit var db: UserInfo
    private val executor = Executors.newFixedThreadPool(10)
    val userList = listOf(
        UserRegisterRequest(
            nickName = "홍길동",
            socialID = "google@test@gmail.com",
            jobs = listOf("개발자", "디자이너"),
        ),
        UserRegisterRequest(
            nickName = "김철수",
            socialID = "naver@test2@naver.com",
            jobs = listOf("개발자"),
        ),
        UserRegisterRequest(
            nickName = "김영희",
            socialID = "kakao@test3@naver.com",
            jobs = listOf("디자이너"),
        ),
        UserRegisterRequest(
            nickName = "박영수",
            socialID = "google@test4@gmail.com",
            jobs = listOf("회계사", "세무사"),
        ),
        UserRegisterRequest(
            nickName = "이영희",
            socialID = "naver@test5@naver.com",
            jobs = listOf(),
        ),
    )

    @BeforeAll
    fun `test concurrent register request`() {
        mockMvc.perform(post("/api/v1/user/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"nickName\":\"${userList[0].nickName}\",\"socialID\":\"${userList[0].socialID}\",\"jobs\":\"${userList[0].jobs}\"}"))
            .andExpect(status().isOk)
        for (user in userList.slice(1..4)) {
            executor.submit {
                mockMvc.perform(
                    post("/api/v1/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nickName\":\"${user.nickName}\",\"socialID\":\"${user.socialID}\",\"jobs\":\"${user.jobs}\"}"))
                    .andExpect(status().isLocked)
            }
        }
    }

    @Test
    fun findAllUsers() {
        val findResult = mockMvc.perform(get("/api/v1/user"))
            .andExpect(status().isOk)
            .andReturn()
        val userList = ObjectMapper().readValue(findResult.response.contentAsString, Iterable::class.java).toList()
        assertEquals(userList.size, 1)
        assertEquals(userList[0], ObjectMapper().readValue(findResult.response.contentAsString, Iterable::class.java).toList()[0])
    }

    @Test
    fun findUserByID() {
        val findResult = mockMvc.perform(get("/api/v1/user?userId=1"))
            .andExpect(status().isOk)
            .andReturn()
        assertEquals(userList[0], findResult.response.contentAsString)
    }

    @Test
    fun findUserBySocialID() {
        val findResult = mockMvc.perform(get("/api/v1/user?socialID=1"))
            .andExpect(status().isOk)
            .andReturn()
        assertEquals(userList[0], findResult.response.contentAsString)
    }

    @AfterAll
    fun tearDown() {
        executor.shutdown()
        val finished = executor.awaitTermination(10, TimeUnit.SECONDS)
        assert(finished)
    }
}