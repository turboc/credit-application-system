package me.dio.creditrequestsystem.service.impl

import me.dio.creditrequestsystem.entity.Customer
import me.dio.creditrequestsystem.exception.BusinessException
import me.dio.creditrequestsystem.repository.ICustomerRepository
import me.dio.creditrequestsystem.service.ICustomerSerivoce
import org.springframework.stereotype.Service

@Service
class CustomerServiceImpl(private val customerRepository: ICustomerRepository) : ICustomerSerivoce {

    override fun save(customer: Customer): Customer = this.customerRepository.save(customer)

    // nao fiz inline para experimentar outra sintaxe
    override fun findById(id: Long): Customer {
        return this.customerRepository.findById(id).orElseThrow {
            throw BusinessException("Id $id not found")
        }
    }

    override fun delete(id: Long) {
        val customer : Customer = this.findById(id)
        this.customerRepository.delete(customer)
    }


}