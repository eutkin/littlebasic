package com.github.eutkin.value;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class NumberArrayValue implements BaseValue {

    private final List<NumberValue> array;

    private final Set<NumberValue> set;

    public NumberArrayValue(List<NumberValue> array) {
        this.array = array;
        this.set = new HashSet<>(array);
    }

    public boolean contains(NumberValue other) {
        return this.set.contains(other);
    }

    @Override
    public String toString() {
        return Objects.toString(this.array);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumberArrayValue that = (NumberArrayValue) o;
        return Objects.equals(array, that.array);
    }

    @Override
    public int hashCode() {
        return Objects.hash(array);
    }
}
