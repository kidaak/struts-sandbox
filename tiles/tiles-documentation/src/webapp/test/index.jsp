<%--
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
--%>
<h1>Tiles Simple Tests</h1>
<P> 
                                
         These tests allow quick checking of 
Tiles behaviors and Tiles instalation. </P>
<P>It is possible to run each test separately, or all in one. In this later 
case, test results and test codes are displayed in the same page. You can 
compare the resulting page with the quick overview page from main site to check 
if all is ok.</P>
<P>To run tests, follow the "all in one" link. If there is a problem, try 
each separate test in turn to localize which one fail.</P>
<UL>
  <LI><A href="testAll.jsp">All in one</A> (main code + test results)  
  <LI><A href="testBasic.jsp">basic tests</A>(no definitions) 
  <LI><A href="testIgnore.jsp">Test 'ignore' attribute, and basic errors processing</A> 
  <LI><A href="testList.jsp">test lists</A> 
  <LI><A href="testDefinitions.jsp">test definitions</A> 
  <LI><A href="testRole.jsp">test role (With Tomcat, use 'tomcat' as userid and password)</A> 
  <LI><A href="testStrutsAction.jsp">test struts action integration and behavior</A> 
  <LI><A href="testController.jsp">test tile controller calls</A> 
</LI></UL>