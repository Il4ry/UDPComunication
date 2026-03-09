package client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class MainClient {
    public static void main(String[] args) {
        System.out.println("CLIENT");
        try {
            InetAddress serverAddress = InetAddress.getLocalHost();
            System.out.println("Indirizzo del server trovato!");
            DatagramSocket dSocket = new DatagramSocket();
            //si prepara il datagramma con i dati da inviare
            String message ="ciao";
            //prendo i bit, la lunghezza del messaggio, l'indirizzo del server e la porta
            DatagramPacket outPacket = new DatagramPacket(message.getBytes(), message.length(), serverAddress, 3000);
            //si inviano i dati
            dSocket.send(outPacket);
            byte[] bufferIn = new byte[256];
            DatagramPacket inputPacket = new DatagramPacket(bufferIn, bufferIn.length);
            dSocket.receive(inputPacket);
            String Servermessage= new String (inputPacket.getData(), 0, inputPacket.getLength());
            System.out.println(Servermessage);
        }catch(IOException e){
            System.out.println("Errore");
        }

    }
}