/*
 * $Id: ComponentDefinitions.java 307013 2005-10-07 04:49:50Z wsmoak $
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

package org.apache.tiles.test.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Sample servlet that includes a page specified by the <code>include</code>
 * init parameter.
 * 
 * @version $Rev: 307013 $ $Date: 2005-10-07 06:49:50 +0200 (ven, 07 ott 2005) $ 
 */
public class IncludingServlet extends HttpServlet {

    private String include;

    /**
     * Initializes the servlet, reading the <code>include</code> init parameter
     */
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        include = config.getInitParameter("include");
    }

    /**
     * Processes the request, including the specified page.
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(include).include(request, response);
    }
}
