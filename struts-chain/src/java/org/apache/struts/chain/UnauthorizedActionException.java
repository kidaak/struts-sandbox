/*
 * Copyright 2003,2004 The Apache Software Foundation.
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
package org.apache.struts.chain;

/**
 *  <p>Exception thrown when the chosen action mapping is not authorized
 *  for the current request.</p>
 *
 *@author     Don Brown
 *@version    $Rev$ $Date$
 */

public class UnauthorizedActionException extends Exception {

    /**  Constructor */
    public UnauthorizedActionException() {
        super();
    }

    /**
     *  Constructor.
     *
     *@param  message  The error or warning message.
     */
    public UnauthorizedActionException(String message) {
        super(message);
    }

}

