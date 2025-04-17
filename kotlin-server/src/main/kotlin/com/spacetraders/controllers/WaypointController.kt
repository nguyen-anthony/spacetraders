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
    suspend fun getHeadquartersWaypoint() = try {
        logger.info("Received request for headquarters waypoint")
        val waypoint = waypointService.getHeadquartersWaypoint()
        logger.info("Successfully retrieved headquarters waypoint: ${waypoint.data.symbol}")
        HttpResponse.ok(waypoint)
    } catch (e: Exception) {
        logger.error("Error getting waypoint information", e)
        HttpResponse.serverError("Error getting waypoint information: ${e.message}")
    }
} 