package com.spacetraders.services

import com.spacetraders.client.SpaceTradersClient
import com.spacetraders.client.SpaceTradersConfig
import com.spacetraders.models.Waypoint
import jakarta.inject.Singleton
import org.slf4j.LoggerFactory

@Singleton
class WaypointService(
    private val spaceTradersClient: SpaceTradersClient,
    private val spaceTradersConfig: SpaceTradersConfig
) {
    private val logger = LoggerFactory.getLogger(WaypointService::class.java)

    suspend fun getHeadquartersWaypoint(): Waypoint = try {
        logger.info("Getting agent information")
        val agent = spaceTradersClient.getAgent(spaceTradersConfig.token)
        val headquarters = agent.body().data.headquarters
        logger.info("Agent headquarters: $headquarters")
        
        val parts = headquarters.split("-")
        if (parts.size < 2) {
            throw IllegalStateException("Invalid headquarters format: $headquarters")
        }
        val systemSymbol = "${parts[0]}-${parts[1]}"
        logger.info("Getting waypoint information for system: $systemSymbol, waypoint: $headquarters")
        spaceTradersClient.getWaypoint(systemSymbol, headquarters, spaceTradersConfig.token)
    } catch (e: Exception) {
        logger.error("Failed to get headquarters waypoint", e)
        throw RuntimeException("Failed to get headquarters waypoint", e)
    }
} 