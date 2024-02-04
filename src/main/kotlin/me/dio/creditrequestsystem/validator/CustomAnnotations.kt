package me.dio.creditrequestsystem.validator

import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

class CustomAnnotations {
}

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [LocalDateWithinThreeMonthsValidator::class])
annotation class LocalDateWithinThreeMonths(val message: String = "A data deve ser no máximo 3 meses após a data atual",
                                            val groups: Array<KClass<*>> = [],
                                            val payload: Array<KClass<out Payload>> = [])
