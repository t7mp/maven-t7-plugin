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

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

import com.googlecode.t7mp.steps.Context;
import com.googlecode.t7mp.util.SystemUtil;
import com.googlecode.t7mp.util.TomcatUtil;

/**
 * 
 * Stops the forked Tomcat instance.
 * 
 * @goal stop-forked
 * @requiresDependencyResolution runtime
 * 
 *
 */
public class StopForkedMojo extends AbstractT7TomcatMojo {

    private PluginLog log;
    private Context context;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        context = buildParentContext();
        this.log = new MavenPluginLog(this.getLog());
        log.info("running t7:stop-forked ...");

        DefaultMavenPluginContext mavenPluginContext = new DefaultMavenPluginContext(context, this);

        List<InstanceConfiguration> configurations = InstanceConfigurationUtil.createInstanceConfigurations(mavenPluginContext);

        for (InstanceConfiguration configuration : configurations) {
           setStartScriptPermissions(TomcatUtil.getBinDirectory(new File(configuration.getCatalinaBasePath())));
           ProcessBuilder processBuilder = new ProcessBuilder(getStopSkriptCommand());
           int exitValue = -1;
           try {
              File binDirectory = TomcatUtil.getBinDirectory(new File(configuration.getCatalinaBasePath()));
              Process p = processBuilder.directory(binDirectory).start();
              InputStream is = p.getInputStream();
              BufferedReader br = new BufferedReader(new InputStreamReader(is));
              String line;
              while ((line = br.readLine()) != null) {
                 log.info(line);
              }
              exitValue = p.waitFor();
           } catch (InterruptedException e) {
              e.printStackTrace();
           } catch (IOException e) {
              e.printStackTrace();
           }

           waitForTomcatShutdown(configuration.getId(), mavenPluginContext);
           log.info("Exit-Value ForkedTomcatShutdownHook " + exitValue);
        }
    }

   private void waitForTomcatShutdown(int id, DefaultMavenPluginContext mavenPluginContext) {
      int shutdownTimeout = mavenPluginContext.getConfiguration().getTomcatShutdownTimeout();
      try {
         log.info("Waiting up to " + shutdownTimeout + " seconds for tomcat instance " + id + " to stop.");
         File lockFile = new File(new File(System.getProperty("java.io.tmpdir")), "maven-t7-forked-mojo-" + id + ".tmp");
         int elapsedSeconds = 0;
         while (lockFile.exists() && elapsedSeconds < shutdownTimeout) {
            Thread.sleep(1000);
            elapsedSeconds++;
         }
         if(lockFile.exists()) {
            log.warn("Timed out waiting for tomcat to stop.");
            if(!lockFile.delete()) {
               log.error("Failed to delete lock file for tomcat instance " + id);
            }
         } else {
            log.info("Tomcat has stopped in " + elapsedSeconds + " seconds.");
         }
      } catch (InterruptedException e) {
         log.error(e.getMessage(), e);
      }
   }

    protected String[] getStopSkriptCommand() {
        if (SystemUtil.isWindowsSystem()) {
            return new String[] {"cmd", "/c", "catalina.bat", "stop"};
        } else {
            return new String[] {"./catalina.sh", "stop"};
        }
    }

    protected void setStartScriptPermissions(File binDirectory) {
        log.debug("set permissions ...");
        if (SystemUtil.isWindowsSystem()) {
            // do we have filepermissions on windows
            return;
        }
        ProcessBuilder processBuilder = new ProcessBuilder("chmod", "755", "catalina.sh", "setclasspath.sh", "startup.sh", "shutdown.sh");
        processBuilder.directory(binDirectory);
        processBuilder.redirectErrorStream(true);
        int exitValue = -1;
        try {
            Process p = processBuilder.start();
            exitValue = p.waitFor();
        } catch (InterruptedException e) {
            getLog().error(e.getMessage(), e);
            throw new TomcatSetupException(e.getMessage(), e);
        } catch (IOException e) {
            getLog().error(e.getMessage(), e);
            throw new TomcatSetupException(e.getMessage(), e);
        }
        log.debug("SetStartScriptPermission return value " + exitValue);
    }

}
