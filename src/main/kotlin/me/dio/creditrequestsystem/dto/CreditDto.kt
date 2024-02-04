package me.dio.creditrequestsystem.dto

import jakarta.validation.constraints.Future
import jakarta.validation.constraints.Positive
import me.dio.creditrequestsystem.entity.Credit
import me.dio.creditrequestsystem.entity.Customer
import java.math.BigDecimal
import java.time.LocalDate


data class CreditDto(
    @field:Positive(message = "Credito deve ser informado") val creditValue: BigDecimal,
    @field:Future val dayFirstInstallment: LocalDate,
    @field:Positive(message = "numberOfInstallments deve ser informado") val numberOfInstallments: Int,
    @field:Positive(message = "Customer deve ser informado")  val customerId: Long
) {
    fun toEntity(): Credit = Credit(
        creditValue = this.creditValue,
        dayFirstInstallment = this.dayFirstInstallment,
        numberOfInstallments = this.numberOfInstallments,
        customer = Customer(id = this.customerId)
    )
}
