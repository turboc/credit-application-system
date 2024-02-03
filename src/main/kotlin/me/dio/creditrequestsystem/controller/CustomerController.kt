package me.dio.creditrequestsystem.controller

import me.dio.creditrequestsystem.dto.CustomerDto
import me.dio.creditrequestsystem.dto.CustomerUpdateDto
import me.dio.creditrequestsystem.dto.CustomerView
import me.dio.creditrequestsystem.entity.Customer
import me.dio.creditrequestsystem.service.impl.CustomerServiceImpl
import org.springframework.web.bind.annotation.*



@RestController
@RequestMapping("api/customers")
class CustomerController (private val customerServiceImpl: CustomerServiceImpl) {

    @PostMapping
    fun saveCustomer(@RequestBody customerDto: CustomerDto): String {
        val savedCustomer = this.customerServiceImpl.save(customerDto.toEntity())
        return "Customer ${savedCustomer.email} saved!"
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long) : CustomerView {
        val customer: Customer = this.customerServiceImpl.findById(id)
        return CustomerView(customer)
    }

    @DeleteMapping("/{id}")
    fun deleteCustomer(@PathVariable id: Long) =  this.customerServiceImpl.delete(id)

    @PatchMapping
    fun updateCustomer(@RequestParam(value = "customerId") id: Long, @RequestBody customerUpdateDro: CustomerUpdateDto): CustomerView {
        var customer = this.customerServiceImpl.findById(id)
        this.customerServiceImpl.save(customerUpdateDro.updateEntity(customer))
        return CustomerView(customer)

    }

}