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
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.startup.Bootstrap;

import com.google.common.collect.Lists;
import com.googlecode.t7mp.scanner.Scanner;
import com.googlecode.t7mp.scanner.ScannerSetup;
import com.googlecode.t7mp.steps.CopyJuliJarStep;
import com.googlecode.t7mp.steps.StepSequence;
import com.googlecode.t7mp.util.CatalinaOutPrintStream;

/**
 * Holds a {@link Bootstrap} instance (Tomcat-Instance).
 * 
 * @author Joerg Bellmann
 *
 */
public class BootstrapHolder {

    // the tomcat
    private Bootstrap bootstrap;

    private CatalinaOutPrintStream catalinaOutPrintStream;
    private PluginLog log;
    private T7Configuration configuration;

    public void startBootstrapInstance(MavenPluginContext pluginContext) {
        this.log = pluginContext.getLog();
        this.configuration = pluginContext.getConfiguration();
        List<Stoppable> stoppables = Lists.newArrayList();
        getSetupStepSequence().execute(pluginContext);

        PrintStream originalSystemErr = System.err;

        bootstrap = getBootstrap();
        log.info("Starting Tomcat ...");
        try {
            File catalinaout = new File(configuration.getCatalinaBase(), "/logs/catalina.out");
            CatalinaOutPrintStream catalinaOutputStream = new CatalinaOutPrintStream(originalSystemErr, new FileOutputStream(catalinaout),
                    configuration.isSuspendConsoleOutput());
            System.setErr(catalinaOutputStream);
            bootstrap.init();
            final BootstrapShutdownHook shutdownHook = new BootstrapShutdownHook();
            List<Stoppable> stoppableScanner = ScannerSetup.configureScanners(shutdownHook, configuration, log);
            stoppables.addAll(stoppableScanner);
                bootstrap.start();
                Runtime.getRuntime().addShutdownHook(shutdownHook);
                log.info("Tomcat started");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        stoppables.add(new StoppableBootstrapAdapter(bootstrap));
        pluginContext.getMojo().getPluginContext().put(AbstractT7BaseMojo.T7_BOOTSTRAP_CONTEXT_ID, stoppables);
    }
    
    protected StepSequence getSetupStepSequence() {
        StepSequence seq = new MavenTomcatSetupSequence();
        seq.add(new CopyJuliJarStep());
        return seq;
    }

    protected Bootstrap getBootstrap() {
        return new Bootstrap();
    }

    
    
    /**
     * ShutdownHook for stopping the {@link Bootstrap} instance.
     * 
     * @author jbellmann
     *
     */
    final class BootstrapShutdownHook extends Thread implements ShutdownHook {

        private static final int SLEEPTIME = 1000;
        private final List<Scanner> scanners = new ArrayList<Scanner>();

        @Override
        public void addScanner(Scanner scanner) {
            this.scanners.add(scanner);
        }

        @Override
        public void run() {
            log.info("Stopping Tomcat ...");
            stopScanners();
            if (bootstrap != null) {
                try {
                    bootstrap.stop();
                    bootstrap = null;
                    log.info("Tomcat stopped");
                    Thread.sleep(SLEEPTIME);
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }
            if (catalinaOutPrintStream != null) {
                catalinaOutPrintStream.flush();
                catalinaOutPrintStream.close();
                System.setErr(catalinaOutPrintStream.getOriginalSystemErr());
            }
        }

        @Override
        public void stopScanners() {
            for (Scanner scanner : scanners) {
                scanner.stop();
            }
        }
    }

}
