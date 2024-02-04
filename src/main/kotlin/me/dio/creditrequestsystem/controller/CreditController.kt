package me.dio.creditrequestsystem.controller


import jakarta.validation.Valid
import me.dio.creditrequestsystem.dto.CreditDto
import me.dio.creditrequestsystem.dto.CreditViewDto
import me.dio.creditrequestsystem.dto.CreditViewListDto
import me.dio.creditrequestsystem.entity.Credit
import me.dio.creditrequestsystem.service.impl.CreditServiceImpl
import org.apache.coyote.Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("api/credits")
class CreditController(private val creditServiceImpl: CreditServiceImpl) {

    @PostMapping
    fun saveCredit(@RequestBody @Valid creditDto: CreditDto): ResponseEntity<String> {
        val creditVal = this.creditServiceImpl.save(creditDto.toEntity())
        return ResponseEntity.status(HttpStatus.OK).body(
            "Credit ${creditVal.creditCode} - Customer ${creditVal.customer?.firstName}"
        )
    }

    @GetMapping
    fun findAllByCustomerId(@RequestParam customerId: Long): ResponseEntity<List<CreditViewListDto>> {
        return ResponseEntity.status(HttpStatus.OK).body(creditServiceImpl.findAllByCustomer(customerId).stream()
            .map { credit: Credit -> CreditViewListDto(credit) }.toList()
        )
    }

    @GetMapping("/{id}")
    fun findByCreditCode(
        @RequestParam(value = "customerId") customerId: Long,
        @PathVariable(value = "id") creditCode: UUID
    ): ResponseEntity<CreditViewDto> {
        return ResponseEntity.status(HttpStatus.OK).body(
            CreditViewDto(creditServiceImpl.findByCustomerIdAndCreditCode(customerId, creditCode))
        )
    }


}