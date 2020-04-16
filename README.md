# Armadillo Web3j
Armadillo, spanish for 'little armored one' is designed to provide resiliency feature 
over Ethereum's Web3j library. 

## Motivation
The current web3j library can only connect to a single node. Should that node fail for whatever
reason, the Dapp would not be able to failover to another running node (under your control, of course).

## Limitations
Armadillo-Web3j does not attempt to provide failover functionalities for all of Web3j's APIs. e.g. 

```java
miner.start(1);
...
miner.stop();
```

