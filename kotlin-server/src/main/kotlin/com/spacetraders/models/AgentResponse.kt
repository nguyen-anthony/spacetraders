package com.spacetraders.models

import io.micronaut.serde.annotation.Serdeable
import kotlinx.serialization.Serializable

@Serdeable
@Serializable
data class AgentResponse(
    val data: AgentData
)

@Serdeable
@Serializable
data class AgentData(
    val accountId: String,
    val symbol: String,
    val headquarters: String,
    val credits: Int,
    val startingFaction: String,
    val shipCount: Int
)