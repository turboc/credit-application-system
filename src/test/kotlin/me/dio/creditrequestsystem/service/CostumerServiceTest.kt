package me.dio.creditrequestsystem.service

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import me.dio.creditrequestsystem.entity.Customer
import me.dio.creditrequestsystem.exception.BusinessException
import me.dio.creditrequestsystem.repository.ICustomerRepository
import me.dio.creditrequestsystem.service.impl.CustomerServiceImpl
import me.dio.creditrequestsystem.util.BuildUtil
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ActiveProfiles
import java.util.*

@ActiveProfiles("test")
@ExtendWith(MockKExtension::class)
class CostumerServiceTest {
    @MockK
    lateinit var customerRepository: ICustomerRepository
    @InjectMockKs
    lateinit var customerServiceImpl: CustomerServiceImpl

    @Test
    fun `should create customer`() {
        // giveen
        val fakeCustomer: Customer = BuildUtil.buildCustomer()
        every { customerRepository.save(any()) } returns fakeCustomer

        // when

        val actual: Customer = customerServiceImpl.save(fakeCustomer)

        //then
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isSameAs(fakeCustomer)

        verify(exactly = 1) { customerRepository.save(fakeCustomer) }


    }

    @Test
    fun `should find customer by id`() {
        // given
        val fakeId: Long = Random().nextLong()
        val fakeCustomer: Customer = BuildUtil.buildCustomer(id = fakeId)
        every { customerRepository.findById(fakeId) } returns Optional.of(fakeCustomer)
        // when
        val actual: Customer = customerServiceImpl.findById(fakeId)
        // then
        Assertions.assertThat(actual).isExactlyInstanceOf(Customer::class.java)
        Assertions.assertThat(actual).isSameAs(fakeCustomer)

        verify(exactly = 1) { customerRepository.findById(fakeId) }

    }

    @Test
    fun `should not find customer by invalid id and throw BusinessException`() {
        // given
        val fakeId: Long = Random().nextLong()
        every { customerRepository.findById(fakeId) } returns Optional.empty()
        // when
        // then
        Assertions.assertThatExceptionOfType(BusinessException::class.java)
            .isThrownBy { customerServiceImpl.findById(fakeId) }
            .withMessage("Id $fakeId not found")
        verify(exactly = 1) { customerRepository.findById(fakeId) }

    }

    @Test
    fun `should delete customer by id`() {
        // given
        val fakeId: Long = Random().nextLong()
        val fakeCustomer: Customer = BuildUtil.buildCustomer(id = fakeId)
        every { customerRepository.findById(fakeId) } returns Optional.of(fakeCustomer)

        every { customerRepository.delete(fakeCustomer) } just runs

        // when

        customerServiceImpl.delete(fakeId)

        //then
        verify(exactly = 1) { customerRepository.findById(fakeId) }
        verify(exactly = 1) { customerRepository.delete(fakeCustomer) }


    }



}