package com.github.eutkin.value;

import java.util.Objects;

public class NumberValue implements BaseValue {

    private final Long value;

    public NumberValue(Long value) {
        this.value = value;
    }

    public NumberValue add(NumberValue right) {
        return new NumberValue(this.value + right.value);
    }

    public NumberValue mul(NumberValue right) {
        return new NumberValue(this.value * right.value);
    }

    public BooleanValue eq(NumberValue right) {
        boolean result = Objects.equals(this.value, right.value);
        return new BooleanValue(result);
    }

    @Override
    public String toString() {
        return Objects.toString(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumberValue that = (NumberValue) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
