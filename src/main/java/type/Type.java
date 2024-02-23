package type;

import value.Value;

public interface Type {

    Value defaultValue();

    boolean equals(Object other);

    Type deepCopy();
}
