package me.dio.creditrequestsystem.util

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Positive
import me.dio.creditrequestsystem.dto.CustomerDto
import me.dio.creditrequestsystem.dto.CustomerUpdateDto
import me.dio.creditrequestsystem.ennumeration.Status
import me.dio.creditrequestsystem.entity.Address
import me.dio.creditrequestsystem.entity.Credit
import me.dio.creditrequestsystem.entity.Customer
import org.hibernate.validator.constraints.br.CPF
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

object  BuildUtil {

     fun buildCustomer(
        firstName: String = "Jorge",
        lastName: String = "V",
        cpf: String = "03578565811",
        email: String = "jorge@email.com",
        password: String = "12345",
        zipCode: String = "12345",
        street: String = "Marechal",
        income: BigDecimal = BigDecimal.valueOf(1000.00),
        id: Long = 1L
    ) = Customer(
        firstName,
        lastName,
        cpf,
        email,
        income,
        password,
        address = Address(zipCode, street),
        credits = mutableListOf(),
        id
    )

    fun buildCustomerEmpty(
        firstName: String = "Jorge",
        lastName: String = "V",
        cpf: String = "03578565811",
        email: String = "jorge@email.com",
        password: String = "12345",
        zipCode: String = "12345",
        street: String = "Marechal",
        income: BigDecimal = BigDecimal.valueOf(1000.00),
    ) = Customer(
        firstName,
        lastName,
        cpf,
        email,
        income,
        password,
        address = Address(zipCode, street),
        credits = mutableListOf(),
    )

     fun buildCredit(
        creditCode: UUID = UUID.randomUUID(),
        creditValue: BigDecimal = BigDecimal.ZERO,
        dayFirstInstallment: LocalDate = LocalDate.now(),
        numberOfInstallments: Int = 1,
        status: Status = Status.IN_PROGRESS,
        customer: Customer? = buildCustomer(),
        id: Long = 1L
    ) = Credit(
        creditCode,
        creditValue,
        dayFirstInstallment,
        numberOfInstallments,
        status,
        customer,
        id
    )

    fun buildCreditEmpty(
        creditCode: UUID = UUID.randomUUID(),
        creditValue: BigDecimal = BigDecimal.ZERO,
        dayFirstInstallment: LocalDate = LocalDate.now(),
        numberOfInstallments: Int = 1,
        status: Status = Status.IN_PROGRESS,
        customer: Customer? = buildCustomer(),
    ) = Credit(
        creditCode,
        creditValue,
        dayFirstInstallment,
        numberOfInstallments,
        status,
        customer,
    )


    fun buildCustomerDto (
        firstName: String = "Jorge",
        lastName: String = "V",
        cpf: String = "88358271670", // cpf valido para teste
        email: String = "jorge@email.com",
        password: String = "12345",
        zipCode: String = "12345",
        street: String = "Marechal",
        income: BigDecimal = BigDecimal.valueOf(1000.00),
    ) = CustomerDto (
        firstName,
        lastName,
        cpf,
        income,
        email,
        password,
        zipCode,
        street,
    )

    fun buildCustomerUpdateDto (
        firstName: String = "Jorge-Update",
        lastName: String = "V",
        zipCode: String = "45656",
        street: String = "Rua Updated",
        income: BigDecimal = BigDecimal.valueOf(5000.00),
    ) = CustomerUpdateDto (
        firstName,
        lastName,
        income,
        zipCode,
        street,
    )


}