import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class MainServer {
    public static void main(String[] args) {
        System.out.printf("SERVER");

            try {
                DatagramSocket dSocket = new DatagramSocket(3000);
                byte[] bufferIn = new byte[256];
                DatagramPacket inputPacket = new DatagramPacket(bufferIn, bufferIn.length);
                while(bufferIn.length!=0) {
                    dSocket.receive(inputPacket);
                    String clientMessage = new String(inputPacket.getData(), 0, inputPacket.getLength());
                    System.out.println("\nClient: " + clientMessage);
                    InetAddress clientAddress = inputPacket.getAddress();
                    int port = inputPacket.getPort();
                    System.out.println("Scrivi il messaggio da voler mandare al client: ");
                    Scanner sc = new Scanner(System.in);
                    String message = sc.nextLine();
                    //prendo i bit, la lunghezza del messaggio, l'indirizzo del server e la porta
                    DatagramPacket outPacket = new DatagramPacket(message.getBytes(), message.length(), clientAddress, port);
                    dSocket.send(outPacket);
                }
                System.out.println("Fine trasmissione");
            } catch (IOException e) {
                System.out.println("Errore");
            }

    }
}