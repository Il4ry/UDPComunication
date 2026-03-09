package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class MainServer {
    public static void main(String[] args) {
        System.out.printf("SERVER");

        try {
            DatagramSocket dSocket = new DatagramSocket(3000);
            byte[] bufferIn = new byte[256];
            DatagramPacket inputPacket = new DatagramPacket(bufferIn, bufferIn.length);
            dSocket.receive(inputPacket);
            String message= new String (inputPacket.getData(), 0, inputPacket.getLength());
            System.out.println("\nClient: " + message);
            InetAddress clientAddress = inputPacket.getAddress();
            int port = inputPacket.getPort();
            DatagramPacket outPacket =  new DatagramPacket(message.getBytes(), message.length(), clientAddress, port);
            dSocket.send(outPacket);
            System.out.println("messaggio rinviato");
        } catch (IOException e) {
            System.out.println("Errore");
        }

    }
}