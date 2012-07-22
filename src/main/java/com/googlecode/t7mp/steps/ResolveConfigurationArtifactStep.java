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
package com.googlecode.t7mp.steps;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.io.FileUtils;

import com.googlecode.t7mp.T7Configuration;
import com.googlecode.t7mp.PluginLog;
import com.googlecode.t7mp.TomcatSetupException;
import com.googlecode.t7mp.configuration.PluginArtifactResolver;
import com.googlecode.t7mp.configuration.ResolutionException;
import com.googlecode.t7mp.util.FileFilters;
import com.googlecode.t7mp.util.FileUtil;
import com.googlecode.t7mp.util.ZipUtil;

/**
 * Resolve the configurationArtifact and copy Files to conf-directory.
 * 
 * @author Joerg Bellmann
 *
 */
public class ResolveConfigurationArtifactStep implements Step {

    protected PluginLog logger;

    protected T7Configuration baseConfiguration;
    protected PluginArtifactResolver artifactResolver;

    @Override
    public void execute(Context context) {
        this.artifactResolver = context.getArtifactResolver();
        this.baseConfiguration = context.getConfiguration();
        this.logger = context.getLog();
        // skip this step if no configuationArtifact is found
        if (baseConfiguration.getConfigArtifact() == null) {
            this.logger.info("No configurationArtifact found, skip this step.");
            return;
        }
        // lets do the hard work
        this.logger.debug("resolve configurationArtifact ...");
        File unpackDirectory = null;
        try {
            File resolvedArtifact = artifactResolver.resolveArtifact(this.baseConfiguration.getConfigArtifact()
                    .getArtifactCoordinates());
            unpackDirectory = getUnpackDirectory();
            ZipUtil.unzip(resolvedArtifact, unpackDirectory);
            this.logger.debug("unzipped to " + unpackDirectory.getAbsolutePath());
            copyToTomcatConfDirectory(unpackDirectory);
        } catch (ResolutionException e) {
            this.logger.error(e.getMessage(), e);
            throw new TomcatSetupException(e.getMessage(), e);
        } catch (IOException e) {
            this.logger.error(e.getMessage(), e);
            throw new TomcatSetupException(e.getMessage(), e);
        } finally {
            if (unpackDirectory != null) {
                try {
                    FileUtils.deleteDirectory(unpackDirectory);
                } catch (IOException e) {
                    this.logger.error("Could not delete temp directory " + unpackDirectory.getAbsolutePath(), e);
                }
            }
        }
    }

    private void copyToTomcatConfDirectory(File unpackDirectory) throws IOException {
        this.logger.debug("copy conf-files ...");
        File confDirectory = new File(this.baseConfiguration.getCatalinaBase(), "conf");
        this.logger.debug("targetConfDirectory is " + confDirectory.getAbsolutePath());
        Set<File> files = FileUtil.getAllFiles(unpackDirectory, FileFilters.forAll(), false);
        for (File file : files) {
            this.logger.debug("copy file " + file.getName() + ", path " + file.getAbsolutePath()
                    + " to targetDirectory");
            FileUtils.copyFileToDirectory(file, confDirectory);
        }
    }

    protected File getUnpackDirectory() {
        File tempDir = new File(System.getProperty("java.io.tmpdir"));
        File upackDirectory = new File(tempDir, UUID.randomUUID().toString());
        upackDirectory.mkdirs();
        return upackDirectory;
    }
}
