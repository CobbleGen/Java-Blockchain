/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockchainproject;

import java.util.ArrayList;

/**
 *
 * @author max.afklercker
 */
public class Node extends BlockchainCommunicator {
    ArrayList<Integer> nodes = new ArrayList<>();

    public Node(int port) {
        super(port);
    }

    @Override
    public void localExecute(String command, int from) {
        switch(command) {
            case "getNodes" :
                
                break;
        }
    }
    
}
