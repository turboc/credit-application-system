package me.dio.creditrequestsystem.service

import me.dio.creditrequestsystem.entity.Credit
import java.util.*

interface ICreditService {

    fun save(credit: Credit): Credit

    fun findAllByCustomerId(customerId: Long): List<Credit>

    fun findByCustomerIdAndCreditCode(customerId: Long, creditCode: UUID): Credit
}