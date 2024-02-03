package me.dio.creditrequestsystem.controller


import me.dio.creditrequestsystem.dto.CreditDto
import me.dio.creditrequestsystem.service.impl.CreditServiceImpl
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/credits")
class CreditController (private val creditServiceImpl: CreditServiceImpl) {

    @PostMapping
    fun saveCredit(@RequestBody creditDto: CreditDto): String {
        val creditVal = this.creditServiceImpl.save(creditDto.toEntity())
        return "Credit ${creditVal.creditCode} - Customer ${creditVal.customer?.firstName}"
    }





}