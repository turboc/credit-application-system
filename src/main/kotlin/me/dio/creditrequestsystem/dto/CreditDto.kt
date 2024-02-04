package me.dio.creditrequestsystem.dto


import jakarta.validation.constraints.Future
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Positive
import me.dio.creditrequestsystem.entity.Credit
import me.dio.creditrequestsystem.entity.Customer
import me.dio.creditrequestsystem.validator.LocalDateWithinThreeMonths
import java.math.BigDecimal
import java.time.LocalDate



data class CreditDto(
    @field:Positive(message = "Credito deve ser informado") val creditValue: BigDecimal,
    @field:Future
    @field:LocalDateWithinThreeMonths(message = "A data não deve exceder 3 meses após a data atual")
    val dayFirstInstallment: LocalDate,
    @field:Positive(message = "numberOfInstallments deve ser informado")
    @field:Max(value = 48, message = "Número de parcelas solicitado inválido")
    val numberOfInstallments: Int,
    @field:Positive(message = "Customer deve ser informado")  val customerId: Long
) {
    fun toEntity(): Credit = Credit(
        creditValue = this.creditValue,
        dayFirstInstallment = this.dayFirstInstallment,
        numberOfInstallments = this.numberOfInstallments,
        customer = Customer(id = this.customerId)
    )
}



