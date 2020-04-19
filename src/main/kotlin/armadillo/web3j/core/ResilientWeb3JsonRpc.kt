package armadillo.web3j.core

import org.web3j.protocol.Web3jService
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
import java.io.IOException
import java.io.Serializable
import java.math.BigInteger
import java.util.concurrent.ScheduledExecutorService

class ResilientWeb3JsonRpc(
        private val web3jServices: List<Web3jService>,
        pollingInterval: Long,
        private val scheduledExecutorService: ScheduledExecutorService
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

    override fun web3Sha3(data: String): Request<*, Web3Sha3> {
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

    override fun ethGetTransactionReceipt(transactionHash: String): Request<*, EthGetTransactionReceipt> {
        return ResilientRequest(
                "eth_getTransactionReceipt",
                listOf(transactionHash),
                web3jServices,
                EthGetTransactionReceipt::class.java
        )
    }

    override fun ethCompileSolidity(sourceCode: String): Request<*, EthCompileSolidity> {
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

    override fun ethGetUncleCountByBlockNumber(defaultBlockParameter: DefaultBlockParameter): Request<*, EthGetUncleCountByBlockNumber> {
        return ResilientRequest(
                "eth_getUncleCountByBlockNumber",
                listOf(defaultBlockParameter.value),
                web3jServices,
                EthGetUncleCountByBlockNumber::class.java
        )
    }

    override fun ethGetTransactionByBlockHashAndIndex(blockHash: String, transactionIndex: BigInteger): Request<*, EthTransaction> {
        return ResilientRequest(
                "eth_getTransactionByBlockHashAndIndex",
                listOf(blockHash, Numeric.encodeQuantity(transactionIndex)),
                web3jServices,
                EthTransaction::class.java
        )
    }

    override fun dbGetString(databaseName: String, keyName: String): Request<*, DbGetString> {
        return ResilientRequest(
                "db_getString",
                listOf(databaseName, keyName),
                web3jServices,
                DbGetString::class.java
        )
    }

    override fun shhVersion(): Request<*, ShhVersion> {
        return ResilientRequest(
                "shh_version", emptyList<String>(), web3jServices, ShhVersion::class.java
        )
    }

    override fun adminNodeInfo(): Request<*, AdminNodeInfo> {
        return ResilientRequest(
                "admin_nodeInfo", emptyList<Any>(), web3jServices, AdminNodeInfo::class.java
        )
    }

    override fun shhAddToGroup(identityAddress: String): Request<*, ShhAddToGroup> {
        return ResilientRequest(
                "shh_addToGroup",
                listOf(identityAddress),
                web3jServices,
                ShhAddToGroup::class.java
        )
    }

    override fun ethGetFilterChanges(filterId: BigInteger): Request<*, EthLog> {
        return ResilientRequest(
                "eth_getFilterChanges",
                listOf(Numeric.toHexStringWithPrefixSafe(filterId)),
                web3jServices,
                EthLog::class.java
        )
    }

    override fun shhGetMessages(filterId: BigInteger): Request<*, ShhMessages> {
        return ResilientRequest(
                "shh_getMessages",
                listOf(Numeric.toHexStringWithPrefixSafe(filterId)),
                web3jServices,
                ShhMessages::class.java
        )
    }

    override fun ethGetWork(): Request<*, EthGetWork> {
        return ResilientRequest(
                "eth_getWork", emptyList<String>(), web3jServices, EthGetWork::class.java
        )
    }

    override fun dbPutHex(databaseName: String, keyName: String, dataToStore: String): Request<*, DbPutHex> {
        return ResilientRequest(
                "db_putHex",
                listOf(databaseName, keyName, dataToStore),
                web3jServices,
                DbPutHex::class.java
        )
    }

    override fun ethCompileSerpent(sourceCode: String): Request<*, EthCompileSerpent> {
        return ResilientRequest(
                "eth_compileSerpent",
                listOf(sourceCode),
                web3jServices,
                EthCompileSerpent::class.java
        )
    }

    override fun shhNewFilter(shhFilter: ShhFilter): Request<*, ShhNewFilter> {
        return ResilientRequest(
                "shh_newFilter", listOf(shhFilter), web3jServices, ShhNewFilter::class.java
        )
    }

    override fun ethGetFilterLogs(filterId: BigInteger): Request<*, EthLog> {
        return ResilientRequest(
                "eth_getFilterLogs",
                listOf(Numeric.toHexStringWithPrefixSafe(filterId)),
                web3jServices,
                EthLog::class.java
        )
    }

    override fun ethGetUncleCountByBlockHash(blockHash: String): Request<*, EthGetUncleCountByBlockHash> {
        return ResilientRequest(
                "eth_getUncleCountByBlockHash",
                listOf(blockHash),
                web3jServices,
                EthGetUncleCountByBlockHash::class.java
        )
    }

    override fun shhUninstallFilter(filterId: BigInteger): Request<*, ShhUninstallFilter> {
        return ResilientRequest(
                "shh_uninstallFilter",
                listOf(Numeric.toHexStringWithPrefixSafe(filterId)),
                web3jServices,
                ShhUninstallFilter::class.java
        )
    }

    override fun ethGetBlockTransactionCountByHash(blockHash: String): Request<*, EthGetBlockTransactionCountByHash> {
        return ResilientRequest(
                "eth_getBlockTransactionCountByHash",
                listOf(blockHash),
                web3jServices,
                EthGetBlockTransactionCountByHash::class.java
        )
    }

    override fun ethNewBlockFilter(): Request<*, EthFilter> {
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
        return ResilientRequest(
                "net_peerCount", emptyList<String>(), web3jServices, NetPeerCount::class.java
        )
    }

    override fun ethGetTransactionByHash(transactionHash: String): Request<*, EthTransaction> {
        return ResilientRequest(
                "eth_getTransactionByHash",
                listOf(transactionHash),
                web3jServices,
                EthTransaction::class.java
        )
    }

    override fun ethSign(address: String, sha3HashOfDataToSign: String): Request<*, EthSign> {
        return ResilientRequest(
                "eth_sign",
                listOf(address, sha3HashOfDataToSign),
                web3jServices,
                EthSign::class.java
        )
    }

    override fun ethNewFilter(ethFilter: org.web3j.protocol.core.methods.request.EthFilter): Request<*, EthFilter> {
        return ResilientRequest(
                "eth_newFilter",
                listOf(ethFilter),
                web3jServices,
                EthFilter::class.java
        )
    }

    override fun shhPost(shhPost: ShhPost): Request<*, org.web3j.protocol.core.methods.response.ShhPost> {
        return ResilientRequest(
                "shh_post",
                listOf(shhPost),
                web3jServices,
                org.web3j.protocol.core.methods.response.ShhPost::class.java
        )
    }

    override fun ethSendTransaction(transaction: Transaction): Request<*, EthSendTransaction> {
        return ResilientRequest(
                "eth_sendTransaction",
                listOf(transaction),
                web3jServices,
                EthSendTransaction::class.java
        )
    }

    override fun ethGasPrice(): Request<*, EthGasPrice> {
        return ResilientRequest(
                "eth_gasPrice", emptyList<String>(), web3jServices, EthGasPrice::class.java
        )
    }

    override fun ethCall(transaction: Transaction, defaultBlockParameter: DefaultBlockParameter): Request<*, EthCall> {
        return ResilientRequest(
                "eth_call",
                listOf(transaction, defaultBlockParameter),
                web3jServices,
                EthCall::class.java
        )
    }

    override fun ethBlockNumber(): Request<*, EthBlockNumber> {
        return ResilientRequest(
                "eth_blockNumber", emptyList<String>(),
                web3jServices,
                EthBlockNumber::class.java)
    }

    override fun ethSendRawTransaction(signedTransactionData: String): Request<*, EthSendTransaction> {
        return ResilientRequest(
                "eth_sendRawTransaction",
                listOf(signedTransactionData),
                web3jServices,
                EthSendTransaction::class.java
        )
    }

    override fun ethChainId(): Request<*, EthChainId> {
        return ResilientRequest(
                "eth_chainId", emptyList<String>(), web3jServices, EthChainId::class.java
        )
    }

    override fun shhHasIdentity(identityAddress: String): Request<*, ShhHasIdentity> {
        return ResilientRequest(
                "shh_hasIdentity",
                listOf(identityAddress),
                web3jServices,
                ShhHasIdentity::class.java)
    }

    override fun ethGetBlockByNumber(defaultBlockParameter: DefaultBlockParameter, returnFullTransactionObjects: Boolean): Request<*, EthBlock> {
        return ResilientRequest<Serializable, EthBlock>(
                "eth_getBlockByNumber",
                listOf(defaultBlockParameter.value, returnFullTransactionObjects),
                web3jServices,
                EthBlock::class.java)
    }

    override fun ethGetCompilers(): Request<*, EthGetCompilers> {
        return ResilientRequest(
                "eth_getCompilers", emptyList<String>(),
                web3jServices,
                EthGetCompilers::class.java)
    }

    override fun ethUninstallFilter(filterId: BigInteger): Request<*, EthUninstallFilter> {
        return ResilientRequest(
                "eth_uninstallFilter",
                listOf(Numeric.toHexStringWithPrefixSafe(filterId)),
                web3jServices,
                EthUninstallFilter::class.java)
    }

    override fun ethSyncing(): Request<*, EthSyncing> {
        return ResilientRequest(
                "eth_syncing", emptyList<String>(), web3jServices, EthSyncing::class.java)
    }

    override fun ethEstimateGas(transaction: Transaction): Request<*, EthEstimateGas> {
        return ResilientRequest(
                "eth_estimateGas", listOf(transaction), web3jServices, EthEstimateGas::class.java)
    }

    override fun shhNewGroup(): Request<*, ShhNewGroup> {
        return ResilientRequest(
                "shh_newGroup", emptyList<String>(), web3jServices, ShhNewGroup::class.java)
    }

    override fun ethSubmitWork(nonce: String, headerPowHash: String, mixDigest: String): Request<*, EthSubmitWork> {
        return ResilientRequest(
                "eth_submitWork",
                listOf(nonce, headerPowHash, mixDigest),
                web3jServices,
                EthSubmitWork::class.java)
    }

    override fun ethHashrate(): Request<*, EthHashrate> {
        return ResilientRequest(
                "eth_hashrate", emptyList<String>(), web3jServices, EthHashrate::class.java)
    }

    override fun shhGetFilterChanges(filterId: BigInteger): Request<*, ShhMessages> {
        return ResilientRequest(
                "shh_getFilterChanges",
                listOf(Numeric.toHexStringWithPrefixSafe(filterId)),
                web3jServices,
                ShhMessages::class.java)
    }

    override fun ethGetBalance(address: String, defaultBlockParameter: DefaultBlockParameter): Request<*, EthGetBalance> {
        return ResilientRequest(
                "eth_getBalance",
                listOf(address, defaultBlockParameter.value),
                web3jServices,
                EthGetBalance::class.java)
    }

    override fun netListening(): Request<*, NetListening> {
        return ResilientRequest(
                "net_listening", emptyList<String>(), web3jServices, NetListening::class.java)
    }

    override fun ethSubmitHashrate(hashrate: String, clientId: String): Request<*, EthSubmitHashrate> {
        return ResilientRequest(
                "eth_submitHashrate",
                listOf(hashrate, clientId),
                web3jServices,
                EthSubmitHashrate::class.java)
    }

    override fun ethGetLogs(ethFilter: org.web3j.protocol.core.methods.request.EthFilter): Request<*, EthLog> {
        return ResilientRequest(
                "eth_getLogs", listOf(ethFilter), web3jServices, EthLog::class.java
        )
    }

    override fun ethGetBlockByHash(blockHash: String, returnFullTransactionObjects: Boolean): Request<*, EthBlock> {
        return ResilientRequest(
                "eth_getBlockByHash",
                listOf(blockHash, returnFullTransactionObjects),
                web3jServices,
                EthBlock::class.java
        )
    }

    override fun ethGetCode(address: String, defaultBlockParameter: DefaultBlockParameter): Request<*, EthGetCode> {
        return ResilientRequest(
                "eth_getCode",
                listOf(address, defaultBlockParameter.value),
                web3jServices,
                EthGetCode::class.java
        )
    }

    override fun dbPutString(databaseName: String, keyName: String, stringToStore: String): Request<*, DbPutString> {
        return ResilientRequest(
                "db_putString",
                listOf(databaseName, keyName, stringToStore),
                web3jServices,
                DbPutString::class.java
        )
    }

    override fun ethCompileLLL(sourceCode: String): Request<*, EthCompileLLL> {
        return ResilientRequest(
                "eth_compileLLL", listOf(sourceCode), web3jServices, EthCompileLLL::class.java
        )
    }

    override fun ethGetBlockTransactionCountByNumber(defaultBlockParameter: DefaultBlockParameter): Request<*, EthGetBlockTransactionCountByNumber> {
        return ResilientRequest(
                "eth_getBlockTransactionCountByNumber",
                listOf(defaultBlockParameter.value),
                web3jServices,
                EthGetBlockTransactionCountByNumber::class.java
        )
    }

    override fun ethNewPendingTransactionFilter(): Request<*, EthFilter> {
        return ResilientRequest(
                "eth_newPendingTransactionFilter",
                emptyList<String>(),
                web3jServices,
                EthFilter::class.java
        )
    }

    override fun ethGetTransactionByBlockNumberAndIndex(defaultBlockParameter: DefaultBlockParameter, transactionIndex: BigInteger): Request<*, EthTransaction> {
        return ResilientRequest(
                "eth_getTransactionByBlockNumberAndIndex",
                listOf(
                        defaultBlockParameter.value,
                        Numeric.encodeQuantity(transactionIndex)
                ),
                web3jServices,
                EthTransaction::class.java
        )
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
        scheduledExecutorService.shutdown()
        try {
            for (w in web3jServices) {
                w.close()
            }
        } catch (e: IOException) {
            throw Exception("Failed to close web3j service", e)
        }
    }

    override fun ethGetStorageAt(address: String, position: BigInteger, defaultBlockParameter: DefaultBlockParameter): Request<*, EthGetStorageAt> {
        return ResilientRequest(
                "eth_getStorageAt",
                listOf(
                        address,
                        Numeric.encodeQuantity(position),
                        defaultBlockParameter.value),
                web3jServices,
                EthGetStorageAt::class.java
        )
    }

    override fun ethMining(): Request<*, EthMining> {
        return ResilientRequest(
                "eth_mining", emptyList<String>(), web3jServices, EthMining::class.java
        )
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

    override fun dbGetHex(databaseName: String, keyName: String): Request<*, DbGetHex> {
        return ResilientRequest(
                "db_getHex", listOf(databaseName, keyName), web3jServices, DbGetHex::class.java
        )
    }

}