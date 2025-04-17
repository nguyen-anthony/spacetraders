package com.spacetraders.services

import com.spacetraders.client.SpaceTradersClient
import com.spacetraders.client.SpaceTradersConfig
import com.spacetraders.models.Agent
import io.micronaut.http.HttpResponse
import jakarta.inject.Singleton
import org.slf4j.LoggerFactory

@Singleton
class AgentService(
    private val spaceTradersClient: SpaceTradersClient,
    private val spaceTradersConfig: SpaceTradersConfig
) {
    private val logger = LoggerFactory.getLogger(AgentService::class.java)

    suspend fun getAgent(): HttpResponse<Agent> = try {
        logger.info("Getting agent details")
        spaceTradersClient.getAgent(spaceTradersConfig.token)
    } catch (e: Exception) {
        logger.error("Failed to get agent details", e)
        throw RuntimeException("Failed to get agent details", e)
    }
}