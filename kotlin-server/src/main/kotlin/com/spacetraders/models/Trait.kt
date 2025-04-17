package com.spacetraders.models

import io.micronaut.serde.annotation.Serdeable
import kotlinx.serialization.Serializable

@Serdeable
@Serializable
data class Trait(
    val symbol: String,
    val name: String,
    val description: String
)
