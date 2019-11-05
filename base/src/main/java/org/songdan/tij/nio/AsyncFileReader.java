package org.songdan.tij.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author: Songdan
 * @create: 2019-08-18 16:33
 **/
public class AsyncFileReader {

    private String fileName;

    private AsynchronousFileChannel afc;

    public AsyncFileReader(String fileName) throws IOException {
        this.fileName = fileName;
        Path path = Paths.get(fileName);
        this.afc = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
    }

    public CompletableFuture<byte[]> read() {
        CompletableFuture<byte[]> future = new CompletableFuture<>();
        final byte[][] bys = {new byte[0]};
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        afc.read(byteBuffer, 0, byteBuffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer byteBuffer) {
                System.out.println("result = " + result);
                if (result == -1) {
                    future.complete(bys[0]);
                    return;
                }
                byteBuffer.flip();
                byte[] bytes = new byte[byteBuffer.limit()];
                byteBuffer.get(bytes);
                //扩容
                int oldLength = bys[0].length;
                int size = oldLength + bytes.length;
                bys[0] = Arrays.copyOf(bys[0], size);
                System.arraycopy(bytes,0,bys[0],oldLength,bytes.length);
                byteBuffer.clear();
                afc.read(byteBuffer,size,byteBuffer,this);
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {

            }
        });
        return future;
    }

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        System.out.println("begin:"+System.currentTimeMillis());
        CompletableFuture<byte[]> future = new AsyncFileReader("TIJ/base/hot.txt").read();
        System.out.println("return:"+System.currentTimeMillis());
        System.out.println(new String(future.get()));
        System.out.println("end:"+System.currentTimeMillis());
    }


}
