package org.pepo.record.commons;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

public class BaseVO implements Serializable {

    private static final long serialVersionUID = 811498375175340965L;

    protected String[] equalsHashCodeExcludedFieldNames() {
        return null;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, equalsHashCodeExcludedFieldNames());
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj, equalsHashCodeExcludedFieldNames());
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
