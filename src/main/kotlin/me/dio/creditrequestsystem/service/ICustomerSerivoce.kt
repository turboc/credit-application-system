package me.dio.creditrequestsystem.service


import me.dio.creditrequestsystem.entity.Customer




interface ICustomerSerivoce {

    fun save(customer: Customer): Customer

    fun findById(id: Long): Customer


    fun delete(id: Long)




}