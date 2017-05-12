package org.songdan.tij.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * 单线程版Reactor模型
 * 
 * @author Songdan
 * @date 2017/5/12 16:13
 */
public class Reactor implements Runnable {

    private Selector selector;

    private ServerSocketChannel serverSocket;

    public Reactor(int port) throws IOException {
        selector = Selector.open();
        serverSocket = ServerSocketChannel.open();
        serverSocket.socket().bind(new InetSocketAddress(port));
        serverSocket.configureBlocking(false);
        SelectionKey selectionKey = serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        selectionKey.attach(new Acceptor());
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                for (SelectionKey selectionKey : selectionKeys) {
                    dispatch(selectionKey);
                }
                selectionKeys.clear();
            }
        }
        catch (IOException e) {
            // TODO dosomething
        }
    }

    private void dispatch(SelectionKey selectionKey) {
        Runnable r = (Runnable) selectionKey.attachment();
        if (r != null) {
            r.run();
        }
    }

    class Acceptor implements Runnable {

        @Override
        public void run() {
            try {
                SocketChannel socketChannel = serverSocket.accept();
                if (socketChannel != null) {
                    new Handler(selector, socketChannel);
                }
            }
            catch (IOException e) {
                // TODO dosomething
            }
        }
    }

    class Handler implements Runnable {

        final SocketChannel socket;

        final SelectionKey sk;

        private int MAXIN = 1024;

        private int MAXOUT = 1024;

        ByteBuffer input = ByteBuffer.allocate(MAXIN);

        ByteBuffer output = ByteBuffer.allocate(MAXOUT);

        static final int READING = 0, SENDING = 1;

        int state = READING;

        public Handler(Selector selector, SocketChannel socketChannel) throws IOException {
            socket = socketChannel;
            socketChannel.configureBlocking(false);
            sk = socket.register(selector, 0);
            sk.attach(this);
            sk.interestOps(SelectionKey.OP_READ);
            selector.wakeup();
        }

        boolean inputIsComplete() {
            /* ... */
            return true;
        }

        boolean outputIsComplete() {
            /* ... */
            return true;
        }

        void process() {
            /* ... */
        }

        @Override
        public void run() {
            try {
                if (state == READING)
                    read();
                else if (state == SENDING)
                    send();
            }
            catch (IOException ex) {
                /* ... */
            }
        }

        private void read() throws IOException {
            socket.read(input);
            if (inputIsComplete()) {
                process();
                state = SENDING;
                sk.interestOps(SelectionKey.OP_WRITE);
            }
        }

        private void send() throws IOException {
            socket.write(output);
            if (outputIsComplete()) {
                sk.cancel();
            }
        }

    }

}
