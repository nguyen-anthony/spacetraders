package com.spacetraders.controllers

import com.spacetraders.services.AgentService
import com.spacetraders.services.ContractService
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Produces
import jakarta.inject.Inject
import org.slf4j.LoggerFactory

@Controller("/api")
class AgentController @Inject constructor(
    private val agentService: AgentService,
    private val contractService: ContractService,
) {
    private val logger = LoggerFactory.getLogger(AgentController::class.java)

    @Get("/my_agent")
    @Produces(MediaType.APPLICATION_JSON)
    suspend fun getMyAgent() = runCatching {
        logger.info("Received request for getting agent information")
        agentService.getAgent()
    }.fold(
        onSuccess = { it },
        onFailure = {
            logger.info("Error getting agent information", it)
            HttpResponse.serverError("Error getting agent information: ${it.message}")
        }
    )

    @Get("/my_contracts")
    @Produces(MediaType.APPLICATION_JSON)
    suspend fun getMyContracts() = runCatching {
        logger.info("Received request for getting agent contract information")
        contractService.getContracts()
    }.fold(
        onSuccess = { it },
        onFailure = {
            logger.info("Error getting agent information", it)
            HttpResponse.serverError("Error getting agent information: ${it.message}")
        }
    )

    @Post("/accept_contract/{contractId}")
    @Produces(MediaType.APPLICATION_JSON)
    suspend fun acceptContract(@PathVariable contractId: String) = runCatching {
        logger.info("Received request for accepting agent contract")
        contractService.acceptContract(contractId)
    }.fold(
        onSuccess = { it },
        onFailure = {
            logger.info("Error accepting agent contract", it)
            HttpResponse.serverError("Error accepting agent contract: ${it.message}")
        }
    )
}