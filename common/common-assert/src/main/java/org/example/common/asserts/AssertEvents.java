package org.example.common.asserts;

import org.apache.commons.lang3.BooleanUtils;
import org.example.common.exception.NotFoundException;

import java.util.Optional;

public abstract class AssertEvents {

    private AssertEvents() {
    }

    @SuppressWarnings("unchecked")
    public static <T> T valueNull(Object object, String message) {
        if (object == null) {
            throw new NotFoundException(message);
        }
        return (T) object;
    }

    public static <T> T optionalNotNull(Optional<T> optional, String message) {
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new NotFoundException(message);
    }

    @SuppressWarnings("unchecked")
    public static void expressionTrue(Boolean expression, String message) {
        if (!BooleanUtils.isTrue(expression)) {
            throw new IllegalArgumentException(message);
        }
    }
}
