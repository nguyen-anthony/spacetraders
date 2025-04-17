package com.spacetraders.controllers

import com.spacetraders.services.ContractService
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import jakarta.inject.Inject
import org.slf4j.LoggerFactory

@Controller("/api")
class ContractController @Inject constructor(private val contractService: ContractService) {
    private val logger = LoggerFactory.getLogger(ContractController::class.java)
    
    @Get("/contracts")
    @Produces(MediaType.APPLICATION_JSON)
    suspend fun getContracts() = try {
        logger.info("Received request for getting contracts")
        val contracts = contractService.getContracts()
        HttpResponse.ok(contracts)
    } catch (e: Exception) {
        logger.error("Error getting contract information", e)
        HttpResponse.serverError("Error getting contract information: ${e.message}")
    }
} 