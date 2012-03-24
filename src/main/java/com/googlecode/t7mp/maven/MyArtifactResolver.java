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
package com.googlecode.t7mp.maven;

import java.io.File;
import java.util.List;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.factory.ArtifactFactory;
import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.artifact.resolver.ArtifactNotFoundException;
import org.apache.maven.artifact.resolver.ArtifactResolutionException;
import org.apache.maven.artifact.resolver.ArtifactResolver;
import org.apache.maven.artifact.versioning.VersionRange;
import org.apache.maven.plugin.MojoExecutionException;

import com.googlecode.t7mp.AbstractArtifact;
import com.googlecode.t7mp.configuration.Artifacts;
import com.googlecode.t7mp.configuration.PluginArtifactResolver;
import com.googlecode.t7mp.configuration.ResolutionException;

/**
 * Uses Maven-API to resolve the Artifacts.
 * 
 *
 */
public class MyArtifactResolver implements PluginArtifactResolver {

    private final ArtifactResolver resolver;
    private final ArtifactFactory factory;
    private final ArtifactRepository local;
    private final List<ArtifactRepository> remoteRepositories;

    public MyArtifactResolver(AbstractT7BaseMojo t7BaseMojo) {
        this.remoteRepositories = t7BaseMojo.getRemoteRepos();
        this.local = t7BaseMojo.getLocal();
        this.resolver = t7BaseMojo.getResolver();
        this.factory = t7BaseMojo.getFactory();
    }

    /**
     * resolves an Artifact from the repositorys.
     * 
     * @param groupId - Artifact GroupId
     * @param artifactId - Artifact Id
     * @param version - Artifact Version
     * @param classifier - Artifact Version
     * @param type - Artifact Type
     * @param scope - Artifact Scope
     * @return
     * @throws MojoExecutionException
     */
    public Artifact resolve(String groupId, String artifactId, String version, String classifier, String type, String scope) throws MojoExecutionException {
        Artifact artifact = factory.createDependencyArtifact(groupId, artifactId, VersionRange.createFromVersion(version), type, classifier,
                Artifact.SCOPE_COMPILE);
        try {
            resolver.resolve(artifact, remoteRepositories, local);
        } catch (ArtifactResolutionException e) {
            throw new MojoExecutionException(e.getMessage(), e);
        } catch (ArtifactNotFoundException e) {
            throw new MojoExecutionException(e.getMessage(), e);
        }
        return artifact;
    }

    public Artifact resolveJar(String groupId, String artifactId, String version, String classifier) throws MojoExecutionException {
        return resolve(groupId, artifactId, version, classifier, "jar", Artifact.SCOPE_COMPILE);
    }

    public Artifact resolveWar(String groupId, String artifactId, String version, String classifier) throws MojoExecutionException {
        return resolve(groupId, artifactId, version, classifier, "war", Artifact.SCOPE_COMPILE);
    }

    @Override
    public File resolveArtifact(String coordinates) throws ResolutionException {
        AbstractArtifact artifact = Artifacts.fromCoordinates(coordinates);
        Artifact resolvedArtifact = null;
        try {
            resolvedArtifact = resolve(artifact.getGroupId(), artifact.getArtifactId(), artifact.getVersion(), artifact.getClassifier(), artifact.getType(),
                    null);
        } catch (MojoExecutionException e) {
            throw new ResolutionException(e.getMessage(), e);
        }

        return resolvedArtifact.getFile();
    }
}
