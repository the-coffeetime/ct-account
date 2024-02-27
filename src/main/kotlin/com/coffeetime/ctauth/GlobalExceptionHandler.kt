package com.coffeetime.ctauth

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {
    // 무결성(unique 등) 제약조건 위반시 전역 예외 처리
    @ExceptionHandler(DataIntegrityViolationException::class)
    fun handleDataIntegrityViolationException(e: DataIntegrityViolationException): ResponseEntity<String> {
        // Log the exception details for debugging purposes
        // Log.error("Data integrity violation", e)

        // Provide a generic error response or customize based on the exception details
        return ResponseEntity("A unique constraint violation occurred.", HttpStatus.CONFLICT)
    }
}