import java.io.IOException;
import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.*;

/**
 * Created by jsh3571 on 20/11/2016.
 */

public class FileWatcher {
    private Path dir;
    private WatchService watcher;

    public FileWatcher(String path) {
        // Set path, dir which will be watched.
        this.dir = Paths.get(path);

        try {
            // Create watcher who will watch the path, dir.
            this.watcher = FileSystems.getDefault().newWatchService();

            // Set kinds of the watching events.
            this.dir.register(watcher,
                    ENTRY_CREATE,
                    ENTRY_DELETE,
                    ENTRY_MODIFY);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void watch() {
        while (true) {
            // Wait for key to be signaled
            // where key contains all of events happened.
            WatchKey key = null;
            try {
                key = watcher.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind<?> kind = event.kind();

                if (kind == OVERFLOW) continue;

                Path fileName = (Path) event.context();
                Path child = dir.resolve(fileName);

                if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
                    System.out.println(child + "이 작성되었습니다.");
                } else if (kind == StandardWatchEventKinds.ENTRY_DELETE) {
                    System.out.println(child + "가 삭제되었습니다.");
                } else if (kind == StandardWatchEventKinds.ENTRY_MODIFY) {
                    System.out.println(child + "가 변경되었습니다.");
                }
            }

            boolean valid = key.reset();

            if (!valid) break;
        }
    }
}
