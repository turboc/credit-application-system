package me.dio.creditrequestsystem.repository


import me.dio.creditrequestsystem.entity.Credit
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ICreditRepository: JpaRepository<Credit, Long> {

    @Query(nativeQuery = true, value = "select c.* from Credit c where c.customer_id = ?1")
    fun finAllByCustomerId(customerId: Long): List<Credit>

    @Query(nativeQuery = true, value = "select c.* from Credit c where c.customer_id = ?1 and c.credit_code = ?2")
    fun findByCustomerIdAndCreditCode(customerId: Long, creditCode: UUID): Credit?


}