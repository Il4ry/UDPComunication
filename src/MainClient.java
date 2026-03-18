package client;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class MainClient {
    public static void main(String[] args) {
        System.out.println("CLIENT");

            try {
                InetAddress serverAddress = InetAddress.getLocalHost();
                System.out.println("Indirizzo del server trovato!");
                while (true) {
                    DatagramSocket dSocket = new DatagramSocket();
                    //si prepara il datagramma con i dati da inviare
                    System.out.println("Scrivi il messaggio da voler mandare al server: ");
                    Scanner sc = new Scanner(System.in);
                    String message = sc.nextLine();
                    //prendo i bit, la lunghezza del messaggio, l'indirizzo del server e la porta
                    DatagramPacket outPacket = new DatagramPacket(message.getBytes(), message.length(), serverAddress, 3000);
                    //si inviano i dati
                    dSocket.send(outPacket);
                    byte[] bufferIn = new byte[256];
                    DatagramPacket inputPacket = new DatagramPacket(bufferIn, bufferIn.length);
                    dSocket.receive(inputPacket);
                    String Servermessage = new String(inputPacket.getData(), 0, inputPacket.getLength());
                    System.out.println("Server: " + Servermessage);
                }
            }catch(UnknownHostException e){ //eccezione per input/output
                System.err.println("Indirizzo server non trovato");
            }catch (SocketException e) {
                System.err.println("Errore socket");
            }catch (IOException e) {
                System.err.println("Errore");
            }

    }
}