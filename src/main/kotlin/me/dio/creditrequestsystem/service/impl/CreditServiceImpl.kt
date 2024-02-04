package me.dio.creditrequestsystem.service.impl

import me.dio.creditrequestsystem.entity.Credit
import me.dio.creditrequestsystem.exception.BusinessException
import me.dio.creditrequestsystem.repository.ICreditRepository
import me.dio.creditrequestsystem.service.ICreditService
import org.springframework.stereotype.Service
import java.util.*

@Service
class CreditServiceImpl (
    private val creditRepository: ICreditRepository,
    private val customerServiceImpl: CustomerServiceImpl
) : ICreditService  {
    override fun save(credit: Credit): Credit {

        credit.apply {
            customer = customerServiceImpl.findById(credit.customer?.id!!)
        }
        return this.creditRepository.save(credit)
    }

    override fun findAllByCustomer(customerId: Long): List<Credit> {
        return this.creditRepository.finAllByCustomerId(customerId)
    }

    override fun findByCustomerIdAndCreditCode(customerId: Long, creditCode: UUID): Credit {
        return this.creditRepository.findByCustomerIdAndCreditCode(customerId, creditCode) ?:
        throw BusinessException("CreditCode $creditCode not found ")
    }
}