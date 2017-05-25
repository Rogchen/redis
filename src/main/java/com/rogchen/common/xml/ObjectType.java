/*******************************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *******************************************************************************/
package com.rogchen.common.xml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

/**
 * Utilities for analyzing and converting Object types in Java
 * Takes advantage of reflection
 */
public class ObjectType {

    private static Logger logger = LoggerFactory.getLogger(ObjectType.class);

    public static final Object NULL = new NullObject();

    public static final String LANG_PACKAGE = "java.lang."; // We will test both the raw value and this + raw value
    public static final String SQL_PACKAGE = "java.sql.";   // We will test both the raw value and this + raw value

    /**
     * Tests if a class properly implements the specified interface.
     * @param objectClass Class to test
     * @param interfaceObject to test against
     * @return true if interfaceObject is an interface of the objectClass
     */
    public static boolean interfaceOf(Class<?> objectClass, Object interfaceObject) {
        Class<?> interfaceClass = interfaceObject.getClass();

        return interfaceOf(objectClass, interfaceClass);
    }

    /**
     * Tests if an object properly implements the specified interface.
     * @param obj Object to test
     * @param interfaceObject to test against
     * @return true if interfaceObject is an interface of obj
     */
    public static boolean interfaceOf(Object obj, Object interfaceObject) {
        Class<?> interfaceClass = interfaceObject.getClass();

        return interfaceOf(obj, interfaceClass);
    }

    /**
     * Tests if an object properly implements the specified interface.
     * @param obj Object to test
     * @param interfaceClass Class to test against
     * @return true if interfaceClass is an interface of obj
     */
    public static boolean interfaceOf(Object obj, Class<?> interfaceClass) {
        Class<?> objectClass = obj.getClass();

        return interfaceOf(objectClass, interfaceClass);
    }

    /**
     * Tests if a class properly implements the specified interface.
     * @param objectClass Class to test
     * @param interfaceClass Class to test against
     * @return true if interfaceClass is an interface of objectClass
     */
    public static boolean interfaceOf(Class<?> objectClass, Class<?> interfaceClass) {
        while (objectClass != null) {
            Class<?>[] ifaces = objectClass.getInterfaces();

            for (Class<?> iface: ifaces) {
                if (iface == interfaceClass) return true;
            }
            objectClass = objectClass.getSuperclass();
        }
        return false;
    }

    /**
     * Tests if a class is a class of or a sub-class of the parent.
     * @param objectClass Class to test
     * @param parentObject Object to test against
     * @return true if objectClass is a class of or a sub-class of the parent
     */
    public static boolean isOrSubOf(Class<?> objectClass, Object parentObject) {
        Class<?> parentClass = parentObject.getClass();

        return isOrSubOf(objectClass, parentClass);
    }

    /**
     * Tests if an object is an instance of or a sub-class of the parent.
     * @param obj Object to test
     * @param parentObject Object to test against
     * @return true if obj is an instance of or a sub-class of the parent
     */
    public static boolean isOrSubOf(Object obj, Object parentObject) {
        Class<?> parentClass = parentObject.getClass();

        return isOrSubOf(obj, parentClass);
    }

    /**
     * Tests if an object is an instance of or a sub-class of the parent.
     * @param obj Object to test
     * @param parentClass Class to test against
     * @return true if obj is an instance of or a sub-class of the parent
     */
    public static boolean isOrSubOf(Object obj, Class<?> parentClass) {
        Class<?> objectClass = obj.getClass();

        return isOrSubOf(objectClass, parentClass);
    }

    /**
     * Tests if a class is a class of or a sub-class of the parent.
     * @param objectClass Class to test
     * @param parentClass Class to test against
     * @return true if objectClass is a class of or a sub-class of the parent
     */
    public static boolean isOrSubOf(Class<?> objectClass, Class<?> parentClass) {
        //Debug.logInfo("Checking isOrSubOf for [" + objectClass.getName() + "] and [" + objectClass.getName() + "]", module);
        while (objectClass != null) {
            if (objectClass == parentClass) return true;
            objectClass = objectClass.getSuperclass();
        }
        return false;
    }

    /**
     * Tests if a class is a class of a sub-class of or properly implements an interface.
     * @param objectClass Class to test
     * @param typeObject Object to test against
     * @return true if objectClass is a class of a sub-class of, or properly implements an interface
     */
    public static boolean instanceOf(Class<?> objectClass, Object typeObject) {
        Class<?> typeClass = typeObject.getClass();

        return instanceOf(objectClass, typeClass);
    }

    /**
     * Tests if an object is an instance of a sub-class of or properly implements an interface.
     * @param obj Object to test
     * @param typeObject Object to test against
     * @return true if obj is an instance of a sub-class of, or properly implements an interface
     */
    public static boolean instanceOf(Object obj, Object typeObject) {
        Class<?> typeClass = typeObject.getClass();

        return instanceOf(obj, typeClass);
    }

    /**
     * Tests if an object is an instance of a sub-class of or properly implements an interface.
     * @param obj Object to test
     * @param typeClass Class to test against
     * @return true if obj is an instance of a sub-class of typeClass
     */
    public static boolean instanceOf(Object obj, Class<?> typeClass) {
        if (obj == null) return true;
        Class<?> objectClass = obj.getClass();
        return instanceOf(objectClass, typeClass);
    }

    /**
     * Tests if a class is a class of a sub-class of or properly implements an interface.
     * @param objectClass Class to test
     * @param typeClass Class to test against
     * @return true if objectClass is a class or sub-class of, or implements typeClass
     */
    public static boolean instanceOf(Class<?> objectClass, Class<?> typeClass) {
        if (typeClass.isInterface() && !objectClass.isInterface()) {
            return interfaceOf(objectClass, typeClass);
        } else {
            return isOrSubOf(objectClass, typeClass);
        }
    }

    @SuppressWarnings("unchecked")
    public static boolean isEmpty(Object value) {
        if (value == null) return true;

        if (value instanceof String) return UtilValidate.isEmpty((String) value);
        if (value instanceof Collection) return UtilValidate.isEmpty((Collection<? extends Object>) value);
        if (value instanceof Map) return UtilValidate.isEmpty((Map<? extends Object, ? extends Object>) value);
        if (value instanceof CharSequence) return UtilValidate.isEmpty((CharSequence) value);
        //if (value instanceof IsEmpty) return UtilValidate.isEmpty((IsEmpty) value);

        // These types would flood the log
        // Number covers: BigDecimal, BigInteger, Byte, Double, Float, Integer, Long, Short
        if (value instanceof Boolean) return false;
        if (value instanceof Number) return false;
        if (value instanceof Character) return false;
        if (value instanceof java.sql.Timestamp) return false;

        logger.debug("In ObjectType.isEmpty(Object value) returning false for " + value.getClass() + " Object.");
        return false;
    }

    @SuppressWarnings("serial")
    public static final class NullObject implements Serializable {
        public NullObject() { }

        @Override
        public String toString() {
            return "ObjectType.NullObject";
        }

        @Override
        public int hashCode() {
            return toString().hashCode();
        }

        @Override
        public boolean equals(Object other) {
            if (other instanceof NullObject) {
                // should do equality of object? don't think so, just same type
                return true;
            } else {
                return false;
            }
        }
    }
}
