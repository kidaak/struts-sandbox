/*
 * $Id$
 *
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
 *
 */
package org.apache.tiles.access;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tiles.TilesApplicationContext;
import org.apache.tiles.TilesContainer;
import org.apache.tiles.TilesException;

import java.lang.reflect.Method;


/**
 * Provides static access to the tiles container.
 */
public class TilesAccess {

    private static final Log LOG =
        LogFactory.getLog(TilesAccess.class);

    public static final String CONTAINER_ATTRIBUTE =
        "org.apache.tiles.CONTAINER";

    private static final String CONTEXT_ATTRIBUTE =
        "org.apache.tiles.APPLICATION_CONTEXT";

    public static TilesContainer getContainer(Object context) {
        return (TilesContainer) getAttribute(context, CONTAINER_ATTRIBUTE);
    }

    public static void setContainer(Object context, TilesContainer container)
        throws TilesException {

        if (container == null) {
            if(LOG.isInfoEnabled()) {
                LOG.info("Removing TilesContext for context: " + context.getClass().getName());
            }
            removeAttribute(context, CONTAINER_ATTRIBUTE);
        }
        if (container != null && LOG.isInfoEnabled()) {
            LOG.info("Publishing TilesContext for context: " + context.getClass().getName());
        }
        setAttribute(context, CONTAINER_ATTRIBUTE, container);
    }

    public static TilesApplicationContext getApplicationContext(Object context) {
        TilesContainer container = getContainer(context);
        if (container != null) {
            return container.getApplicationContext();
        }
        return (TilesApplicationContext) getAttribute(context, CONTEXT_ATTRIBUTE);
    }

    private static Object getAttribute(Object context, String attributeName) {
        try {
            Class contextClass = context.getClass();
            Method attrMethod = contextClass.getMethod("getAttribute", String.class);
            return attrMethod.invoke(context, attributeName);
        } catch (Exception e) {
            LOG.warn("Unable to retrieve container from specified context: '" + context + "'", e);
            return null;
        }
    }

    private static void setAttribute(Object context, String name, Object value)
        throws TilesException {
        try {
            Class contextClass = context.getClass();
            Method attrMethod = contextClass.getMethod("setAttribute", String.class, Object.class);
            attrMethod.invoke(context, name, value);
        } catch (Exception e) {
            throw new TilesException("Unable to set attribute for specified context: '" + context + "'");
        }
    }

    private static void removeAttribute(Object context, String name)
        throws TilesException {
        try {
            Class contextClass = context.getClass();
            Method attrMethod = contextClass.getMethod("removeAttribute", String.class);
            attrMethod.invoke(context, name);
        } catch (Exception e) {
            throw new TilesException("Unable to remove attribute for specified context: '" + context + "'");
        }
    }
}
