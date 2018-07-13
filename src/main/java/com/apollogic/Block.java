package com.apollogic;

import org.apache.log4j.Logger;

import java.util.Date;

public class Block {

    public String hash;
    public String previousHash;
    private String data;
    private long timestamp;
    private int nonce;

    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timestamp = new Date().getTime();
        this.hash = calculateHash();
    }

    public String calculateHash() {
        return Utils.applySha256(
                previousHash +
                        Long.toString(timestamp) +
                        Integer.toString(nonce) +
                        data);
    }

    public void mineBlock(int difficulty) {
        Logger logger = Logger.getLogger(getClass().getName());
        String target = Utils.getDifficultyString(difficulty);
        while (!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        logger.info("Block mined : " + hash);
    }

    public void setData(String data) {
        this.data = data;
    }


}
