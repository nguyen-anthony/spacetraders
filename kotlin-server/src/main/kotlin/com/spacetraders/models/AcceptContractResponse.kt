package com.spacetraders.models

data class AcceptContractResponse(
    val data: AcceptContractData
) {
    data class AcceptContractData(
        val contract: ContractData,
        val agent: AgentData,
    )
}
