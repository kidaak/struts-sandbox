/*
 * Copyright 2004 The Apache Software Foundation.
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

package org.apache.shale.test.mock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.ServletContext;

/**
 * <p>Mock impementation of <code>Map</code> for the application scope
 * attributes managed by {@link MockExternalContext}.</p>
 *
 * $Id$
 */

class MockApplicationMap implements Map {


    // ------------------------------------------------------------ Constructors


    /**
     * <p>Construct a default instance.</p>
     */
    public MockApplicationMap(ServletContext context) {

        this.context = context;

    }


    // ----------------------------------------------------- Mock Object Methods


    // ------------------------------------------------------ Instance Variables


    private ServletContext context = null;
 

    // ------------------------------------------------------------- Map Methods


    public void clear() {

        Iterator keys = keySet().iterator();
        while (keys.hasNext()) {
            context.removeAttribute((String) keys.next());
        }

    }


    public boolean containsKey(Object key) {

        return context.getAttribute(key(key)) != null;

    }


    public boolean containsValue(Object value) {

        if (value == null) {
            return false;
        }
        Enumeration keys = context.getAttributeNames();
        while (keys.hasMoreElements()) {
            Object next = context.getAttribute((String) keys.nextElement());
            if (next == value) {
                return true;
            }
        }
        return false;

    }


    public Set entrySet() {

        Set set = new HashSet();
        Enumeration keys = context.getAttributeNames();
        while (keys.hasMoreElements()) {
            set.add(context.getAttribute((String) keys.nextElement()));
        }
        return set;

    }


    public boolean equals(Object o) {

        return context.equals(o);

    }


    public Object get(Object key) {

        return context.getAttribute(key(key));

    }


    public int hashCode() {

        return context.hashCode();

    }


    public boolean isEmpty() {

        return size() < 1;

    }


    public Set keySet() {

        Set set = new HashSet();
        Enumeration keys = context.getAttributeNames();
        while (keys.hasMoreElements()) {
            set.add(keys.nextElement());
        }
        return set;

    }


    public Object put(Object key, Object value) {

        if (value == null) {
            return remove(key);
        }
        String skey = key(key);
        Object previous = context.getAttribute(skey);
        context.setAttribute(skey, value);
        return previous;

    }


    public void putAll(Map map) {

        Iterator keys = map.keySet().iterator();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            context.setAttribute(key, map.get(key));
        }

    }


    public Object remove(Object key) {

        String skey = key(key);
        Object previous = context.getAttribute(skey);
        context.removeAttribute(skey);
        return previous;

    }


    public int size() {

        int n = 0;
        Enumeration keys = context.getAttributeNames();
        while (keys.hasMoreElements()) {
            keys.nextElement();
            n++;
        }
        return n;

    }


    public Collection values() {

        List list = new ArrayList();
        Enumeration keys = context.getAttributeNames();
        while (keys.hasMoreElements()) {
            list.add(context.getAttribute((String) keys.nextElement()));
        }
        return list;

    }


    private String key(Object key) {

        if (key == null) {
            throw new IllegalArgumentException();
        } else if (key instanceof String) {
            return (String) key;
        } else {
            return key.toString();
        }

    }


}
