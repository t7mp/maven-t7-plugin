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
package com.googlecode.t7mp.newTests;

import junit.framework.Assert;

import org.junit.Test;

import com.googlecode.t7mp.TomcatArtifact;

/**
 * 
 * @author Joerg Bellmann
 *
 */
public class TomcatArtifactTest {

    @Test
    public void testTomcatArtifact() {
        TomcatArtifact tomcat = new TomcatArtifact();
        Assert.assertEquals(TomcatArtifact.TOMCAT_GROUPID, tomcat.getGroupId());
        Assert.assertEquals(TomcatArtifact.TOMCAT_ARTIFACTID, tomcat.getArtifactId());
        Assert.assertEquals(TomcatArtifact.DEFAULT_TOMCAT_VERSION, tomcat.getVersion());
        Assert.assertEquals("zip", tomcat.getType());
        Assert.assertNull(tomcat.getClassifier());
    }

    @Test
    public void testSpecialVersion() {
        String version = "7.0.30";
        TomcatArtifact tomcat = new TomcatArtifact(version);
        Assert.assertEquals(version, tomcat.getVersion());
    }

    @Test
    public void testCoordinates() {
        TomcatArtifact tomcat = new TomcatArtifact();
        Assert.assertEquals("com.googlecode.t7mp:tomcat:zip:7.0.27", tomcat.getArtifactCoordinates());
    }

    @Test
    public void testCoordinatesWithSystemPath() {
        TomcatArtifact tomcat = new TomcatArtifact();
        tomcat.setSystemPath("/home/jbellmann/test/tomcat.zip");
        Assert.assertEquals("/home/jbellmann/test/tomcat.zip", tomcat.getArtifactCoordinates());
    }

}
