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

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import com.googlecode.t7mp.JarArtifact;

public class JarArtifactTest {

    //    private static final Logger LOG = LoggerFactory.getLogger(JarArtifact.class);

    @Test
    public void testJarArtifact() {
        JarArtifact artifact = new JarArtifact();
        artifact.setArtifactId(ArtifactConstants.ARTIFACTID);
        artifact.setGroupId(ArtifactConstants.GROUPID);
        artifact.setVersion(ArtifactConstants.VERSION);
        artifact.setType(ArtifactConstants.JAR_TYPE);
        artifact.setClassifier(ArtifactConstants.CLASSIFIER);
        Assert.assertEquals(ArtifactConstants.ARTIFACTID, artifact.getArtifactId());
        Assert.assertEquals(ArtifactConstants.GROUPID, artifact.getGroupId());
        Assert.assertEquals(ArtifactConstants.JAR_TYPE, artifact.getType());
        Assert.assertEquals(ArtifactConstants.VERSION, artifact.getVersion());
        Assert.assertEquals(ArtifactConstants.CLASSIFIER, artifact.getClassifier());
        // JarArtifact should return jar as Type, set this property should have no effect
        artifact.setType(ArtifactConstants.WAR_TYPE);
        Assert.assertEquals(ArtifactConstants.JAR_TYPE, artifact.getType());
        System.out.println(artifact.toString());
    }

    @Test
    public void testArtifactConstructor() {
        JarArtifact jarArtifact = new JarArtifact(ArtifactConstants.GROUPID, ArtifactConstants.ARTIFACTID,
                ArtifactConstants.VERSION, ArtifactConstants.CLASSIFIER, ArtifactConstants.JAR_TYPE);
        jarArtifact.setFile(Mockito.mock(File.class));

        Assert.assertEquals(ArtifactConstants.GROUPID, jarArtifact.getGroupId());
        Assert.assertEquals(ArtifactConstants.ARTIFACTID, jarArtifact.getArtifactId());
        Assert.assertEquals(ArtifactConstants.JAR_TYPE, jarArtifact.getType());
        Assert.assertEquals(ArtifactConstants.CLASSIFIER, jarArtifact.getClassifier());
        Assert.assertNotNull(jarArtifact.getFile());
    }

}