package com.googlecode.t7mp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.startup.Bootstrap;

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
            ScannerSetup.configureScanners(shutdownHook, configuration, log);
                bootstrap.start();
                Runtime.getRuntime().addShutdownHook(shutdownHook);
                log.info("Tomcat started");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
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
