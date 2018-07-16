package com.apollogic;

import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.util.ArrayList;

public class Utils {

    private static final Logger logger = Logger.getLogger(Utils.class);

    public static String applySha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getDifficultyString(int difficulty) {
        return new String(new char[difficulty]).replace('\0', '0');
    }

    public static Boolean isChainValid(ArrayList<Block> blockchain, int difficulty) {
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        for (int i = 1; i < blockchain.size(); i++) {
            currentBlock = blockchain.get(i);
            previousBlock = blockchain.get(i - 1);
            if (!previousBlock.getHash().equals(currentBlock.getPreviousHash())) {
                logger.error("Previous hashes are not equal");
                return false;
            }
            if (!currentBlock.getHash().substring(0, difficulty).equals(hashTarget)) {
                logger.error("This block hasn't been mined");
                return false;
            }
            if (!currentBlock.getHash().equals(currentBlock.calculateHash())) {
                logger.error("Current hashes are not equal");
                return false;
            }
        }
        return true;
    }

}


