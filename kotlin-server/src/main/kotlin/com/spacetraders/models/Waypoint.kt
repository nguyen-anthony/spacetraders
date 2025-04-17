package com.spacetraders.models

import io.micronaut.serde.annotation.Serdeable
import kotlinx.serialization.Serializable

@Serdeable
@Serializable
data class Waypoint(
    val data: WaypointData
)

@Serdeable
@Serializable
data class WaypointData(
    val systemSymbol: String? = null,
    val symbol: String,
    val type: String,
    val x: Int,
    val y: Int,
    val orbitals: List<Orbital>,
    val traits: List<Trait>? = null,
    val modifiers: List<String>? = null,
    val chart: Chart? = null,
    val faction: Faction? = null,
    val isUnderConstruction: Boolean? = null,
)

@Serdeable
@Serializable
data class Chart(
    val submittedBy: String,
    val submittedOn: String
)
