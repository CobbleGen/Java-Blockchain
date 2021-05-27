/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockchainproject;

/**
 *
 * @author max.afklercker
 */
public class Block {
    private String previousHash;
    private String hash;
    private int blockID;
    private Transaction[] data;
    private int nonce;

    public Block(String previousHash, int blockID, Transaction[] data) {
        this.previousHash = previousHash;
        this.blockID = blockID;
        this.data = data;
    }
    
    
}
