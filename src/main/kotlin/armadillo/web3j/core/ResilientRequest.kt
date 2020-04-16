package armadillo.web3j.core

import org.web3j.protocol.Web3jService
import org.web3j.protocol.core.Request
import org.web3j.protocol.core.Response
import org.web3j.protocol.exceptions.ClientConnectionException
import java.io.IOException
import java.util.concurrent.CompletableFuture

class ResilientRequest<S, T : Response<*>> constructor(
        method: String,
        params: List<S>,
        private val web3jInstances: List<Web3jService>,
        private val type: Class<T>
) : Request<S, T>() {

    init {
        setMethod(method)
        setParams(params)
    }

    override fun sendAsync(): CompletableFuture<T> {
        return super.sendAsync()
    }

    override fun send(): T {
        for (web3jInstance in web3jInstances) {
            try {
                return web3jInstance.send(this, type)
            } catch (e: Exception) {
                when(e) {
                    is IOException, is ClientConnectionException -> {
                        //TODO: Some delay (perhaps) jitters over here.
                    }
                    // I do not know how to handle this type of exception
                    else -> throw e
                }
            }

        }

        return super.send()
    }
}
