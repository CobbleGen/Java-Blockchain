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
        BlockchainCommunicator b1 = new Node(2000);
        BlockchainCommunicator b2 = new Node(2005);
        BlockchainCommunicator b3 = new Node(2010);
    }
    
}
