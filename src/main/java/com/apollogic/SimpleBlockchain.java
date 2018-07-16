package com.apollogic;

import com.google.gson.GsonBuilder;
import org.apache.log4j.Logger;

import java.util.ArrayList;

public class SimpleBlockchain {

    private static final Logger logger = Logger.getLogger(SimpleBlockchain.class);

    private static ArrayList<Block> blockchain = new ArrayList<Block>();
    private static int difficulty = 5;

    public static void main(String[] args) {

        blockchain.add(new Block("This is genesis Block #1", "0"));
        logger.info("Mining genesis Block 1");
        blockchain.get(0).mineBlock(difficulty);

        pressAnyKey();

        blockchain.add(new Block("This is Block #2", blockchain.get(blockchain.size() - 1).getHash()));
        logger.info("Mining Block 2 ");
        blockchain.get(1).mineBlock(difficulty);

        pressAnyKey();

        blockchain.add(new Block("This is Block #3", blockchain.get(blockchain.size() - 1).getHash()));
        logger.info("Mining Block 3... ");
        blockchain.get(2).mineBlock(difficulty);

        pressAnyKey();

        blockchain.add(new Block("This is Block #4", blockchain.get(blockchain.size() - 1).getHash()));
        logger.info("Mining Block 4... ");
        blockchain.get(3).mineBlock(difficulty);

        pressAnyKey();

        blockchain.add(new Block("This is Block #5", blockchain.get(blockchain.size() - 1).getHash()));
        logger.info("Mining Block 5... ");
        blockchain.get(4).mineBlock(difficulty);

        pressAnyKey();

        logger.info("Blockchain is valid: " + Utils.isChainValid(blockchain, difficulty));

        pressAnyKey();

        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        logger.info("Blockchain: ");
        logger.info(blockchainJson);

        pressAnyKey();

        logger.info("Modyfing data in Block #3");

        pressAnyKey();

        blockchain.get(2).setData("This is not Block #3. This is block #1");
        blockchain.get(2).calculateHash();
        String modifiedBlockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        logger.info("Blockchain: ");
        logger.info(modifiedBlockchainJson);

        pressAnyKey();

        logger.info("Blockchain is valid: " + Utils.isChainValid(blockchain, difficulty));
    }

    private static void pressAnyKey(){
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}


