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
package org.apache.tiles.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.tiles.TilesContainer;
import org.apache.tiles.TilesException;
import org.apache.tiles.access.TilesAccess;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Tiles dispatching servlet.  Used to invoke
 * a definition directly.
 */
public class TilesDispatchServlet extends HttpServlet {

    private static final Log LOG =
        LogFactory.getLog(TilesDispatchServlet.class);

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException {

        TilesContainer container = TilesAccess.getContainer(getServletContext());
        try {
            String definition = getDefinitionName(req);
            if (LOG.isDebugEnabled()) {
                LOG.info("Dispatching to tile '" + definition + "'");
            }
            container.render(req, res, definition);
        } catch (TilesException e) {
            throw new ServletException("Error rendering tile.", e);
        }
    }

    protected String getDefinitionName(HttpServletRequest request) {
        String path = (String) request.getAttribute("javax.servlet.include.servlet_path");
        if (path == null) {
            path = request.getServletPath();
        }

        int start = path.startsWith("/") ? 1 : 0;
        int end = path.endsWith(".tiles") ? path.indexOf(".tiles") : path.length();

        return path.substring(start, end);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException {
        LOG.info("Tiles dispatch request received. Redirecting POST to GET.");
        doGet(req, res);
    }
}
