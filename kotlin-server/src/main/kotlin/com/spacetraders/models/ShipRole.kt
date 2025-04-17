package com.spacetraders.models

enum class ShipRole {
    FABRICATOR,
    HARVESTER,
    HAULER,
    INTERCEPTOR,
    EXCAVATOR,
    TRANSPORT,
    REPAIR,
    SURVEYOR,
    COMMAND,
    CARRIER,
    PATROL,
    SATELLITE,
    EXPLORER,
    REFINERY;

    companion object {
        fun fromValue(value: String): ShipRole? {
            return ShipRole.entries.find { it.name.equals(value, ignoreCase = true) }
        }
    }
}