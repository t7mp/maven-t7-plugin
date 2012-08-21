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
import java.io.FileFilter;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import com.googlecode.t7mp.T7Configuration;
import com.googlecode.t7mp.PluginLog;
import com.googlecode.t7mp.TomcatArtifact;
import com.googlecode.t7mp.TomcatSetupException;
import com.googlecode.t7mp.configuration.PluginArtifactResolver;
import com.googlecode.t7mp.configuration.ResolutionException;
import com.googlecode.t7mp.util.ZipUtil;

/**
 * TODO Comment.
 * 
 * @author Joerg Bellmann
 *
 */
public class ResolveTomcatStep implements Step {

    protected PluginLog logger;
    protected T7Configuration configuration;
    protected PluginArtifactResolver artifactResolver;

    @Override
    public void execute(Context context) {
        this.configuration = context.getConfiguration();
        this.artifactResolver = context.getArtifactResolver();
        this.logger = context.getLog();
        if (StringUtils.isEmpty(configuration.getTomcatVersion())) {
            throw new TomcatSetupException("Version should not be null or empty.");
        }

        File unpackDirectory = null;
        try {

            TomcatArtifact tomcatArtifact = configuration.getTomcatArtifact();
            tomcatArtifact.setVersion(configuration.getTomcatVersion());
            File resolvedArtifact = artifactResolver.resolveArtifact(tomcatArtifact.getArtifactCoordinates());
            unpackDirectory = getUnpackDirectory();
            ZipUtil.unzip(resolvedArtifact, unpackDirectory);
            copyToTomcatDirectory(unpackDirectory);
        } catch (ResolutionException e) {
            logger.error(e.getMessage(), e);
            throw new TomcatSetupException(e.getMessage(), e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new TomcatSetupException(e.getMessage(), e);
        } finally {
            if (unpackDirectory != null) {
                try {
                    FileUtils.deleteDirectory(unpackDirectory);
                } catch (IOException e) {
                    logger.error("Could not delete tomcat upack directory : " + unpackDirectory.getAbsolutePath());
                    logger.error(e.getMessage(), e);
                }
            }
        }
    }

    private void copyToTomcatDirectory(File unpackDirectory) throws IOException {
        File[] files = unpackDirectory.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isDirectory();
            }
        });
        // should only be one
        FileUtils.copyDirectory(files[0], this.configuration.getCatalinaBase());
    }

    protected File getUnpackDirectory() {
        File tempDir = new File(System.getProperty("java.io.tmpdir"));
        File upackDirectory = new File(tempDir, UUID.randomUUID().toString());
        upackDirectory.mkdirs();
        return upackDirectory;
    }

}
