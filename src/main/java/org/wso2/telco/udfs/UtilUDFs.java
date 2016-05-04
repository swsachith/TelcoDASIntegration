package org.wso2.telco.udfs;

public class UtilUDFs {

    /**
     * Returns the alternate value if the object is null.
     * @param object object for which null check should be done.
     * @param value alternate value to be returned if the object is null.
     * @return the alternate value if the object is null, or object otherwise.
     */
    public Object ifnull(Object object, Long value) {
        return (object == null) ? value : object;
    }
}
