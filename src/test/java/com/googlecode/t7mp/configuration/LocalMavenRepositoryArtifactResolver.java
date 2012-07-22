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
package com.googlecode.t7mp.configuration;

import java.io.File;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

import com.googlecode.t7mp.AbstractArtifact;

/**
 * 
 * @author Joerg Bellmann
 *
 */
public class LocalMavenRepositoryArtifactResolver implements PluginArtifactResolver {

    private final File localMavenRepository;

    public LocalMavenRepositoryArtifactResolver() {
        localMavenRepository = createLocalMavenRepository();
    }

    public LocalMavenRepositoryArtifactResolver(String localMavenRepositoryPath) {
        Validate.notEmpty(localMavenRepositoryPath);
        localMavenRepository = createLocalMavenRepository(new File(localMavenRepositoryPath));
    }

    private File createLocalMavenRepository() {
        File userHomeDirectory = new File(System.getProperty("user.home"));
        File repositoryDirectory = new File(userHomeDirectory, "/.m2/repository");
        return createLocalMavenRepository(repositoryDirectory);
    }

    private File createLocalMavenRepository(File file) {
        Validate.isTrue(file.isDirectory(), "The directory " + file.getAbsolutePath() + " does not exist.");
        return file;
    }

    @Override
    public File resolveArtifact(String coordinates) throws ResolutionException {
        Validate.notEmpty(coordinates);
        AbstractArtifact artifact;
        try {
            artifact = Artifacts.fromCoordinates(coordinates);
        } catch (InvalidCoordinatesException e) {
            throw new ResolutionException("Invalid MavenCoordinates " + coordinates);
        }
        String relativePath;
        relativePath = createRelativeArtifactPath(artifact);
        File result = new File(this.localMavenRepository, relativePath);
        if (!result.isFile()) {
            throw new ResolutionException("Resolved file is a directory.");
        } else if (!result.exists()) {
            throw new ResolutionException("File " + result.getAbsolutePath() + " does not exist.");
        }
        return result;
    }

    protected String createRelativeArtifactPath(AbstractArtifact artifact) {
        StringBuilder sb = new StringBuilder();
        sb.append(artifact.getGroupId().replace('.', '/')).append("/");
        sb.append(artifact.getArtifactId()).append("/");
        sb.append(artifact.getVersion()).append("/");
        sb.append(artifact.getArtifactId()).append("-").append(artifact.getVersion());
        if (!StringUtils.isBlank(artifact.getClassifier())) {
            sb.append("-").append(artifact.getClassifier());
        }
        sb.append(".").append(artifact.getType());
        return sb.toString();
    }

}
