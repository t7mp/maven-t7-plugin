package com.googlecode.t7mp.steps;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.googlecode.t7mp.PluginLog;
import com.googlecode.t7mp.T7Configuration;

/**
 * This Step deletes the 'docs' and 'examples' directories of the
 * Tomcat distribution.
 * 
 * Make sure you only use it after the extraction-step.
 * 
 * @author Joerg Bellmann
 *
 */
public class DeleteDocsAndExamplesStep implements Step {

    protected PluginLog logger;
    protected T7Configuration configuration;

    @Override
    public void execute(Context context) {
        this.logger = context.getLog();
        this.configuration = context.getConfiguration();
        if (configuration.isDownloadTomcatExamples()) {
            // now we do not support a repackaged version anymore
            // so we only have complete tomcat-zips in maven-central
            logger.info("Skip deletion of 'docs' and 'examples' directories.");
            return;
        }
        // the user do not want docs and examples
        try {
            FileUtils.deleteDirectory(new File(configuration.getCatalinaBase(), "/docs"));
        } catch (IOException e) {
            logger.error("Could not delete the 'docs' example.", e);
        }
        try {
            FileUtils.deleteDirectory(new File(configuration.getCatalinaBase(), "/examples"));
        } catch (IOException e) {
            logger.error("Could not delete the 'examples' directory.", e);
        }
    }

}
