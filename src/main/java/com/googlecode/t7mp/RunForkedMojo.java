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

import java.io.File;
import java.util.List;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import com.googlecode.t7mp.steps.Context;

/**
 * 
 * Runs a Tomcat 7 (or 6) instance in an separate process (jvm), other than the 
 * maven build.
 * 
 * @goal run-forked
 * @requiresDependencyResolution test
 * 
 *
 */
public class RunForkedMojo extends AbstractT7TomcatMojo {

    private static final long SLEEPTIME = 1000;
    private Context context;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        context = buildParentContext();
        PreConditions.checkConfiguredTomcatVersion(context.getLog(), this.getTomcatVersion());

        DefaultMavenPluginContext mavenPluginContext = new DefaultMavenPluginContext(context, this);

        List<InstanceConfiguration> configurations = InstanceConfigurationUtil.createInstanceConfigurations(mavenPluginContext);

        printInstancesToStart(configurations);

        for (InstanceConfiguration configuration : configurations) {
            getLog().info("Starting Tomcat ...");
            try {
                mavenPluginContext.getConfiguration().setTomcatHttpPort(configuration.getHttpPort());
                mavenPluginContext.getConfiguration().setTomcatShutdownPort(configuration.getShutdownPort());
                mavenPluginContext.getConfiguration().setCatalinaBase(new File(configuration.getCatalinaBasePath()));
                ForkedInstance p = new ForkedInstance();
                p.configureInstance(mavenPluginContext);
                Thread t = new Thread(p);
                t.start();
                Thread.sleep(SLEEPTIME);
            } catch (Exception e) {
                throw new MojoExecutionException(e.getMessage(), e);
            }
        }

        if (mavenPluginContext.getConfiguration().isTomcatSetAwait()) {
            new ExecutionLock().lock();
        }
    }

    private void printInstancesToStart(List<InstanceConfiguration> configurations) {
        context.getLog().info(configurations.size() + " Instances Started");
        context.getLog().info("Configured instances to start ...");
        for (InstanceConfiguration configuration : configurations) {
            context.getLog().info(configuration.toString());
        }
        context.getLog().info("Starting instances ...");
    }
}
