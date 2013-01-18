package net.openjdk.diamond;


import junit.framework.Assert;
import org.junit.Test;

import java.util.*;

public class NewDiamondOperatorTest {


    /**
     * JDK7 introduces simplified diamond operator, it is no longer required to
     * specify your generic types on both sides
     */
    @Test
    public void test1(){

        /* JDK6 */
        Map<String, List<String>> map0 = new TreeMap<String, List<String>>();

        /* JDK7 */
        Map<String, List<String>> map1 = new TreeMap<>();
        Map<Integer, Map<String, List<String>>> map2 = new HashMap<>();
        List<String> list1 = new LinkedList<>();

        list1.add("value");
        map1.put("key", list1);
        map2.put(1, map1);

        Assert.assertEquals("value", map2.get(1).get("key").get(0));
    }

}
