package com.spacetraders.controllers

import com.spacetraders.services.WaypointService
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
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
} 