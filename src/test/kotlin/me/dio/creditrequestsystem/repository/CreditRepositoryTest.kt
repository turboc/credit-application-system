package me.dio.creditrequestsystem.repository

import me.dio.creditrequestsystem.entity.Credit
import me.dio.creditrequestsystem.entity.Customer
import me.dio.creditrequestsystem.util.BuildUtil
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.ActiveProfiles
import java.util.UUID

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CreditRepositoryTest {
    @Autowired lateinit var creditRepository: ICreditRepository
    @Autowired lateinit var testEntityManager: TestEntityManager

    private lateinit var customer: Customer
    private lateinit var credit1: Credit
    private lateinit var credit2: Credit

    @BeforeEach fun setup () {
        customer = testEntityManager.persist(BuildUtil.buildCustomerEmpty())
        credit1 = testEntityManager.persist(BuildUtil.buildCreditEmpty(customer = customer))
        credit2 = testEntityManager.persist(BuildUtil.buildCreditEmpty(customer = customer))

    }

    @Test
    fun `should find credit by credit code`() {

        println (credit1)
        println (credit2)

        // given
        val creditCode1 = UUID.fromString("361f87d6-671f-4a30-9863-4c556fedaa34")
        val creditCode2 = UUID.fromString("752415d9-0df5-446a-b0a5-5a99ebdbcd16")

        credit1.creditCode = creditCode1
        credit2.creditCode = creditCode2

        // when

        val fakeCredit1: Credit = creditRepository.findByCustomerIdAndCreditCode(1L, creditCode1)!!
        val fakeCredit2: Credit = creditRepository.findByCustomerIdAndCreditCode(1L, creditCode2)!!

        // then
        Assertions.assertThat(fakeCredit1).isNotNull
        Assertions.assertThat(fakeCredit2).isNotNull

        Assertions.assertThat(fakeCredit1).isSameAs(credit1)
        Assertions.assertThat(fakeCredit2).isSameAs(credit2)

    }

    @Test
    fun `should find all credits by customer id`() {

        //give
        val customerId: Long = 1L
        //when
        val creditList: List<Credit> = creditRepository.finAllByCustomerId(customerId)

        //then
        Assertions.assertThat(creditList).isNotEmpty
        Assertions.assertThat(creditList.size).isEqualTo(2)
        Assertions.assertThat(creditList).contains(credit1, credit2)



    }



}