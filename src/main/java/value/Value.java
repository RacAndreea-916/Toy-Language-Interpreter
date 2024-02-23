package value;

import type.Type;

public interface Value {
    Type getType();

    Value deepCopy();

    boolean equals(Object other);
}
