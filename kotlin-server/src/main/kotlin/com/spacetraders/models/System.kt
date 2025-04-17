package com.spacetraders.models

import io.micronaut.serde.annotation.Serdeable
import kotlinx.serialization.Serializable

@Serializable
@Serdeable
data class System (
    val data: List<SystemData>
) {
    @Serdeable
    @Serializable
    data class SystemData(
        val symbol: String,
        val constellation: String,
        val name: String,
        val sectorSymbol: String,
        val type: String,
        val x: Int,
        val y: Int,
        val waypoints: List<WaypointData>,
        val factions: List<Faction>,
    )
}
