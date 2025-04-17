package com.spacetraders.client

import com.spacetraders.config.EnvConfig
import com.spacetraders.models.AgentResponse
import com.spacetraders.models.WaypointResponse
import com.spacetraders.models.ContractsResponse
import io.micronaut.context.annotation.ConfigurationProperties
import io.micronaut.http.HttpHeaders
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Header
import io.micronaut.http.client.annotation.Client
import jakarta.inject.Inject

@ConfigurationProperties("spacetraders")
class SpaceTradersConfig @Inject constructor(private val envConfig: EnvConfig) {
    val token: String
        get() = "Bearer ${envConfig.spacetradersToken}"
}

@Client("https://api.spacetraders.io/v2")
interface SpaceTradersClient {
    @Get("/my/agent")
    suspend fun getAgent(@Header(HttpHeaders.AUTHORIZATION) token: String): AgentResponse

    @Get("/systems/{systemSymbol}/waypoints/{waypointSymbol}")
    suspend fun getWaypoint(
        systemSymbol: String,
        waypointSymbol: String,
        @Header(HttpHeaders.AUTHORIZATION) token: String
    ): WaypointResponse

    @Get("/my/contracts")
    suspend fun getContracts(@Header(HttpHeaders.AUTHORIZATION) token: String): ContractsResponse
} 