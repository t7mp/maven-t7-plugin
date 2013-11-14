/**
 * Copyright (C) 2010-2012 Joerg Bellmann <joerg.bellmann@googlemail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.googlecode.t7mp;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.common.collect.Lists;

/**
 * 
 * 
 * 
 * @author Joerg Bellmann
 * 
 */
public final class InstanceConfigurationUtil {

	private InstanceConfigurationUtil() {
		// hide constructor
	}

	public static List<InstanceConfiguration> createInstanceConfigurations(
			DefaultMavenPluginContext mavenPluginContext) {
		validateInstanceCount(mavenPluginContext.getConfiguration()
				.getInstanceCount());
		final int configuredHttpPort = mavenPluginContext.getConfiguration()
				.getTomcatHttpPort();
		final int configuredShutdownPort = mavenPluginContext
				.getConfiguration().getTomcatShutdownPort();
		final String configuredCatalinaBasePath = mavenPluginContext
				.getConfiguration().getCatalinaBase().getAbsolutePath();
		List<InstanceConfiguration> configurations = Lists.newArrayList();
		for (int i = 0; i < mavenPluginContext.getConfiguration()
				.getInstanceCount(); i++) {
			InstanceConfiguration coniguration = new InstanceConfiguration(i,
					new AtomicInteger(configuredHttpPort).addAndGet(i),
					new AtomicInteger(configuredShutdownPort).addAndGet(i),
					configuredCatalinaBasePath + i);
			configurations.add(coniguration);
		}
		// validate ports
		validatePorts(configurations);
		return configurations;
	}

	private static void validatePorts(List<InstanceConfiguration> configurations) {
		List<Integer> ports = Lists.newArrayList();
		for (InstanceConfiguration configuration : configurations) {
			if (ports.contains(new Integer(configuration.getHttpPort()))) {
				throw new TomcatSetupException("We have conflicting ports ...");
			} else {
				ports.add(new Integer(configuration.getHttpPort()));
			}
			// shutdown ports
			if (ports.contains(new Integer(configuration.getShutdownPort()))) {
				throw new TomcatSetupException("We have conflicting ports ...");
			} else {
				ports.add(new Integer(configuration.getShutdownPort()));
			}
		}
	}

	private static void validateInstanceCount(int instanceCount) {
		if (instanceCount < 1) {
			throw new TomcatSetupException(
					"InstanceCount property should not be smaller than 1");
		}
	}

}
