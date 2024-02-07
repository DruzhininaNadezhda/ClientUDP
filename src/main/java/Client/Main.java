package Client;

import java.net.UnknownHostException;

public class Main {
    public static void main(String[] args) {

        new ClientForServer().start("localhost",22223);
    }
}
