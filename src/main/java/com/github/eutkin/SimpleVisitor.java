package com.github.eutkin;

import basic.LBExpressionParser;
import basic.SimpleExpressionBaseVisitor;
import basic.SimpleExpressionParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.littlebasic.Memory;
import org.littlebasic.Value;

import java.io.BufferedReader;
import java.io.PrintStream;

/**
 * The ANTLR visitor. This does the actual job of executing the program.
 */
public class SimpleVisitor extends SimpleExpressionBaseVisitor<Value> {

    private Memory memory;

    protected Value value;


    public SimpleVisitor(Memory memory) {
        this.memory = memory;
    }

    @Override
    public Value visit(ParseTree tree) {
        return super.visit(tree);
    }

    @Override
    public Value visitLine(SimpleExpressionParser.LineContext ctx) {
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
    public Value visitExp(SimpleExpressionParser.ExpContext ctx) {
        Value result = super.visitExp(ctx);
        this.value = result;
        return result;
    }


    @Override
    public Value visitString(SimpleExpressionParser.StringContext ctx) {
        String value = ctx.getText();
        return new Value(value.substring(1, value.length() - 1));
    }

    @Override
    public Value visitNumber(SimpleExpressionParser.NumberContext ctx) {
        return new Value(Long.parseLong(ctx.getText()));
    }

    @Override
    public Value visitId(SimpleExpressionParser.IdContext ctx) {
        String id = ctx.getText();
        return memory.get(id);
    }

    @Override
    public Value visitDivExpr(SimpleExpressionParser.DivExprContext ctx) {
        Value left = visit(ctx.expression(0));
        Value right = visit(ctx.expression(1));
        return left.div(right);
    }

    @Override
    public Value visitMulExpr(SimpleExpressionParser.MulExprContext ctx) {
        Value left = visit(ctx.expression(0));
        Value right = visit(ctx.expression(1));
       return left.mul(right);
    }

    @Override
    public Value visitAddSubExpr(SimpleExpressionParser.AddSubExprContext ctx) {
        Value left = visit(ctx.expression(0));
        Value right = visit(ctx.expression(1));
        if (ctx.op.getType() == LBExpressionParser.ADD) {
            return left.add(right);
        } else {
            return left.sub(right);
        }
    }


    @Override
    public Value visitRelExpr(SimpleExpressionParser.RelExprContext ctx) {
        Value left = visit(ctx.expression(0));
        Value right = visit(ctx.expression(1));
        switch (ctx.op.getType()) {
            case LBExpressionParser.GT:
                return left.gt(right);
            case LBExpressionParser.GTE:
                return left.gte(right);
            case LBExpressionParser.LT:
                return left.lt(right);
            case LBExpressionParser.LTE:
                return left.lte(right);
            case LBExpressionParser.EQ:
                return left.eq(right);
            default:
                return left.neq(right);
        }
    }


    @Override
    public Value visitNotExpr(SimpleExpressionParser.NotExprContext ctx) {
        Value value = visit(ctx.expression());
        return value.not();
    }

    @Override
    public Value visitAndExpr(SimpleExpressionParser.AndExprContext ctx) {
        Value left = visit(ctx.expression(0));
        Value right = visit(ctx.expression(1));
        return left.and(right);
    }

    @Override
    public Value visitOrExpr(SimpleExpressionParser.OrExprContext ctx) {
        Value left = visit(ctx.expression(0));
        Value right = visit(ctx.expression(1));
        return left.or(right);
    }

}
