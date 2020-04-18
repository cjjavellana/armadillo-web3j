package armadillo.web3j.core

import armadillo.web3j.ArmadilloWeb3j
import org.junit.jupiter.api.Test
import org.web3j.protocol.http.HttpService

class ArmadilloWeb3jTest {

    @Test
    fun test(){
        ArmadilloWeb3j.build(listOf(HttpService(),HttpService(),HttpService()))
    }

}