package com.spacetraders.client

import com.spacetraders.config.EnvConfig
import com.spacetraders.models.AcceptContractResponse
import com.spacetraders.models.Agent
import com.spacetraders.models.Contract
import com.spacetraders.models.Waypoint
import com.spacetraders.models.System
import io.micronaut.context.annotation.ConfigurationProperties
import io.micronaut.http.HttpHeaders
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Header
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.QueryValue
import io.micronaut.http.client.annotation.Client
import jakarta.inject.Inject

@ConfigurationProperties("spacetraders")
class SpaceTradersConfig @Inject constructor(private val envConfig: EnvConfig) {
    val token: String
        get() = "Bearer ${envConfig.spacetradersToken}"
}

@Client("https://api.spacetraders.io/v2")
interface SpaceTradersClient {
    @Get("/")
    suspend fun getAPIStatus(): HttpResponse<Any>

    @Post("/register")
    suspend fun registerAgent(
        @Header(HttpHeaders.AUTHORIZATION) token: String,
    ): HttpResponse<Any>

    @Get("/systems")
    suspend fun getSystems(
        @Header(HttpHeaders.AUTHORIZATION) token: String,
        @QueryValue("page") page: Int? = 1,
        @QueryValue("limit") limit: Int? = 10
    ): System

    @Get("/my/agent")
    suspend fun getAgent(@Header(HttpHeaders.AUTHORIZATION) token: String): HttpResponse<Agent>

    @Get("/systems/{systemSymbol}/waypoints/{waypointSymbol}")
    suspend fun getWaypoint(
        systemSymbol: String,
        waypointSymbol: String,
        @Header(HttpHeaders.AUTHORIZATION) token: String
    ): Waypoint

    @Get("/my/contracts")
    suspend fun getContracts(@Header(HttpHeaders.AUTHORIZATION) token: String): HttpResponse<Contract>

    @Post("/my/contracts/{contractId}/accept")
    suspend fun acceptContract(@Header(HttpHeaders.AUTHORIZATION) token: String, @PathVariable contractId: String): HttpResponse<AcceptContractResponse>
} 