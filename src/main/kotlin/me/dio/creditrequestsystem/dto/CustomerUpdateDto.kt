package me.dio.creditrequestsystem.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Positive
import me.dio.creditrequestsystem.entity.Customer

import java.math.BigDecimal

data class CustomerUpdateDto(

    @field:NotEmpty(message = "Invalid input") val firstName: String,
    @field:NotEmpty(message = "Invalid input") val lastName: String,

    @field:Positive(message = "Renda deve ser informada") val income: BigDecimal,

    @field:NotEmpty(message = "Invalid input") val zipCode: String,
    @field:NotEmpty(message = "Invalid input") val street: String

) {
    fun updateEntity(customer: Customer): Customer {
        customer.firstName = this.firstName
        customer.lastName = this.lastName
        customer.income = this.income
        customer.address.zipCode = this.zipCode
        customer.address.street = this.street

        return customer
    }
}