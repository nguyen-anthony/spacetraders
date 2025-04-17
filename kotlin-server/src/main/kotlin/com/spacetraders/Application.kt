package com.spacetraders

import io.github.cdimascio.dotenv.dotenv
import io.micronaut.runtime.Micronaut
import org.slf4j.LoggerFactory

fun main() {
    val logger = LoggerFactory.getLogger("com.spacetraders.Application")
    
    // Load environment variables
    try {
        val env = dotenv {
            ignoreIfMissing = true
        }
        val token = env["SPACETRADERS_TOKEN"]
        if (token != null) {
            logger.info("Successfully loaded SpaceTraders token: ${token.take(10)}...")
        } else {
            logger.error("SPACETRADERS_TOKEN not found in .env file")
        }
    } catch (e: Exception) {
        logger.error("Error loading .env file: ${e.message}")
    }

    Micronaut.build()
        .packages("com.spacetraders")
        .start()
}