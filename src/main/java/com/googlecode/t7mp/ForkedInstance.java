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

import com.googlecode.t7mp.scanner.ScannerSetup;
import com.googlecode.t7mp.steps.CopySetenvScriptStep;
import com.googlecode.t7mp.steps.StepSequence;
import com.googlecode.t7mp.util.SystemUtil;
import com.googlecode.t7mp.util.TomcatUtil;

/**
 * Holds the {@link Process} p for an Tomcat instance.
 * 
 * 
 * @author Joerg Bellmann
 *
 */
public class ForkedInstance implements Runnable {

    private Process p;
    private PluginLog log;
    private T7Configuration configuration;

    /**
     * Set up the instance.
     * 
     * @param mavenPluginContext
     */
    public void configureInstance(MavenPluginContext mavenPluginContext) {
        log = mavenPluginContext.getLog();
        configuration = mavenPluginContext.getConfiguration();
        getSetupStepSequence().execute(mavenPluginContext);
    }

    @Override
    public void run() {
        startTomcat();
    }

    private void startTomcat() {

        setStartScriptPermissions(TomcatUtil.getBinDirectory(configuration.getCatalinaBase()));

        ProcessBuilder processBuilder = new ProcessBuilder(getStartSkriptCommand());
        processBuilder.directory(TomcatUtil.getBinDirectory(configuration.getCatalinaBase()));
        processBuilder.redirectErrorStream(true);

        processBuilder.environment().putAll(configuration.getSystemProperties());

        int exitValue = -1;
        try {
            this.p = processBuilder.start();

            if (configuration.isTomcatSetAwait()) {
                final ForkedTomcatProcessShutdownHook shutdownHook = new ForkedTomcatProcessShutdownHook(this.p, log);
                ScannerSetup.configureScanners(shutdownHook, configuration, log);
                Runtime.getRuntime().addShutdownHook(shutdownHook);
            }

            InputStream is = this.p.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            do {
                line = getNextLine(br);
            } while (line != null);
            //
            exitValue = this.p.waitFor();
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        log.info("Exit-Value " + exitValue);
    }

    private String getNextLine(BufferedReader br) {
        String line;
        try {
            line = br.readLine();
            System.out.println(line);
        } catch (IOException e) {
            line = null;
        }
        return line;
    }

    private void setStartScriptPermissions(File binDirectory) {
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
            log.error(e.getMessage(), e);
            throw new TomcatSetupException(e.getMessage(), e);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new TomcatSetupException(e.getMessage(), e);
        }
        log.debug("SetStartScriptPermission return value " + exitValue);
    }

    protected StepSequence getSetupStepSequence() {
        StepSequence seq = new ForkedSetupSequence();
        seq.add(new CopySetenvScriptStep());
        return seq;
    }

    protected String[] getStartSkriptCommand() {
        if (SystemUtil.isWindowsSystem()) {
            return new String[] {"cmd", "/c", "catalina.bat", "run"};
        } else {
            return new String[] {"./catalina.sh", "run"};
        }
    }

}
