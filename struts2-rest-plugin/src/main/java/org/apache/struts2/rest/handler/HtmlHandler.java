/*
 * $Id: Restful2ActionMapper.java 540819 2007-05-23 02:48:36Z mrdon $
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
package org.apache.struts2.rest.handler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;

/**
 * Handles HTML content, usually just a simple passthrough to the framework
 */
public class HtmlHandler implements ContentTypeHandler {

    public String fromObject(Object obj, String resultCode, OutputStream out) throws IOException {
        return resultCode;
    }

    public void toObject(InputStream in, Object target) {
    }

    public String getExtension() {
        return "xhtml";
    }

    public String getContentType() {
        return "application/xhtml+xml";
    }

}
