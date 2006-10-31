/*
 * $Id$
 *
 * Copyright 2006 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.tiles.impl;

import org.apache.tiles.*;
import org.apache.tiles.context.TilesContextFactory;
import org.apache.tiles.definition.UrlDefinitionsFactory;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;

import java.util.Map;

/**
 * Basic implementation of the tiles container interface.
 * In most cases, this container will be customized by
 * injecting customized services, not necessarily by
 * override the container
 *
 * @since 2.0
 * @version $Rev$
 *
 */
public class BasicTilesContainer implements TilesContainer {

    /**
     * Log instance for all BasicTilesContainer
     * instances.
     */
    private static final Log LOG =
            LogFactory.getLog(BasicTilesContainer.class);

    private TilesApplicationContext context;
    private DefinitionsFactory definitionsFactory;
    private TilesContextFactory contextFactory;

    /**
     * Initialize the Container with the given configuration.
     *
     * @param context
     * @throws TilesException
     */
    public void init(TilesApplicationContext context) throws TilesException {
        checkInit();
        this.context = context;
        contextFactory.init(context.getInitParams());
        definitionsFactory.init(context.getInitParams());
    }

    /**
     * Determine whether or not the container has been
     * initialized. Utility method used for methods which
     * can not be invoked after the container has been
     * started.
     * @throws  IllegalStateException if the container has already been initialized.
     */
    private void checkInit() {
        if(context != null) {
            throw new IllegalStateException("Container allready initialized");
        }
    }

    /**
     * Standard Getter
     * @return the application context for this container.
     */
    public TilesApplicationContext getApplicationContext() {
        return context;
    }


    /**
     * Standard Getter
     * @return the definitions factory used by this container.
     */
    public DefinitionsFactory getDefinitionsFactory() {
        return definitionsFactory;
    }

    /**
     * Standard Setter
     * @param definitionsFactory the definitions factory for this instance.
     */
    public void setDefinitionsFactory(DefinitionsFactory definitionsFactory) {
        checkInit();
        this.definitionsFactory = definitionsFactory;
    }


    public TilesContextFactory getContextFactory() {
        checkInit();
        return contextFactory;
    }

    public void setContextFactory(TilesContextFactory contextFactory) {
        checkInit();
        this.contextFactory = contextFactory;
    }

    /**
     * Render the specified definition.
     *
     * @param request the TilesRequestContext
     * @throws TilesException
     */
    public void render(TilesRequestContext request)
            throws TilesException {
        String definitionName = request.getDefinitionName();
        ComponentDefinition definition =
                definitionsFactory.getDefinition(definitionName, request);

        if (definition == null) {
            if(LOG.isWarnEnabled()) {
                String message = "Unable to find the definition '" + definitionName + "'";
                LOG.warn(message);
            }
            throw new NoSuchDefinitionException(definitionName);
        }

        ComponentContext context = getComponentContext(request, definition);

        try {
            ViewPreparer preparer = definition.getOrCreatePreparer();
            if (preparer != null) {
                if (LOG.isDebugEnabled()) {
                    LOG.debug("Executing tiles preparer [" + preparer + "]");
                }
                preparer.execute(request, context);
            }

            String dispatchPath = definition.getPath();
            request.dispatch(dispatchPath);

        } catch (TilesException e) {
            throw e;
        } catch (Exception e) {
            // TODO it would be nice to make the preparer throw a more specific
            // tiles exception so that it doesn't need to be rethrown.
            throw new TilesException(e.getMessage(), e);
        }

    }

    protected ComponentContext getComponentContext(TilesRequestContext request,
                                                   ComponentDefinition definition) {
        ComponentContext context = ComponentContext.getContext(request);
        if (context == null) {
            context = new ComponentContext(definition.getAttributes());
            ComponentContext.setContext(context, request);
        } else {
            context.addMissing(definition.getAttributes());
        }
        return context;
    }

}
