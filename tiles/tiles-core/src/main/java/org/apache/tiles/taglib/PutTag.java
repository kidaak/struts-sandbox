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

import org.apache.tiles.taglib.ContainerTagSupport;
import org.apache.tiles.taglib.ComponentConstants;
import org.apache.tiles.taglib.PutTagParent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Put an attribute in enclosing attribute container tag.
 * Enclosing attribute container tag can be : &lt;insert&gt; or &lt;definition&gt;.
 * Exception is thrown if no appropriate tag can be found.
 * Put tag can have following atributes :
 * <li>
 * <ul>name : Name of the attribute</ul>
 * <ul>value : value to put as attribute</ul>
 * <ul>type : value type. Only valid if value is a String and is set by
 * value="something" or by a bean.
 * Possible type are : string (value is used as direct string),
 * template (value is used as a page url to insert),
 * definition (value is used as a definition name to insert)</ul>
 * <ul>direct : Specify if value is to be used as a direct string or as a
 * page url to insert. This is another way to specify the type. It only apply
 * if value is set as a string, and type is not present.</ul>
 * <ul>beanName : Name of a bean used for setting value. Only valid if value is not set.
 * If property is specified, value come from bean's property. Otherwise, bean
 * itself is used for value.</ul>
 * <ul>beanProperty : Name of the property used for retrieving value.</ul>
 * <ul>beanScope : Scope containing bean. </ul>
 * <ul>role : Role to check when 'insert' will be called. If enclosing tag is
 * &lt;insert&gt;, role is checked immediately. If enclosing tag is
 * &lt;definition&gt;, role will be checked when this definition will be
 * inserted.</ul>
 * </li>
 * Value can also come from tag body. Tag body is taken into account only if
 * value is not set by one of the tag attributes. In this case Attribute type is
 * "string", unless tag body define another type.
 */
public class PutTag extends ContainerTagSupport implements ComponentConstants {

    private static final Log LOG = LogFactory.getLog(PutTag.class);

    /**
     * Name of attribute to put in component context.
     */
    protected String name = null;

    /**
     * Associated attribute value.
     */
    private Object value = null;

    /**
     * Requested type for the value.
     */
    private String type = null;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * Release all allocated resources.
     */
    public void release() {
        super.release();
        name = null;
        value = null;
        type = null;
    }

    /**
     * Save the body content of this tag (if any)
     *
     * @throws JspException if a JSP exception has occurred
     */
    public int doAfterBody() throws JspException {
        if (bodyContent != null) {
            value = bodyContent.getString();
            type = "string";
        }
        return (SKIP_BODY);
    }

    protected void execute() throws JspException {
        PutTagParent parent = (PutTagParent)
            TagSupport.findAncestorWithClass(this, PutTagParent.class);


        if (parent == null) {
            String message = "Error: enclosing tag '"
                +getParent().getClass().getName()+" doesn't accept 'put' tag.";
            LOG.error(message);
            throw new JspException(message);
        }

        parent.processNestedTag(this);
    }
}
