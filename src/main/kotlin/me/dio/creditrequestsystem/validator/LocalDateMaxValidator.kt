package me.dio.creditrequestsystem.validator

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import java.time.LocalDate

class LocalDateWithinThreeMonthsValidator : ConstraintValidator<LocalDateWithinThreeMonths, LocalDate> {

    override fun isValid(value: LocalDate?, context: ConstraintValidatorContext): Boolean {
        return value?.let {
            val threeMonthsLater = LocalDate.now().plusMonths(3)
            it.isBefore(threeMonthsLater) || it.isEqual(threeMonthsLater)
        } ?: true
    }
}

