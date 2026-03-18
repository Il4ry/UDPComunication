import java.io.IOException;
import java.net.*;

public class MainServer {
    public static void main(String[] args) {
        System.out.printf("SERVER");

            try {
                DatagramSocket dSocket = new DatagramSocket(3000);
                byte[] bufferIn = new byte[256];
                DatagramPacket inputPacket = new DatagramPacket(bufferIn, bufferIn.length);
                while(bufferIn.length!=0) {
                    dSocket.receive(inputPacket); //metodo bloccante
                    String clientMessage = new String(inputPacket.getData(), 0, inputPacket.getLength());//offset: dove comincia la lettura del messaggio
                    System.out.println("\nClient: " + clientMessage);
                    InetAddress clientAddress = inputPacket.getAddress();
                    int port = inputPacket.getPort();
                    DatagramPacket clientPacket =  new DatagramPacket(clientMessage.getBytes(), clientMessage.length(), clientAddress, port);
                    dSocket.send(clientPacket);
                    System.out.println("messaggio rinviato");
                    /*System.out.println("Scrivi il messaggio da voler mandare al client: ");
                    Scanner sc = new Scanner(System.in);
                    String message = sc.nextLine();
                    //prendo i bit, la lunghezza del messaggio, l'indirizzo del server e la porta
                    DatagramPacket outPacket = new DatagramPacket(message.getBytes(), message.length(), clientAddress, port);
                    dSocket.send(outPacket);*/
                }
                System.out.println("Fine trasmissione");
            }catch(UnknownHostException e){ //eccezione per input/output
                System.err.println("Errore i/o");
            }catch (SocketException e) {
                System.err.println("Errore socket");
            } catch (IOException e) {
                System.out.println("Errore");
            }

    }
}