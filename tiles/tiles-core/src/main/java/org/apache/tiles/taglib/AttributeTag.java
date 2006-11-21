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

package org.apache.tiles.taglib;

import org.apache.tiles.ComponentAttribute;
import org.apache.tiles.ComponentContext;
import org.apache.tiles.TilesException;
import org.apache.tiles.context.jsp.JspUtil;

import javax.servlet.jsp.JspException;
import java.io.IOException;
import java.util.Map;

/**
 * This is the tag handler for &lt;tiles:attribute&gt;, which defines an
 * attribute. If the attribute value is a template or a definition, its
 * attributes and its template can be overridden.
 *
 * @version $Rev$ $Date$
 */
public class AttributeTag extends RenderTagSupport {

    /**
     * Name to insert.
     */
    protected String name;

    protected String template;

    public void setName(String value) {
        this.name = value;
    }

    public String getName() {
        return name;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }


    public void release() {
        super.release();
        this.name = null;
        this.template = null;
    }

    protected void render() throws JspException, TilesException, IOException {
        ComponentContext context = container.getComponentContext(pageContext);
        ComponentAttribute attr = context.getAttribute(name);
        if (attr == null && ignore) {
            return;
        }

        if (attr == null) {
            throw new TilesException("Attribute '" + name + "' not found.");
        }

        container.render(pageContext, attr);
    }
}
