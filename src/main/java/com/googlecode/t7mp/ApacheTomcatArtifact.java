package com.googlecode.t7mp;

/**
 * 
 * TODO Comment
 *
 * @author Joerg Bellmann
 *
 */
public class ApacheTomcatArtifact extends TomcatArtifact {

    public static final String DEFAULT_TOMCAT_VERSION = "7.0.42";
    public static final String A_TOMCAT_GROUPID = "org.apache.tomcat";
    public static final String A_TOMCAT_ARTIFACTID = "tomcat";
    public static final String A_TOMCAT_TYPE = "zip";

    public ApacheTomcatArtifact() {
        this(DEFAULT_TOMCAT_VERSION);
    }

    public ApacheTomcatArtifact(String version) {
        super(A_TOMCAT_GROUPID, A_TOMCAT_ARTIFACTID, version, A_TOMCAT_TYPE);
    }

    @Override
    public String getType() {
        return A_TOMCAT_TYPE;
    }

    @Override
    public String toString() {
        return "ApacheTomcatArtifact[" + super.getArtifactCoordinates() + "]";
    }

}
