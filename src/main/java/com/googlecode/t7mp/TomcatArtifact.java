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

/**
 * Use this artifact to resolve the tomcat distribution from Maven-Central.
 *
 * @author  Joerg Bellmann
 */
public class TomcatArtifact extends AbstractArtifact {

    public static final String DEFAULT_TOMCAT_VERSION = "7.0.52";
    public static final String TOMCAT_GROUPID = "com.googlecode.t7mp";
    public static final String TOMCAT_ARTIFACTID = "tomcat";
    public static final String TOMCAT_TYPE = "zip";

    public TomcatArtifact() {
        this(DEFAULT_TOMCAT_VERSION);
    }

    public TomcatArtifact(final String version) {
        this(TOMCAT_GROUPID, TOMCAT_ARTIFACTID, version, TOMCAT_TYPE);
    }

    public TomcatArtifact(final String groupId, final String artifactId, final String version, final String type) {
        super(groupId, artifactId, version, null, type);
    }

    @Override
    public String getType() {
        return "zip";
    }

    @Override
    public String toString() {
        return "TomcatArtifact[" + super.getArtifactCoordinates() + "]";
    }

}
