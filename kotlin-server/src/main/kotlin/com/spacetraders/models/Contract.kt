package com.spacetraders.models

import io.micronaut.serde.annotation.Serdeable
import kotlinx.serialization.Serializable

@Serdeable
@Serializable
data class Contract(
    val data: List<ContractData>
)

@Serdeable
@Serializable
data class ContractData(
    val id: String,
    val factionSymbol: String,
    val type: String,
    val terms: ContractTerms,
    val accepted: Boolean,
    val fulfilled: Boolean,
    val expiration: String? = null,
    val deadlineToAccept: String? = null,
)

@Serdeable
@Serializable
data class ContractTerms(
    val deadline: String,
    val payment: ContractPayment,
    val deliver: List<ContractDeliver>
) {
  @Serdeable
  @Serializable
  data class ContractPayment(
    val onAccepted: Int,
    val onFulfilled: Int
  )

  @Serdeable
  @Serializable
  data class ContractDeliver(
    val tradeSymbol: String,
    val destinationSymbol: String,
    val unitsRequired: Int,
    val unitsFulfilled: Int
  )
}