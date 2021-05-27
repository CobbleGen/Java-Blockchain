/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockchainproject;

import java.security.*;

/**
 * https://docs.oracle.com/javase/tutorial/security/apisign/step3.html
 * @author max.afklercker
 */
public class Transaction {
    private int amount;
    private PublicKey belongsTo;
    private PublicKey sentFrom;
    private String transactionHash;

    public Transaction(int amount, PublicKey belongsTo, PublicKey sentFrom, PrivateKey privateKey) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException {
        this.amount = amount;
        this.belongsTo = belongsTo;
        this.sentFrom = sentFrom;
        Signature dsa = Signature.getInstance("SHA1withDSA", "SUN"); 
        dsa.initSign(privateKey);
    }
    
    
}
