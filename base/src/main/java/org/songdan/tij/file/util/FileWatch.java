package org.songdan.tij.file.util;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

/**
 * 监控文件服务
 *
 * @author song dan
 * @since 31 十月 2017
 */
public class FileWatch {

    public static void main(String[] args) throws IOException, InterruptedException {
        String fileName = "/Users/songdan/IdeaProjects/github/TIJ/base/src/main/java/org/songdan/tij/file/util";
        WatchService watchService = FileSystems.getDefault().newWatchService();

        Paths.get(fileName).register(watchService, StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_DELETE);

        while (true) {
            WatchKey key = watchService.take();
            List<WatchEvent<?>> watchEvents = key.pollEvents();
            for (WatchEvent<?> watchEvent : watchEvents) {

                WatchEvent.Kind<?> kind = watchEvent.kind();

                if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
                    System.out.println(System.currentTimeMillis());
                    System.out.println(String.format("<<<<<<file:%s create>>>>> ",watchEvent.context()));
                }
                if (kind == StandardWatchEventKinds.ENTRY_MODIFY) {
                    System.out.println(System.currentTimeMillis());
                    System.out.println(String.format("<<<<<file:%s modify>>>>>",watchEvent.context()));
                }
                if (kind == StandardWatchEventKinds.ENTRY_DELETE) {
                    System.out.println(System.currentTimeMillis());
                    System.out.println(String.format("<<<<<file:%s delete>>>>>",watchEvent.context()));
                }
            }
            key.reset();
        }

    }

}
