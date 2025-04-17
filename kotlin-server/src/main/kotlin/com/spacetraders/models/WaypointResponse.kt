package com.spacetraders.models

import io.micronaut.serde.annotation.Serdeable
import kotlinx.serialization.Serializable

@Serdeable
@Serializable
data class WaypointResponse(
    val data: WaypointData
)

@Serdeable
@Serializable
data class WaypointData(
    val symbol: String,
    val type: String,
    val systemSymbol: String,
    val x: Int,
    val y: Int,
    val orbitals: List<Orbital>,
    val traits: List<Trait>,
    val chart: Chart,
    val faction: Faction
)

@Serdeable
@Serializable
data class Orbital(
    val symbol: String
)

@Serdeable
@Serializable
data class Trait(
    val symbol: String,
    val name: String,
    val description: String
)

@Serdeable
@Serializable
data class Chart(
    val submittedBy: String,
    val submittedOn: String
)

@Serdeable
@Serializable
data class Faction(
    val symbol: String
) 