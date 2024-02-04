package me.dio.creditrequestsystem.exception

import org.springframework.dao.DataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalDateTime

@RestControllerAdvice
class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handlerValidException(ex: MethodArgumentNotValidException): ResponseEntity<ExceptionDetails> {
        val errors: MutableMap<String, String?> = HashMap()
        ex.bindingResult.allErrors.stream().forEach { error: ObjectError ->
            val fieldName: String = (error as FieldError).field
            val messageError: String? = error.defaultMessage
            errors[fieldName] = messageError
        }
        return ResponseEntity(geneRateDetails(ex, errors, HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST)
    }
    @ExceptionHandler(DataAccessException::class)
    fun handlerValidException(ex: DataAccessException): ResponseEntity<ExceptionDetails> {
        val error: MutableMap<String, String?> = mutableMapOf(ex.cause.toString() to ex.localizedMessage)
        return ResponseEntity(geneRateDetails(ex, error, HttpStatus.CONFLICT.value()), HttpStatus.CONFLICT)
    }

    @ExceptionHandler(BusinessException::class)
    fun handlerValidException(ex: BusinessException): ResponseEntity<ExceptionDetails> {
        val error: MutableMap<String, String?> = mutableMapOf("message" to ex.localizedMessage)
        return ResponseEntity(geneRateDetails(ex, error, HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST)
    }


    private fun geneRateDetails(ex: Exception, details: MutableMap<String, String?>, status: Int): ExceptionDetails {
        return ExceptionDetails(
            title = "${HttpStatus.valueOf(status)}! Consult the documentation",
            timestamp = LocalDateTime.now(),
            status,
            exception = ex.javaClass.toString(),
            details
        )
    }








}