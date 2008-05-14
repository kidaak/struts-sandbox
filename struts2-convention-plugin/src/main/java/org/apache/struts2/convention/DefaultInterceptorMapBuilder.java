/*
 * $ID$
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
package org.apache.struts2.convention;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.AnnotationTools;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;

import com.opensymphony.xwork2.ObjectFactory;
import com.opensymphony.xwork2.config.Configuration;
import com.opensymphony.xwork2.config.ConfigurationException;
import com.opensymphony.xwork2.config.entities.InterceptorMapping;
import com.opensymphony.xwork2.config.entities.PackageConfig;
import com.opensymphony.xwork2.config.providers.InterceptorBuilder;
import com.opensymphony.xwork2.inject.Inject;
import com.opensymphony.xwork2.util.AnnotationUtils;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

/**
 * <p>
 * Builds interceptor mappings from annotations.
 * </p>
 */
public class DefaultInterceptorMapBuilder implements InterceptorMapBuilder {
	private static final Logger LOG = LoggerFactory
			.getLogger(DefaultInterceptorMapBuilder.class);

	private Configuration configuration;

	public List<InterceptorMapping> build(Class<?> actionClass, PackageConfig.Builder builder,
			String actionName, Action annotation) {
		List<InterceptorMapping> interceptorList = new ArrayList<InterceptorMapping>(
				10);

		//from @InterceptorRefs annotation
        InterceptorRefs interceptorRefs = AnnotationTools.findAnnotation(actionClass, InterceptorRefs.class);
        if (interceptorRefs != null)
            interceptorList.addAll(build(interceptorRefs.value(), actionName, builder));

        //from @InterceptorRef annotation
        InterceptorRef interceptorRef = AnnotationTools.findAnnotation(actionClass, InterceptorRef.class);
        if (interceptorRef != null)
            interceptorList.addAll(build(new InterceptorRef[] {interceptorRef}, actionName, builder));

		//from @Action annotation
		if (annotation != null) {
			InterceptorRef[] interceptors = annotation.interceptorRefs();
			if (interceptors != null) {
			    interceptorList.addAll(build(interceptors, actionName, builder));
			}
		}

		return interceptorList;
	}

	protected List<InterceptorMapping> build(InterceptorRef[] interceptors, String actionName, PackageConfig.Builder builder) {
	    List<InterceptorMapping> interceptorList = new ArrayList<InterceptorMapping>(
                10);
	    for (InterceptorRef interceptor : interceptors) {
            if (LOG.isTraceEnabled())
                LOG.trace("Adding interceptor [#0] to [#1]",
                        interceptor.value(), actionName);
            Map<String, String> params = createParameterMap(interceptor
                    .params());
            interceptorList.addAll(buildInterceptorList(builder,
                    interceptor, params));
        }

	    return interceptorList;
	}

	protected Map<String, String> createParameterMap(String[] parms) {
		Map<String, String> map = new HashMap<String, String>();
		int subtract = parms.length % 2;
		if (subtract != 0) {
			throw new ConfigurationException(
					"The InterceptorRef annotation uses an array of strings for"
							+ " parameters and they must be in a key value pair configuration. It looks like you"
							+ " have specified an odd number of parameters and there should only be an even number."
							+ " (e.g. params = {\"key\", \"value\"})");
		}

		for (int i = 0; i < parms.length; i = i + 2) {
			String key = parms[i];
			String value = parms[i + 1];
			map.put(key, value);
			if (LOG.isTraceEnabled()) {
				LOG.trace("Adding parmeter [#0:#1] to interceptor", key, value);
			}
		}

		return map;
	}

	protected List<InterceptorMapping> buildInterceptorList(
			PackageConfig.Builder builder, InterceptorRef ref, Map params) {
		return InterceptorBuilder.constructInterceptorReference(builder, ref
				.value(), params, builder.build().getLocation(),
				(ObjectFactory) configuration.getContainer().getInstance(
						ObjectFactory.class));
	}

	@Inject
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}
}
