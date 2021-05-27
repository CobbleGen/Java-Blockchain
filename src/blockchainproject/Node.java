/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockchainproject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author max.afklercker
 */
public class Node extends BlockchainCommunicator {
    ArrayList<Integer> nodes = new ArrayList<>();
    JFrame frame;
    JTextArea jta_nodeList;

    public Node(int port) {
        super(port);
        frame = new JFrame("Node for Blockchain: " + port);
        JPanel mp = new JPanel(new GridBagLayout());
        frame.add(mp);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1000,600));
        
        GridBagConstraints c = new GridBagConstraints();
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;
        
        JLabel headerLabel = new JLabel("Amazing blockchain node + miner application 3000");
        c.gridx = 0;
        c.gridwidth = 1;
        c.gridy = 0;
        mp.add(headerLabel, c);
        
        JButton btn_newMiner = new JButton("New Miner");
        c.gridx = 3;
        c.gridwidth = 1;
        c.gridy = 0;
        mp.add(btn_newMiner, c);
        btn_newMiner.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Node((int)(Math.random()*1000));
            }
        });
        
//        JButton btn_newWallet = new JButton("New Wallet");
//        c.gridx = 3;
//        c.gridwidth = 1;
//        c.gridy = 0;
//        mp.add(btn_newWallet, c);
//        btn_newMiner.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                new Node((int)(Math.random()*1000));
//            }
//        });
        
        JPanel jp1 = new JPanel();
        jp1.setBackground(Color.red);
        c.gridx = 0;
        c.gridwidth = 2;
        c.gridy = 1;
        mp.add(jp1, c);
        
        JPanel jp2 = new JPanel();
        jp2.setBackground(Color.green);
        c.gridx = 3;
        c.gridwidth = 1;
        c.gridy = 1;
        mp.add(jp2, c);
        
        jta_nodeList = new JTextArea();
        jp2.add(jta_nodeList);
        
        frame.pack();
        if(port !=2000) {
            sendMessage(2000, "attachNode()");
        } else {
            nodes.add(2000);
        }
    }


    @Override
    public void localExecute(String command, String args, int from) {
        switch(command) {
            case "receiveNodes" : //Receive all nodes from somewhere else and update full list
                nodes.clear();
                String[] str = args.split(",");
                for (int i = 1; i < str.length; i++)
                {
                    nodes.add(Integer.parseInt(str[i]));
                }
                updateUI();
                break;
                
            case "addNode" : //Add a new node to list
                nodes.add(Integer.parseInt(args));
                System.out.println(port + ": Added "+ args);
                updateUI();
                break;
                
            case "attachNode" : //Receive new node and send it to all others, also give node list to the new node
                sendToAll("addNode("+from+")");
                nodes.add(from);
                String nodeStr = "";
                for(int s: nodes) {
                    nodeStr = nodeStr + "," + s;
                }
                sendMessage(from, "receiveNodes("+nodeStr+")");
                updateUI();
                break;
        }
    }
    
    public void sendToAll(String message) {
        for(int p: nodes) {
            if(p != port) {
                sendMessage(p, message);
            }
        }
    }
    
    public void updateUI() {
        jta_nodeList.setText("");
        for (int p: nodes) {
            jta_nodeList.setText(jta_nodeList.getText() + String.valueOf(p) + "\n");
        }
    }
}
