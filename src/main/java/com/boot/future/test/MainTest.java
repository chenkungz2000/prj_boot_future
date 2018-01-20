package com.boot.future.test;

import java.io.File;

public class MainTest {


    public static void pathPage()  {
        try{
            System.out.println(new File("").getAbsolutePath());
            System.out.println(new File("").getCanonicalPath());
        }catch (Exception e){

        }
    }

    public static void main(String[] aa) {
        System.out.println(System.getProperty("user.dir"));
        pathPage();
    }

}
