package multicatch_exceptions;


import junit.framework.Assert;
import org.junit.Test;

import java.io.IOException;

public class MultiCatchTest {


    /**
     * To increase the readability of exception handling it is now possible to catch multiple exceptions
     * It is important that the exceptions are disjoint
     * http://www.oracle.com/technetwork/articles/java/java7exceptions-486908.html
     */
    @Test
    public void test1(){

        try{
            throwException1();
            throwException2();
        } catch(IOException | IndexOutOfBoundsException  e){
            /* Exception e will be of a type corresponding to the caught exception */
            Assert.assertTrue(e instanceof IndexOutOfBoundsException);
        }

    }

    private void throwException1() {
        throw new IndexOutOfBoundsException();
    }
    private void throwException2() throws IOException{
        throw new IOException("Error");
    }




    /**
     * Rethrow functionality was slightly enhanced
     */
    @Test
    public void test2_rethrow(){

        try{
            rethrow_Exception();
        }catch(IOException e){
            Assert.assertTrue(e.getMessage().equals("Error"));
            return;
        }
        Assert.fail();
    }

    private void rethrow_Exception() throws IOException{
        try {
            throw new IOException("Error");

        }catch(Exception exception){
            /* This would not be possible in JDK6 */
            throw exception;
        }
    }



}
