package com.github.eutkin;


import com.github.eutkin.value.BaseValue;
import org.junit.Test;

import java.io.IOException;

public class InterpreterTest {


    @Test
    public void test() throws IOException {
        Interpreter interpreter = new Interpreter();
        BaseValue value = interpreter.run("2 + 2 * 2 = 8");
        System.out.println(value);
    }

    @Test
    public void testString() throws IOException {
        Interpreter interpreter = new Interpreter();
        BaseValue value = interpreter.run("\"hello \" + \"world\"");
        System.out.println(value);
    }
}