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
public class BlockchainProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BlockchainCommunicator b1 = new BlockchainCommunicator(2000);
        BlockchainCommunicator b2 = new BlockchainCommunicator(2005);
        b1.sendMessage(2005, "Hejsan svejsan!");
        b2.sendMessage(2000, "sug min röv");
    }
    
}
