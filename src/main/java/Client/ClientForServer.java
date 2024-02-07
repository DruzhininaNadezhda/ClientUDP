package Client;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class ClientForServer {
    private String message = "";
    public void message() { // для записи из консоли
        System.out.println("Введите сообщение");
        Scanner in = new Scanner(System.in);
        message = in.nextLine();
    }
    public void start(String host, int port ) {
        while (true) {
            message(); // запись из консоли
            if (!message.equals("")) {
                try (DatagramSocket datagramSocket = new DatagramSocket())//открывается подключение САМ выбирается свободный порт
                {
                    DatagramPacket datagramPacket = new DatagramPacket(message.getBytes(),message.getBytes().length); //сообщение в буфер
                    InetAddress address = InetAddress.getByName(host);
                    datagramPacket.setAddress(address);
                    datagramPacket.setPort(port);
                    datagramSocket.send(datagramPacket);

                    byte[] buffer = new byte[1024]; // приём ответа
                        DatagramPacket datagramPacketIN = new DatagramPacket(buffer, buffer.length);
                        datagramSocket.receive(datagramPacketIN);
                        System.out.println(new String(buffer, "UTF-8").trim());

                } catch (IOException e) {
                    System.out.println("Что-то с клиентом");
                    e.printStackTrace();
                }
            }
        }
    }
}
