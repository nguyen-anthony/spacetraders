package com.spacetraders.config

import io.github.cdimascio.dotenv.dotenv
import jakarta.inject.Singleton

@Singleton
class EnvConfig {
    private val dotenv = dotenv {
        ignoreIfMissing = true
        directory = "./"
    }

    val spacetradersToken: String
        get() = dotenv["SPACETRADERS_TOKEN"] ?: throw IllegalStateException("SPACETRADERS_TOKEN environment variable is not set")
} 