package com.github.eutkin;

import basic.SimpleExpressionBaseVisitor;
import basic.SimpleExpressionParser;
import com.github.eutkin.value.BaseValue;
import com.github.eutkin.value.BooleanValue;
import com.github.eutkin.value.NumberValue;
import com.github.eutkin.value.StringValue;
import org.antlr.v4.runtime.tree.ParseTree;
import org.littlebasic.Memory;

/**
 * The ANTLR visitor. This does the actual job of executing the program.
 */
public class SimpleVisitor extends SimpleExpressionBaseVisitor<BaseValue> {

    private Memory memory;

    protected BaseValue value;


    public SimpleVisitor(Memory memory) {
        this.memory = memory;
    }

    @Override
    public BaseValue visit(ParseTree tree) {
        return super.visit(tree);
    }

    @Override
    public BaseValue visitLine(SimpleExpressionParser.LineContext ctx) {
        init();
        try {
            return super.visitLine(ctx);
        } finally {
            cleanup();
        }
    }


    private void init() {
    }

    private void cleanup() {

    }

    @Override
    public BaseValue visitExpression(SimpleExpressionParser.ExpressionContext ctx) {
        BaseValue result = super.visitExpression(ctx);
        this.value = result;
        return result;
    }


    @Override
    public BooleanValue visitStringEqExp(SimpleExpressionParser.StringEqExpContext ctx) {
        StringValue left = (StringValue) visit(ctx.string_expression(0));
        StringValue right = (StringValue) visit(ctx.string_expression(1));
        return left.eq(right);
    }

    @Override
    public BaseValue visitNumberEqExp(SimpleExpressionParser.NumberEqExpContext ctx) {
        NumberValue left = (NumberValue) visit(ctx.number_expression(0));
        NumberValue right = (NumberValue) visit(ctx.number_expression(1));
        return left.eq(right);
    }


    @Override
    public StringValue visitString(SimpleExpressionParser.StringContext ctx) {
        String value = ctx.getText();
        return new StringValue(value.substring(1, value.length() - 1));
    }

    @Override
    public NumberValue visitNumber(SimpleExpressionParser.NumberContext ctx) {
        return new NumberValue(Long.parseLong(ctx.getText()));
    }


    @Override
    public StringValue visitStringPlusExp(SimpleExpressionParser.StringPlusExpContext ctx) {
        StringValue left = (StringValue) visit(ctx.string_expression(0)) ;
        StringValue right = (StringValue) visit(ctx.string_expression(1));

        return left.add(right);
    }

    @Override
    public BaseValue visitNumberMulExp(SimpleExpressionParser.NumberMulExpContext ctx) {
        NumberValue left = (NumberValue) visit(ctx.number_expression(0));
        NumberValue right = (NumberValue) visit(ctx.number_expression(1));
        return left.mul(right);
    }

    @Override
    public BaseValue visitNumberPluxExp(SimpleExpressionParser.NumberPluxExpContext ctx) {
        NumberValue left = (NumberValue) visit(ctx.number_expression(0));
        NumberValue right = (NumberValue) visit(ctx.number_expression(1));
        return left.add(right);
    }





}
