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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.faces.FactoryFinder;
import javax.faces.lifecycle.LifecycleFactory;
import javax.faces.lifecycle.Lifecycle;

/**
 * <p>Mock implementation of <code>LifecycleFactory</code>.</p>
 *
 * $Id$
 */

public class MockLifecycleFactory extends LifecycleFactory {


    // ------------------------------------------------------------ Constructors


    /**
     * <p>Return a default instance.</p>
     */
    public MockLifecycleFactory() {

        lifecycles = new HashMap();
        lifecycles.put(LifecycleFactory.DEFAULT_LIFECYCLE, new MockLifecycle());

    }


    // ----------------------------------------------------- Mock Object Methods


    // ------------------------------------------------------ Instance Variables


    private Map lifecycles = null;


    // ------------------------------------------------ LifecycleFactory Methods


    public void addLifecycle(String lifecycleId, Lifecycle lifecycle) {

        lifecycles.put(lifecycleId, lifecycle);

    }


    public Lifecycle getLifecycle(String lifecycleId) {

	return (Lifecycle) lifecycles.get(lifecycleId);

    }


    public Iterator getLifecycleIds() {

        return lifecycles.keySet().iterator();

    }

    
}
