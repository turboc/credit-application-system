package me.dio.creditrequestsystem.controller

import jakarta.validation.Valid
import me.dio.creditrequestsystem.dto.CustomerDto
import me.dio.creditrequestsystem.dto.CustomerUpdateDto
import me.dio.creditrequestsystem.dto.CustomerViewDto
import me.dio.creditrequestsystem.entity.Customer
import me.dio.creditrequestsystem.service.impl.CustomerServiceImpl
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("api/customers")
class CustomerController(private val customerServiceImpl: CustomerServiceImpl) {

    @PostMapping
    fun saveCustomer(@RequestBody @Valid customerDto: CustomerDto): ResponseEntity<CustomerViewDto> {
        val savedCustomer = this.customerServiceImpl.save(customerDto.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED).body(CustomerViewDto(savedCustomer))
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<CustomerViewDto> {
        val customer: Customer = this.customerServiceImpl.findById(id)
        return ResponseEntity.status(HttpStatus.OK).body(CustomerViewDto(customer))
    }

    @DeleteMapping("/{id}")
    fun deleteCustomer(@PathVariable id: Long): ResponseEntity<Unit> {
        this.customerServiceImpl.delete(id)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

    @PatchMapping
    fun updateCustomer(
        @RequestParam(value = "customerId") id: Long,
        @RequestBody @Valid customerUpdateDro: CustomerUpdateDto
    ): ResponseEntity<CustomerViewDto> {
        var customer = this.customerServiceImpl.findById(id)
        this.customerServiceImpl.save(customerUpdateDro.updateEntity(customer))
        return ResponseEntity.status(HttpStatus.OK).body(CustomerViewDto(customer))

    }

}