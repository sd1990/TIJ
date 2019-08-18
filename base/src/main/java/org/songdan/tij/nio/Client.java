package org.songdan.tij.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

/**
 * @author: Songdan
 * @create: 2019-08-17 21:42
 **/
public class Client {

    private int remotePort;

    private String remoteAddress;

    private SocketChannel socketChannel;

    public Client(int remotePort, String remoteAddress) throws IOException {
        this.remotePort = remotePort;
        this.remoteAddress = remoteAddress;
        this.socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
    }

    public void connect() throws IOException {
        socketChannel.connect(new InetSocketAddress(remoteAddress, remotePort));
    }

    public void close() throws IOException {
        socketChannel.close();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Client client = new Client(8080, "127.0.0.1");
        client.connect();
        TimeUnit.SECONDS.sleep(3);
        client.close();
    }
}
