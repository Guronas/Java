package ru.ncedu.java.tasks;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Maks on 18.03.2016.
 */

public class Test {

    class Inner extends  Test{
    }

    public static void main(String[] args) throws IOException {

        System.out.printf("%s, %<s, %<s", "a", "b", "c");

    }

}