package com.github.eutkin;


import org.junit.Test;
import org.littlebasic.Value;

import java.io.IOException;

public class InterpreterTest {


    @Test
    public void test() throws IOException {
        Interpreter interpreter = new Interpreter();
        Value value = interpreter.run("2 + 2 * 2");
        System.out.println(value);
    }
}