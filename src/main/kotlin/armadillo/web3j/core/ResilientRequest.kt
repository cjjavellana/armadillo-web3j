package armadillo.web3j.core

import org.web3j.protocol.Web3jService
import org.web3j.protocol.core.Request
import org.web3j.protocol.core.Response
import org.web3j.protocol.exceptions.ClientConnectionException
import java.io.IOException
import java.lang.Thread.sleep
import java.util.concurrent.CompletableFuture

/**
 * The ResilientRequest class executes the requested operation identified in the `method` parameter
 * against the first web3jInstance in the `web3jInstances` parameter.
 *
 * If it does not receive a successful response, it will failover to the next configured instance, then to the next
 * till it has gone through all of the `web3jInstances`
 *
 * It will throw a {@code ClientConnectionException} when it has gone through all of the instances unsuccessfully.
 */
class ResilientRequest<S, T : Response<*>> constructor(
        method: String,
        params: List<S>,
        private val web3jInstances: List<Web3jService>,
        private val type: Class<T>
) : Request<S, T>(method, params, web3jInstances.first(), type) {

    override fun sendAsync(): CompletableFuture<T> {
        return super.sendAsync()
    }

    override fun send(): T {
        for (web3jInstance in web3jInstances) {
            try {
                return web3jInstance.send(this, type)
            } catch (e: Exception) {
                when (e) {
                    is IOException, is ClientConnectionException -> {
                        sleep(DEFAULT_RETRY_DELAY_MILLISECOND)
                    }
                    // This is not something that we are expecting.
                    else -> throw e
                }
            }

        }

        throw ClientConnectionException("Unable to execute requested operation using any of the configured clients")
    }

    companion object {
        private const val DEFAULT_RETRY_DELAY_MILLISECOND = 100L
    }
}
