package com.spacetraders.services

import com.spacetraders.client.SpaceTradersClient
import com.spacetraders.client.SpaceTradersConfig
import com.spacetraders.models.AcceptContractResponse
import com.spacetraders.models.Contract
import com.spacetraders.models.ContractDetailsResponse
import io.micronaut.http.HttpResponse
import jakarta.inject.Singleton
import org.slf4j.LoggerFactory

@Singleton
class ContractService(
    private val spaceTradersClient: SpaceTradersClient,
    private val spaceTradersConfig: SpaceTradersConfig
) {
    private val logger = LoggerFactory.getLogger(ContractService::class.java)

    suspend fun getContracts(): HttpResponse<Contract> = try {
        logger.info("Getting contracts")
        spaceTradersClient.getContracts(spaceTradersConfig.token)
    } catch (e: Exception) {
        logger.error("Failed to get contracts", e)
        throw RuntimeException("Failed to get contracts", e)
    }

    suspend fun getContractDetails(contractId: String): HttpResponse<ContractDetailsResponse> = try {
        logger.info("Getting contract details")
        spaceTradersClient.getContractDetails(spaceTradersConfig.token, contractId)
    } catch (e: Exception) {
        logger.error("Failed to get contract details", e)
        throw RuntimeException("Failed to get contract details", e)
    }

    suspend fun acceptContract(contractId: String): HttpResponse<AcceptContractResponse> = try {
        logger.info("Accepting contract with ID: $contractId")
        spaceTradersClient.acceptContract(spaceTradersConfig.token, contractId)
    } catch (e: Exception) {
        logger.error("Failed to accept contract with ID: $contractId", e)
        throw RuntimeException("Failed to accept contract with ID: $contractId", e)
    }
} 