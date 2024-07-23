package com.github.eutkin.value;

import java.util.Objects;

public class StringValue implements BaseValue {

    private final String value;

    public StringValue(String value) {
        this.value = value;
    }

    public StringValue add(StringValue right) {
        return new StringValue(this + right.value);
    }

    public BooleanValue eq(StringValue right) {
        boolean result = this.value.equalsIgnoreCase(right.value);
        return new BooleanValue(result);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringValue that = (StringValue) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
