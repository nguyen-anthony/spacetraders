package com.spacetraders.services

import com.spacetraders.client.SpaceTradersClient
import com.spacetraders.client.SpaceTradersConfig
import com.spacetraders.models.SearchWaypointResponse
import com.spacetraders.models.Waypoint
import com.spacetraders.models.WaypointTrait
import jakarta.inject.Singleton
import org.slf4j.LoggerFactory

@Singleton
class WaypointService(
    private val agentService: AgentService,
    private val spaceTradersClient: SpaceTradersClient,
    private val spaceTradersConfig: SpaceTradersConfig
) {
    private val logger = LoggerFactory.getLogger(WaypointService::class.java)

    suspend fun getHeadquarters(): String = try {
        logger.info("Getting agent information")
        val agent = agentService.getAgent()
        agent.body().data.headquarters
    } catch (e: Exception) {
        logger.error("Failed to get headquarters", e)
        throw RuntimeException("Failed to get headquarters", e)
    }

    suspend fun getSystemSymbol(): String = try {
        val headquarters = getHeadquarters()
        logger.info("Agent headquarters: $headquarters")

        val parts = headquarters.split("-")
        if (parts.size < 2) {
            throw IllegalStateException("Invalid headquarters format: $headquarters")
        }
        "${parts[0]}-${parts[1]}"
    } catch (e: Exception) {
        logger.error("Failed to get system symbol", e)
        throw RuntimeException("Failed to get system symbol", e)
    }

    suspend fun getHeadquartersWaypoint(): Waypoint = try {
        val headquarters = getHeadquarters()
        val systemSymbol = getSystemSymbol()
        logger.info("Getting waypoint information for system: $systemSymbol, waypoint: $headquarters")
        spaceTradersClient.getWaypoint(systemSymbol, headquarters, spaceTradersConfig.token)
    } catch (e: Exception) {
        logger.error("Failed to get headquarters waypoint", e)
        throw RuntimeException("Failed to get headquarters waypoint", e)
    }

    suspend fun searchWaypointTrait(waypointTrait: WaypointTrait?): SearchWaypointResponse = try {
        logger.info("Searching for waypoint of type: $waypointTrait")
        val systemSymbol = getSystemSymbol()
        spaceTradersClient.searchWaypoints(spaceTradersConfig.token, systemSymbol, waypointTrait)
    } catch (e: Exception) {
        logger.error("Failed to search for waypoint type", e)
        throw RuntimeException("Failed to search for waypoint type", e)
    }
} 