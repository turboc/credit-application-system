package me.dio.creditrequestsystem.service

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import me.dio.creditrequestsystem.entity.Credit
import me.dio.creditrequestsystem.entity.Customer
import me.dio.creditrequestsystem.repository.ICreditRepository
import me.dio.creditrequestsystem.repository.ICustomerRepository
import me.dio.creditrequestsystem.service.impl.CreditServiceImpl
import me.dio.creditrequestsystem.service.impl.CustomerServiceImpl
import me.dio.creditrequestsystem.util.BuildUtil
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ActiveProfiles
import java.util.*


@ActiveProfiles("test")
@ExtendWith(MockKExtension::class)
class CreditServiceTest {

    @MockK
    lateinit var customerServiceImpl: CustomerServiceImpl

    @MockK
    lateinit var creditRepository: ICreditRepository

    @MockK
    lateinit var customerRepository: ICustomerRepository

    @InjectMockKs
    lateinit var creditServiceImpl: CreditServiceImpl


    @Test
    fun `should create Credit`() {
        // giveen
        val fakeId: Long = 1L
        val fakeCredit: Credit = BuildUtil.buildCredit(id = fakeId)
        val fakeCustomer: Customer = BuildUtil.buildCustomer(id = fakeId)
        every { customerRepository.findById(fakeId) } returns Optional.of(fakeCustomer)
        every { customerServiceImpl.findById(fakeId) } returns fakeCustomer

        every { creditRepository.save(any()) } returns fakeCredit

        // when

        val actual: Credit = creditServiceImpl.save(fakeCredit)

        //then
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isSameAs(fakeCredit)

        verify(exactly = 1) { creditRepository.save(fakeCredit) }


    }


    @Test
    fun `should find credit by customer`() {
        // given
        val fakeId: Long = 1L
        val fakeCredit: Credit = BuildUtil.buildCredit(id = fakeId)
        every { creditRepository.finAllByCustomerId(fakeId) } returns mutableListOf(fakeCredit)
        // when
        val actual: List<Credit> = creditServiceImpl.findAllByCustomerId(fakeId)
        // then
        Assertions.assertThat(actual).hasSize(1)
        Assertions.assertThat(actual).containsExactly(fakeCredit)
        verify(exactly = 1) { creditServiceImpl.findAllByCustomerId(fakeId) }

    }




}