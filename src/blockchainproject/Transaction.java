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
    private byte[] transactionHash;

    //Begins a new transaction, supplies all the info about the new transaction as well as
    //the private key needed to sign it and create the transaction hash
    //
    //TODO: Don't create this with an int amount, only portions or combinations of previous transactions
    public Transaction(int amount, PublicKey belongsTo, PublicKey sentFrom, PrivateKey privateKey) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, SignatureException {
        this.amount = amount;
        this.belongsTo = belongsTo;
        this.sentFrom = sentFrom;
        
        Signature dsa = Signature.getInstance("SHA1withDSA", "SUN"); 
        dsa.initSign(privateKey);
        dsa.update(getData());
        transactionHash = dsa.sign();
    }

    //Create a transaction object that someone has already signed (to add to your blockchain)
    //Just adds all the info, including the premade hash. This can be verified later using confirmTransaction()
    //
    //TODO: Make and throw an exception when creating the transaction if the signature doesn't verify
    //instead of having it in a separate function
    public Transaction(int amount, PublicKey belongsTo, PublicKey sentFrom, String transactionHash) {
        this.amount = amount;
        this.belongsTo = belongsTo;
        this.sentFrom = sentFrom;
        this.transactionHash = transactionHash.getBytes();
    }
    
    //Confirm that the transaction hash is verifiably signed by the person who should have sent the transaction
    public boolean confirmTransaction() throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, SignatureException {
        Signature sig = Signature.getInstance("SHA1withDSA", "SUN");
        sig.initVerify(sentFrom);
        sig.update(getData());
        return sig.verify(transactionHash);
    }
    
    public byte[] getData() {
        return (sentFrom.toString() + belongsTo.toString() + amount).getBytes();
    }

    public int getAmount() {
        return amount;
    }

    public PublicKey getBelongsTo() {
        return belongsTo;
    }

    public PublicKey getSentFrom() {
        return sentFrom;
    }

    public String getTransactionHash() {
        return transactionHash.toString();
    }
}
