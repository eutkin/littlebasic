package com.github.eutkin;

import basic.SimpleExpressionBaseVisitor;
import basic.SimpleExpressionParser;
import com.github.eutkin.value.BaseValue;
import com.github.eutkin.value.BooleanValue;
import com.github.eutkin.value.NumberArrayValue;
import com.github.eutkin.value.NumberValue;
import com.github.eutkin.value.StringValue;
import org.antlr.v4.runtime.tree.ParseTree;
import org.littlebasic.Memory;

import java.util.stream.Collectors;

/**
 * The ANTLR visitor. This does the actual job of executing the program.
 */
public class SimpleVisitor extends SimpleExpressionBaseVisitor<BaseValue> {

    private Memory memory;

    private BooleanValue value;


    public SimpleVisitor(Memory memory) {
        this.memory = memory;
    }

    public BooleanValue run(ParseTree tree) {
        this.visit(tree);
        return this.value;

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
        BooleanValue result = (BooleanValue) super.visitExpression(ctx);
        this.value = result;
        return result;
    }


    @Override
    public BooleanValue visitStringEqExp(SimpleExpressionParser.StringEqExpContext ctx) {
        StringValue left = (StringValue) visitLeftOperand(ctx);
        StringValue right = (StringValue) visitRightOperand(ctx);
        return left.eq(right);
    }

    @Override
    public BaseValue visitNumberEqExp(SimpleExpressionParser.NumberEqExpContext ctx) {
        NumberValue left = (NumberValue) visitLeftOperand(ctx);
        NumberValue right = (NumberValue) visitRightOperand(ctx);
        return left.eq(right);
    }

    @Override
    public BooleanValue visitNumberInArrayExp(SimpleExpressionParser.NumberInArrayExpContext ctx) {
        NumberValue left = (NumberValue) this.visitLeftOperand(ctx);
        NumberArrayValue right = (NumberArrayValue) this.visitRightOperand(ctx);
        return left.in(right);
    }

    @Override
    public NumberArrayValue visitNumber_array_expression(SimpleExpressionParser.Number_array_expressionContext ctx) {
        return ctx.number_expression()
                .stream()
                .map(this::visit)
                .map(i -> (NumberValue) i)
                .collect(Collectors.collectingAndThen(Collectors.toList(), NumberArrayValue::new));
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
        StringValue left = (StringValue) visitLeftOperand(ctx);
        StringValue right = (StringValue) visitRightOperand(ctx);

        return left.add(right);
    }

    @Override
    public NumberValue visitNumberMulExp(SimpleExpressionParser.NumberMulExpContext ctx) {
        NumberValue left = (NumberValue) visitLeftOperand(ctx);
        NumberValue right = (NumberValue) visitRightOperand(ctx);
        return left.mul(right);
    }

    @Override
    public NumberValue visitNumberPluxExp(SimpleExpressionParser.NumberPluxExpContext ctx) {
        NumberValue left = (NumberValue) visitLeftOperand(ctx);
        NumberValue right = (NumberValue) visitRightOperand(ctx);
        return left.add(right);
    }

    private BaseValue visitLeftOperand(ParseTree tree) {
        return this.visit(tree.getChild(0));
    }

    private BaseValue visitRightOperand(ParseTree tree) {
        // 2 because skip operator
        return this.visit(tree.getChild(2));
    }





}
