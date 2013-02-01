package underscore_numerals;


import junit.framework.Assert;
import org.junit.Test;

public class UnderscoreInNumeralsTest {


    /**
     * To increase readability of long numerals it is now possible to separate numerals with underscores
     */
    @Test
    public void test1(){


        int millions1 = 100000000;
        int millions2 = 100_000_000;

        Assert.assertEquals(millions1, millions2);

    }


}
