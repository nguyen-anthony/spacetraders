package com.spacetraders.services

import com.spacetraders.client.SpaceTradersClient
import com.spacetraders.client.SpaceTradersConfig
import com.spacetraders.models.ContractsResponse
import jakarta.inject.Singleton
import org.slf4j.LoggerFactory

@Singleton
class ContractService(
    private val spaceTradersClient: SpaceTradersClient,
    private val spaceTradersConfig: SpaceTradersConfig
) {
    private val logger = LoggerFactory.getLogger(ContractService::class.java)

    suspend fun getContracts(): ContractsResponse = try {
        logger.info("Getting contracts")
        spaceTradersClient.getContracts(spaceTradersConfig.token)
    } catch (e: Exception) {
        logger.error("Failed to get contracts", e)
        throw RuntimeException("Failed to get contracts", e)
    }
} 