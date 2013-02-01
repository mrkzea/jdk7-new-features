package autoclosable;


import junit.framework.Assert;
import org.junit.Test;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class TryWithResourcesTest {


    /**
     * Any resource declared within try block will be closed automatically given it implements
     * AutoClosable interface.
     */
    @Test
    public void test1(){

        OutputStream closed = null;

        try (
                OutputStream os = new FileOutputStream("SomeFile");
                DataOutputStream dos = new DataOutputStream(os)
             ){

            dos.write(1);
            closed = os;

        } catch(IOException e){
            e.printStackTrace();
        }


        try {

            closed.write(2);

        } catch (IOException e) {
            Assert.assertTrue(e.getMessage().equals("Stream Closed"));
        }


    }
}
