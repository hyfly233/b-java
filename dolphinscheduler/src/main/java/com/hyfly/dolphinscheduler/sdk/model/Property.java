package com.hyfly.dolphinscheduler.sdk.model;

import com.hyfly.dolphinscheduler.sdk.enums.DataType;
import com.hyfly.dolphinscheduler.sdk.enums.Direct;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * org.apache.dolphinscheduler.plugin.task.api.model.Property
 */
@Data
@Accessors(chain = true)
public class Property implements Serializable {

    @Serial
    private static final long serialVersionUID = -1L;
    /**
     * key
     */
    private String prop;

    /**
     * input/output
     */
    private Direct direct;

    /**
     * data type
     */
    private DataType type;

    /**
     * value
     */
    private String value;

    public Property() {
    }

    public Property(String prop, Direct direct, DataType type, String value) {
        this.prop = prop;
        this.direct = direct;
        this.type = type;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Property property = (Property) o;
        return Objects.equals(prop, property.prop)
                && Objects.equals(value, property.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(prop, value);
    }

    @Override
    public String toString() {
        return "Property{"
                + "prop='" + prop + '\''
                + ", direct=" + direct
                + ", type=" + type
                + ", value='" + value + '\''
                + '}';
    }

}
