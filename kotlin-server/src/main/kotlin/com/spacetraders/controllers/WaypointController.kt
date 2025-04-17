package com.spacetraders.controllers

import com.spacetraders.models.WaypointTrait
import com.spacetraders.services.WaypointService
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Produces
import jakarta.inject.Inject
import org.slf4j.LoggerFactory

@Controller("/api")
class WaypointController @Inject constructor(private val waypointService: WaypointService) {
    private val logger = LoggerFactory.getLogger(WaypointController::class.java)
    
    @Get("/headquarters-waypoint")
    @Produces(MediaType.APPLICATION_JSON)
    suspend fun getHeadquartersWaypoint() = runCatching {
        logger.info("Received request for headquarters waypoint")
        waypointService.getHeadquartersWaypoint()
    }.fold (
        onSuccess = {
            it
        },
        onFailure = {
            logger.error("Error getting headquarters waypoint", it)
            HttpResponse.serverError("Error getting headquarters waypoint: ${it.message}")
        }
    )

    @Get("/search-waypoint/{waypointTrait}")
    @Produces(MediaType.APPLICATION_JSON)
    suspend fun searchWaypoint(@PathVariable waypointTrait: String) = runCatching {
        logger.info("Received request for search for a $waypointTrait waypoint")
        val trait = WaypointTrait.fromValue(waypointTrait.uppercase())
        waypointService.searchWaypointTrait(trait)
    }.fold (
        onSuccess = { it },
        onFailure = {
            logger.error("Error searching for waypoint", it)
            HttpResponse.serverError("Error searching for waypoint: ${it.message}")
        }
    )

} 