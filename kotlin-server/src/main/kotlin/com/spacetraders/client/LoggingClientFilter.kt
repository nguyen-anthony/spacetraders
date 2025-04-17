package com.spacetraders.client

import io.micronaut.http.HttpHeaders
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Filter
import io.micronaut.http.MutableHttpRequest
import io.micronaut.http.filter.ClientFilterChain
import io.micronaut.http.filter.HttpClientFilter
import org.reactivestreams.Publisher
import org.slf4j.LoggerFactory
import jakarta.inject.Singleton

@Filter("/**")
@Singleton
class LoggingClientFilter : HttpClientFilter {
    private val logger = LoggerFactory.getLogger(LoggingClientFilter::class.java)

    override fun doFilter(request: MutableHttpRequest<*>, chain: ClientFilterChain): Publisher<out HttpResponse<*>> {
        logger.info("Request URL: ${request.uri}")
        logger.info("Authorization header: ${request.headers.get(HttpHeaders.AUTHORIZATION)}")
        return chain.proceed(request)
    }
} 