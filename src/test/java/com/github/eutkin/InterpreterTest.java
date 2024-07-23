package com.github.eutkin;


import com.github.eutkin.value.BaseValue;
import com.github.eutkin.value.BooleanValue;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class InterpreterTest {


    @Test
    public void testFalseNumberExp() throws IOException {
        Interpreter interpreter = new Interpreter();
        BooleanValue value = interpreter.run("2 + 2 * 2 = 8");
        assertEquals(false, value.getValue());
    }

    @Test
    public void testTrueNumberExp() throws IOException {
        Interpreter interpreter = new Interpreter();
        BooleanValue value = interpreter.run("2 + 2 * 2 = 6");
        assertEquals(true, value.getValue());
    }

}