package armadillo.web3j.core

import org.web3j.protocol.Web3jService
import org.web3j.protocol.core.BatchRequest
import org.web3j.protocol.core.DefaultBlockParameter
import org.web3j.protocol.core.JsonRpc2_0Web3j
import org.web3j.protocol.core.Request
import org.web3j.protocol.core.methods.request.ShhFilter
import org.web3j.protocol.core.methods.request.ShhPost
import org.web3j.protocol.core.methods.request.Transaction
import org.web3j.protocol.core.methods.response.*
import org.web3j.protocol.core.methods.response.admin.AdminNodeInfo
import org.web3j.protocol.core.methods.response.admin.AdminPeers
import org.web3j.utils.Async
import org.web3j.utils.Numeric
import java.math.BigInteger
import java.util.concurrent.ScheduledExecutorService

class ResilientWeb3JsonRpc(
        private val web3jServices: List<Web3jService>,
        pollingInterval: Long,
        scheduledExecutorService: ScheduledExecutorService
) : JsonRpc2_0Web3j(web3jServices.first(), pollingInterval, scheduledExecutorService) {

    constructor(web3jServices: List<Web3jService>) : this(web3jServices,
            DEFAULT_BLOCK_TIME.toLong(), Async.defaultExecutorService())

    override fun web3ClientVersion(): Request<*, Web3ClientVersion> {
        return ResilientRequest(
                "web3_clientVersion",
                emptyList<String>(),
                web3jServices,
                Web3ClientVersion::class.java
        )
    }

    override fun web3Sha3(data: String?): Request<*, Web3Sha3> {
        return ResilientRequest(
                "web3_sha3", listOf(data), web3jServices, Web3Sha3::class.java
        )
    }

    override fun ethAccounts(): Request<*, EthAccounts> {
        return ResilientRequest(
                "eth_accounts", emptyList<String>(), web3jServices, EthAccounts::class.java
        )
    }

    override fun ethGetTransactionCount(address: String, defaultBlockParameter: DefaultBlockParameter): Request<*, EthGetTransactionCount> {
        return ResilientRequest(
                "eth_getTransactionCount",
                listOf(address, defaultBlockParameter.value),
                web3jServices,
                EthGetTransactionCount::class.java
        )
    }

    override fun ethGetTransactionReceipt(transactionHash: String?): Request<*, EthGetTransactionReceipt> {
        return ResilientRequest(
                "eth_getTransactionReceipt",
                listOf(transactionHash),
                web3jServices,
                EthGetTransactionReceipt::class.java
        )
    }

    override fun ethCompileSolidity(sourceCode: String?): Request<*, EthCompileSolidity> {
        return ResilientRequest(
                "eth_compileSolidity",
                listOf(sourceCode),
                web3jServices,
                EthCompileSolidity::class.java
        )
    }

    override fun ethCoinbase(): Request<*, EthCoinbase> {
        return ResilientRequest(
                "eth_coinbase", emptyList<String>(), web3jServices, EthCoinbase::class.java
        )
    }

    override fun ethGetUncleCountByBlockNumber(defaultBlockParameter: DefaultBlockParameter?): Request<*, EthGetUncleCountByBlockNumber> {
        return ResilientRequest(
                "eth_getUncleCountByBlockNumber",
                listOf(defaultBlockParameter!!.value),
                web3jServices,
                EthGetUncleCountByBlockNumber::class.java
        )
    }

    override fun ethGetTransactionByBlockHashAndIndex(blockHash: String?, transactionIndex: BigInteger?): Request<*, EthTransaction> {
        TODO("Not yet implemented")
    }

    override fun dbGetString(databaseName: String?, keyName: String?): Request<*, DbGetString> {
        TODO("Not yet implemented")
    }

    override fun shhVersion(): Request<*, ShhVersion> {
        TODO("Not yet implemented")
    }

    override fun adminNodeInfo(): Request<*, AdminNodeInfo> {
        TODO("Not yet implemented")
    }

    override fun shhAddToGroup(identityAddress: String?): Request<*, ShhAddToGroup> {
        TODO("Not yet implemented")
    }

    override fun ethGetFilterChanges(filterId: BigInteger?): Request<*, EthLog> {
        TODO("Not yet implemented")
    }

    override fun shhGetMessages(filterId: BigInteger?): Request<*, ShhMessages> {
        TODO("Not yet implemented")
    }

    override fun ethGetWork(): Request<*, EthGetWork> {
        TODO("Not yet implemented")
    }

    override fun dbPutHex(databaseName: String?, keyName: String?, dataToStore: String?): Request<*, DbPutHex> {
        TODO("Not yet implemented")
    }

    override fun ethCompileSerpent(sourceCode: String?): Request<*, EthCompileSerpent> {
        return ResilientRequest(
                "eth_compileSerpent",
                listOf(sourceCode),
                web3jServices,
                EthCompileSerpent::class.java
        )
    }

    override fun shhNewFilter(shhFilter: ShhFilter?): Request<*, ShhNewFilter> {
        return ResilientRequest(
                "shh_newFilter", listOf(shhFilter), web3jServices, ShhNewFilter::class.java
        )
    }

    override fun ethGetFilterLogs(filterId: BigInteger?): Request<*, EthLog> {
        TODO("Not yet implemented")
    }

    override fun ethGetUncleCountByBlockHash(blockHash: String?): Request<*, EthGetUncleCountByBlockHash> {
        TODO("Not yet implemented")
    }

    override fun shhUninstallFilter(filterId: BigInteger?): Request<*, ShhUninstallFilter> {
        TODO("Not yet implemented")
    }

    override fun ethGetBlockTransactionCountByHash(blockHash: String?): Request<*, EthGetBlockTransactionCountByHash> {
        TODO("Not yet implemented")
    }

    override fun ethNewBlockFilter(): Request<*, org.web3j.protocol.core.methods.response.EthFilter> {
        return ResilientRequest(
                "eth_newBlockFilter",
                emptyList<String>(),
                web3jServices,
                org.web3j.protocol.core.methods.response.EthFilter::class.java
        )
    }

    override fun netVersion(): Request<*, NetVersion> {
        return ResilientRequest(
                "net_version", emptyList<String>(), web3jServices, NetVersion::class.java
        )
    }

    override fun netPeerCount(): Request<*, NetPeerCount> {
        TODO("Not yet implemented")
    }

    override fun ethGetTransactionByHash(transactionHash: String?): Request<*, EthTransaction> {
        TODO("Not yet implemented")
    }

    override fun ethSign(address: String?, sha3HashOfDataToSign: String?): Request<*, EthSign> {
        TODO("Not yet implemented")
    }

    override fun ethNewFilter(ethFilter: org.web3j.protocol.core.methods.request.EthFilter): Request<*, EthFilter> {
        return ResilientRequest(
                "eth_newFilter",
                listOf(ethFilter),
                web3jServices,
                EthFilter::class.java
        )
    }

    override fun shhPost(shhPost: ShhPost?): Request<*, org.web3j.protocol.core.methods.response.ShhPost> {
        TODO("Not yet implemented")
    }

    override fun ethSendTransaction(transaction: Transaction?): Request<*, EthSendTransaction> {
        TODO("Not yet implemented")
    }

    override fun ethGasPrice(): Request<*, EthGasPrice> {
        TODO("Not yet implemented")
    }

    override fun ethCall(transaction: Transaction?, defaultBlockParameter: DefaultBlockParameter?): Request<*, EthCall> {
        TODO("Not yet implemented")
    }

    override fun newBatch(): BatchRequest {
        TODO("Not yet implemented")
    }

    override fun ethBlockNumber(): Request<*, EthBlockNumber> {
        TODO("Not yet implemented")
    }

    override fun ethSendRawTransaction(signedTransactionData: String?): Request<*, EthSendTransaction> {
        TODO("Not yet implemented")
    }

    override fun ethChainId(): Request<*, EthChainId> {
        TODO("Not yet implemented")
    }

    override fun shhHasIdentity(identityAddress: String?): Request<*, ShhHasIdentity> {
        TODO("Not yet implemented")
    }

    override fun ethGetBlockByNumber(defaultBlockParameter: DefaultBlockParameter?, returnFullTransactionObjects: Boolean): Request<*, EthBlock> {
        TODO("Not yet implemented")
    }

    override fun ethGetCompilers(): Request<*, EthGetCompilers> {
        TODO("Not yet implemented")
    }

    override fun ethUninstallFilter(filterId: BigInteger?): Request<*, EthUninstallFilter> {
        TODO("Not yet implemented")
    }

    override fun ethSyncing(): Request<*, EthSyncing> {
        TODO("Not yet implemented")
    }

    override fun ethEstimateGas(transaction: Transaction?): Request<*, EthEstimateGas> {
        TODO("Not yet implemented")
    }

    override fun shhNewGroup(): Request<*, ShhNewGroup> {
        TODO("Not yet implemented")
    }

    override fun ethSubmitWork(nonce: String?, headerPowHash: String?, mixDigest: String?): Request<*, EthSubmitWork> {
        TODO("Not yet implemented")
    }

    override fun ethHashrate(): Request<*, EthHashrate> {
        TODO("Not yet implemented")
    }

    override fun shhGetFilterChanges(filterId: BigInteger?): Request<*, ShhMessages> {
        TODO("Not yet implemented")
    }

    override fun ethGetBalance(address: String?, defaultBlockParameter: DefaultBlockParameter?): Request<*, EthGetBalance> {
        TODO("Not yet implemented")
    }

    override fun netListening(): Request<*, NetListening> {
        TODO("Not yet implemented")
    }

    override fun ethSubmitHashrate(hashrate: String?, clientId: String?): Request<*, EthSubmitHashrate> {
        TODO("Not yet implemented")
    }

    override fun ethGetLogs(ethFilter: org.web3j.protocol.core.methods.request.EthFilter): Request<*, EthLog> {
        TODO("Not yet implemented")
    }

    override fun ethGetBlockByHash(blockHash: String?, returnFullTransactionObjects: Boolean): Request<*, EthBlock> {
        TODO("Not yet implemented")
    }

    override fun ethGetCode(address: String?, defaultBlockParameter: DefaultBlockParameter?): Request<*, EthGetCode> {
        TODO("Not yet implemented")
    }

    override fun dbPutString(databaseName: String?, keyName: String?, stringToStore: String?): Request<*, DbPutString> {
        TODO("Not yet implemented")
    }

    override fun ethCompileLLL(sourceCode: String?): Request<*, EthCompileLLL> {
        TODO("Not yet implemented")
    }

    override fun ethGetBlockTransactionCountByNumber(defaultBlockParameter: DefaultBlockParameter?): Request<*, EthGetBlockTransactionCountByNumber> {
        TODO("Not yet implemented")
    }

    override fun ethNewPendingTransactionFilter(): Request<*, org.web3j.protocol.core.methods.response.EthFilter> {
        TODO("Not yet implemented")
    }

    override fun ethGetTransactionByBlockNumberAndIndex(defaultBlockParameter: DefaultBlockParameter?, transactionIndex: BigInteger?): Request<*, EthTransaction> {
        TODO("Not yet implemented")
    }

    override fun ethGetUncleByBlockHashAndIndex(blockHash: String, transactionIndex: BigInteger): Request<*, EthBlock> {
        return ResilientRequest(
                "eth_getUncleByBlockHashAndIndex",
                listOf(blockHash, Numeric.encodeQuantity(transactionIndex)),
                web3jServices,
                EthBlock::class.java
        )
    }

    override fun shhNewIdentity(): Request<*, ShhNewIdentity> {
        return ResilientRequest(
                "shh_newIdentity",
                emptyList<String>(),
                web3jServices,
                ShhNewIdentity::class.java
        )
    }

    override fun shutdown() {
        TODO("Not yet implemented")
    }

    override fun ethGetStorageAt(address: String?, position: BigInteger?, defaultBlockParameter: DefaultBlockParameter?): Request<*, EthGetStorageAt> {
        TODO("Not yet implemented")
    }

    override fun ethMining(): Request<*, EthMining> {
        TODO("Not yet implemented")
    }

    override fun ethGetUncleByBlockNumberAndIndex(defaultBlockParameter: DefaultBlockParameter, uncleIndex: BigInteger): Request<*, EthBlock> {
        return ResilientRequest(
                "eth_getUncleByBlockNumberAndIndex",
                listOf(defaultBlockParameter.value, Numeric.encodeQuantity(uncleIndex)),
                web3jServices,
                EthBlock::class.java
        )
    }

    override fun adminPeers(): Request<*, AdminPeers> {
        return ResilientRequest(
                "admin_peers", emptyList<Any>(), web3jServices, AdminPeers::class.java
        )
    }

    override fun ethProtocolVersion(): Request<*, EthProtocolVersion> {
        return ResilientRequest(
                "eth_protocolVersion",
                emptyList<String>(),
                web3jServices,
                EthProtocolVersion::class.java
        )
    }

    override fun dbGetHex(databaseName: String?, keyName: String?): Request<*, DbGetHex> {
        return ResilientRequest(
                "db_getHex", listOf(databaseName, keyName), web3jServices, DbGetHex::class.java
        )
    }

}