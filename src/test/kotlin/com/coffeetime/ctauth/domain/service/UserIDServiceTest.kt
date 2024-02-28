package com.coffeetime.ctauth.domain.service

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.whenever

@ExtendWith(MockitoExtension::class)
class UserIDServiceTest {

    @Mock
    private lateinit var userIDService: UserIDService

    @InjectMocks
    private lateinit var userIDServiceImpl: UserIDServiceImpl

    @Test
    fun `test saveOrUpdateUserID`() {
        // given: UserIDInfo 인스턴스
        val userIDInfo = UserIDInfo(
            socialID = "someSocialID",
        )
        val userIDInfo2 = UserIDInfo(
            socialID = "someSocialIDss",
            userID = 123
        )

        // when: saveOrUpdateUserID 호출 시 userIDInfo 반환
        whenever(userIDService.saveOrUpdateUserID(userIDInfo)).thenReturn(userIDInfo2)
        val result = userIDService.saveOrUpdateUserID(userIDInfo)

        // then: 반환된 userIDInfo가 예상과 같은지 확인
        assertEquals(userIDInfo, result)
    }

//    @Test
//    fun `test findBySocialID`() {
//        val socialID = "someSocialID"
//        val userIDInfo: UserIDInfo? = UserIDInfo(/* initialize with test data */)
//        whenever(userIDService.findBySocialID(socialID)).thenReturn(userIDInfo)
//
//        val result = userIDService.findBySocialID(socialID)
//
//        assertNotNull(result)
//        assertEquals(userIDInfo, result)
//    }
//
//    @Test
//    fun `test findByUserID`() {
//        val userID = 123
//        val userIDInfos: Iterable<UserIDInfo>? = listOf(UserIDInfo(/* initialize with test data */))
//        whenever(userIDService.findByUserID(userID)).thenReturn(userIDInfos)
//
//        val result = userIDService.findByUserID(userID)
//
//        assertNotNull(result)
//        assertTrue(result!!.any())
//    }
//
//    @Test
//    fun `test findAll`() {
//        val userIDInfos: Iterable<UserIDInfo> = listOf(UserIDInfo(/* initialize with test data */))
//        whenever(userIDService.findAll()).thenReturn(userIDInfos)
//
//        val result = userIDService.findAll()
//
//        assertNotNull(result)
//        assertTrue(result.any())
//    }
}