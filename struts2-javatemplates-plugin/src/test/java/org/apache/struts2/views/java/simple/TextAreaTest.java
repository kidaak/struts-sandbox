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
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.struts2.views.java.simple;

import org.apache.struts2.components.TextField;
import org.apache.struts2.components.UIBean;
import org.apache.struts2.components.TextArea;

public class TextAreaTest extends AbstractCommonAttributesTest {
    private TextArea tag;

    public void testRenderTextArea() {
        tag.setName("name");
        tag.setValue("val1");
        tag.setDisabled("true");
        tag.setReadonly("true");
        tag.setTabindex("1");
        tag.setId("id1");
        tag.setCssClass("class1");
        tag.setCssStyle("style1");
        tag.setTitle("title");
        tag.setRows("1");
        tag.setCols("2");


        tag.evaluateParams();
        map.putAll(tag.getParameters());
        theme.renderTag(getTagName(), context);
        theme.renderTag(getTagName() + "-close", context);
        String output = writer.getBuffer().toString();
        String expected = s("<textarea name='name' cols='2' rows='1' tabindex='1' id='id1' class='class1' style='style1' title='title'>val1</textarea>");
        assertEquals(expected, output);
    }

    public void testRenderTextAreaDefaults() {
        tag.setValue("val1");
        tag.setDisabled("true");
        tag.setReadonly("true");
        tag.setTabindex("1");
        tag.setId("id1");
        tag.setCssClass("class1");
        tag.setCssStyle("style1");
        tag.setTitle("title");


        tag.evaluateParams();
        map.putAll(tag.getParameters());
        theme.renderTag(getTagName(), context);
        theme.renderTag(getTagName() + "-close", context);
        String output = writer.getBuffer().toString();
        String expected = s("<textarea name='' cols='' rows='' tabindex='1' id='id1' class='class1' style='style1' title='title'>val1</textarea>");
        assertEquals(expected, output);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.tag = new TextArea(stack, request, response);
    }

    @Override
    protected UIBean getUIBean() {
        return tag;
    }

    @Override
    protected String getTagName() {
        return "textarea";
    }
}
