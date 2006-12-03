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
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>

<tiles:insertTemplate template="/tutorial/layout/classicLayout.jsp" flush="true">
  <tiles:put name="title"  value="My First Portal Page" />
  <tiles:put name="header" value="/tutorial/common/header.jsp" />
  <tiles:put name="footer" value="/tutorial/common/footer.jsp" />
  <tiles:put name="menu"   value="/tutorial/basic/menu.jsp" />
  <%-- <tiles:put name="menu"   value="/tutorial/common/menu.jsp" /> --%>
  <tiles:put name="body"   value="/tutorial/portal/portalBody.jsp" />
</tiles:insertTemplate>
