package com.github.eutkin;

import basic.SimpleExpressionLexer;
import basic.SimpleExpressionParser;
import com.github.eutkin.value.BaseValue;
import com.github.eutkin.value.BooleanValue;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.littlebasic.InterpreterException;
import org.littlebasic.Memory;

import java.io.IOException;

/**
 * The entry point of the interpreter.
 */
public class Interpreter {

    private Memory memory;

    public BooleanValue run(String line) throws IOException {
        ANTLRInputStream input = new ANTLRInputStream(line);
        SimpleExpressionLexer lexer = new SimpleExpressionLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        SimpleExpressionParser parser = new SimpleExpressionParser(tokens);
        parser.setErrorHandler(new BailErrorStrategy());
        parser.removeErrorListeners();
        try {
            ParseTree tree = parser.line();
            memory = new Memory();
            SimpleVisitor eval = new SimpleVisitor(memory);
            eval.run(tree);
            return eval.value;
        } catch (InterpreterException | ParseCancellationException e) {
           e.printStackTrace();
        }
        return null;
    }

    public Memory getMemory() {
        return memory;
    }

    public void clear() {
        memory.free();
    }
}
