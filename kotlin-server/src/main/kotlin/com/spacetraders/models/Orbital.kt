package com.spacetraders.models

import io.micronaut.serde.annotation.Serdeable
import kotlinx.serialization.Serializable

@Serdeable
@Serializable
data class Orbital(
    val symbol: String
)