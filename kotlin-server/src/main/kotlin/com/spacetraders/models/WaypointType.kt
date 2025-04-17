package com.spacetraders.models

enum class WaypointType {
    PLANET,
    GAS_GIANT,
    MOON,
    ORBITAL_STATION,
    JUMP_GATE,
    ASTEROID_FIELD,
    ASTEROID,
    ENGINEERED_ASTEROID,
    ASTEROID_BASE,
    NEBULA,
    DEBRIS_FIELD,
    GRAVITY_WELL,
    ARTIFICIAL_GRAVITY_WELL,
    FUEL_STATION;

    companion object {
        fun fromValue(value: String): WaypointType? {
            return WaypointType.entries.find { it.name.equals(value, ignoreCase = true) }
        }
    }
}