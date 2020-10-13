package com.analise.vendas.service;

import java.util.Collection;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.analise.vendas.exception.ProcessingApplicationException;

public abstract class AbstractService {

    protected Logger log = LoggerFactory.getLogger(this.getClass().getName());

    protected static <T> T assertNotNull(T object, String message, Object... args) {
        if (Objects.isNull(object)) {
            throw new ProcessingApplicationException(     args != null
                                          			   && args.length > 0
                                          			 ? String.format(message, args)
                                          			 : message);
        }
        return object;
    }

    protected static <T extends Collection<?>> T assertNotEmpty(T coll, String message, Object... args) {
        if (CollectionUtils.isEmpty(coll)) {
            throw new ProcessingApplicationException(     args != null
                                          			   && args.length > 0
                                          			 ? String.format(message, args)
                                          			 : message);
        }
        return coll;
    }

    protected static String assertNotEmpty(String str, String message, Object... args) {
        if (StringUtils.isEmpty(str)) {
            throw new ProcessingApplicationException(     args != null
                                          			   && args.length > 0
                                          			 ? String.format(message, args)
                                          			 : message);
        }
        return str;
    }

    protected static String assertNotBlank(String str, String message, Object... args) {
        if (StringUtils.isBlank(str)) {
            throw new ProcessingApplicationException(     args != null
            										   && args.length > 0
            										 ? String.format(message, args)
            										 : message);
        }
        return str;
    }

    protected static void assertNull(Object object, String message, Object... args) {
        if (Objects.nonNull(object)) {
            throw new ProcessingApplicationException(     args != null
                                          			   && args.length > 0
                                          			 ? String.format(message, args)
                                          			 : message);
        }
    }

    protected static void assertEmpty(Collection<?> coll, String message, Object... args) {
        if (CollectionUtils.isNotEmpty(coll)) {
            throw new ProcessingApplicationException(     args != null
                                          			   && args.length > 0
                                          			 ? String.format(message, args)
                                          			 : message);
        }
    }

    protected static void assertEmpty(String str, String message, Object... args) {
        if (StringUtils.isNotEmpty(str)) {
            throw new ProcessingApplicationException(     args != null
                                          			   && args.length > 0
                                          			 ? String.format(message, args)
                                          			 : message);
        }
    }

    protected static void assertBlank(String str, String message, Object... args) {
        if (StringUtils.isNotBlank(str)) {
            throw new ProcessingApplicationException(     args != null
                                          			   && args.length > 0
                                          			 ? String.format(message, args)
                                          			 : message);
        }
    }

    protected static void assertTrue(boolean expression, String message, Object... args) {
        if (!expression) {
            throw new ProcessingApplicationException(     args != null
                                          			   && args.length > 0
                                          			 ? String.format(message, args)
                                          			 : message);
        }
    }

    protected static void assertFalse(boolean expression, String message, Object... args) {
        if (expression) {
            throw new ProcessingApplicationException(     args != null
                                          			   && args.length > 0
                                          			 ? String.format(message, args)
                                          			 : message);
        }
    }
}