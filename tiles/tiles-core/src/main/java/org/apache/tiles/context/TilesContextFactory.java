/*
 * $Id$
 *
 * Copyright 1999-2006 The Apache Software Foundation.
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
package org.apache.tiles.context;

import org.apache.tiles.TilesApplicationContext;
import org.apache.tiles.TilesRequestContext;

import java.util.Map;

/**
 * Creates an instance of the appropriate TilesApplicationContext implementation.
 *
 * @version $Id$
 * @since Sep 21, 2006
 */
public interface TilesContextFactory {

    /**
     * Initialize the factory
     */
    void init(Map configurationParameters);

    /**
     * Create a TilesApplicationContext for the given context.
     *
     * @param context
     * @return TilesApplicationContext
     */
    TilesApplicationContext createApplicationContext(Object context);

    /**
     * Create a TilesRequestContext for the given context,
     * request, and response.
     * @param context
     * @param request
     * @param response
     * @return  TilesRequestContext
     */
    TilesRequestContext createRequestContext(Object context, Object request, Object response);
}
