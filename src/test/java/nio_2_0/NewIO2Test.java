package nio_2_0;


import junit.framework.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class NewIO2Test {





    /* Path introduced */

    @Test
    public void test1(){
        Path path = Paths.get("c:\\Temp\\test");
        Assert.assertEquals(2, path.getNameCount());
        Assert.assertEquals("test", String.valueOf(path.getFileName()));
        Assert.assertEquals("c:\\", String.valueOf(path.getRoot()));
        Assert.assertEquals("c:\\Temp", String.valueOf(path.getParent()));
    }


    /* Creating and deleting deleting file or directory */
    @Test
    public void test2(){
        try {
            Path path = Files.createFile(Paths.get("test"));
            Assert.assertTrue(Files.exists(path));
            Files.delete(path);
            Assert.assertFalse(Files.exists(path));
            Assert.assertFalse(Files.deleteIfExists(path));
        } catch (IOException e) {
            Assert.fail();
        }
    }






    /* File change notification allow to listen for changes in the file system */

    private AtomicBoolean done = new AtomicBoolean(false);
    @Test
    public void test3() {
        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();
            Path test = Paths.get("test");
            Files.createDirectory(test);
            test.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE);
            new Producer().start();
            int eventsArrived = 0;
            while(true){
                WatchKey key = watchService.take();
                for(WatchEvent<?> event : key.pollEvents()){
                    eventsArrived++;
                }
                key.reset();
                if(done.get()){
                    break;
                }
            }
            Assert.assertEquals(4, eventsArrived);
            Files.delete(test);
        } catch (IOException e) {
            Assert.fail();
        } catch (InterruptedException e) {
            Assert.fail();
        }
    }


    class Producer extends Thread {

        public void run() {
            try {
                Path file1 = Paths.get("test/test1.txt");
                Path file2 = Paths.get("test/test2.txt");
                Files.createFile(file1);
                Thread.sleep(1000);
                Files.createFile(file2);
                Thread.sleep(1000);
                Files.delete(file1);
                Thread.sleep(1000);
                Files.delete(file2);
                done.compareAndSet(false, true);
            } catch (IOException e) {
                e.printStackTrace();
                Assert.fail();
            } catch (InterruptedException e) {
                Assert.fail();
            }
        }

    }
}
