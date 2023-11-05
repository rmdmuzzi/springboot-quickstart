package com.raymond.quickstart;

import java.util.Arrays;

/**
 * @author raymondmuzzi
 * @since 2023-10-29 13:53:58
 */
public class PropertyTest {
    public static void main(String[] args) {
        System.out.println("->" + Arrays.toString(args));
        String value = System.getProperty("key");
        System.out.println(value);
    }
}
