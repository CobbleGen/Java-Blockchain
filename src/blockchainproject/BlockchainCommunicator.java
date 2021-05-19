/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockchainproject;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author max.afklercker
 */
public abstract class BlockchainCommunicator implements Runnable {
    int port;
    String address;

    public BlockchainCommunicator(int port) {
        this.port = port;
        new Thread(this).start();
    }

    public int getPort() {
        return port;
    }

    @Override
    public void run() {
        //Try receive message§
        try {
            byte[] receivedMessage = new byte[256];
            DatagramSocket socket = new DatagramSocket(port);
            
            while(true) {
                DatagramPacket packet = new DatagramPacket(receivedMessage, receivedMessage.length);
                socket.receive(packet);
                String senderInfo = packet.getAddress().getHostName();
                String receivedMsg = new String(packet.getData(), 0, packet.getLength());
                int senderPort = Integer.parseInt(receivedMsg.substring(0, 4));
                receivedMsg = receivedMsg.substring(4);
                System.out.println(""+senderPort + ": " + receivedMsg);
            }
        } catch (IOException ex) {
            System.out.println("IO Exception. Message could not be received.");
        }
    }
    
    public void sendMessage(int port, String msg) {
        try {
            InetAddress inetAddress = InetAddress.getByName("localhost");
            DatagramSocket socket = new DatagramSocket();
            byte[] message = (""+port+msg).trim().getBytes();

            socket.send(new DatagramPacket(message, message.length, inetAddress, port));

        } catch (SocketException ex) {
            System.out.println("Socket Exception. Socket could not be created.");
        } catch (UnknownHostException ex) {
            System.out.println("Unknown Host. Socket could not be created.");
        } catch (IOException ex) {
            System.out.println("IO Exception. Message could not be sent.");
        }
    }
    
    public void execute(String command, int from) {
        
    }
    
    public abstract void localExecute(String command, int from);
}
