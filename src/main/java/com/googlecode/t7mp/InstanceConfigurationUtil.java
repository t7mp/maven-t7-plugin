package com.googlecode.t7mp;

import java.util.List;

import com.google.common.collect.Lists;

import edu.emory.mathcs.backport.java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * 
 * 
 * @author Joerg Bellmann
 *
 */
public final class InstanceConfigurationUtil {

    private InstanceConfigurationUtil() {
        //hide constructor
    }

    public static List<InstanceConfiguration> createInstanceConfigurations(DefaultMavenPluginContext mavenPluginContext) {
        validateInstanceCount(mavenPluginContext.getConfiguration().getInstanceCount());
        final int configuredHttpPort = mavenPluginContext.getConfiguration().getTomcatHttpPort();
        final int configuredShutdownPort = mavenPluginContext.getConfiguration().getTomcatShutdownPort();
        final String configuredCatalinaBasePath = mavenPluginContext.getConfiguration().getCatalinaBase().getAbsolutePath();
        List<InstanceConfiguration> configurations = Lists.newArrayList();
        for (int i = 0; i < mavenPluginContext.getConfiguration().getInstanceCount(); i++) {
            InstanceConfiguration coniguration = new InstanceConfiguration(i, new AtomicInteger(configuredHttpPort).addAndGet(i), new AtomicInteger(
                    configuredShutdownPort).addAndGet(i), configuredCatalinaBasePath + i);
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
            throw new TomcatSetupException("InstanceCount property should not be smaller than 1");
        }
    }

}
