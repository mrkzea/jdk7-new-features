package net.openjdk.string_in_switch_statement;


import junit.framework.Assert;
import org.junit.Test;

public class StringInSwitchTest {



    /**
     * switch-case statement previously supporting only primitives types now also works with String types
     *
     */
    @Test
    public void test1(){

        String code = "ONE";

        switch(code) {
            case "ZERO" : Assert.fail(); break;

            case "ONE" : break;

            case "TWO" : Assert.fail(); break;

            default : Assert.fail();
        }
    }
}
