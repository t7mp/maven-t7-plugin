package com.googlecode.t7mp;

import com.google.common.base.Objects;

/**
 * Holds configuration for a single instance.
 * 
 * 
 * @author Joerg Bellmann
 *
 */
class InstanceConfiguration {

    private int id;
    private int httpPort;
    private int shutdownPort;
    private String catalinaBasePath;

    public InstanceConfiguration(int id, int httpPort, int shutdownPort, String catalinaBasePath) {
        this.id = id;
        this.httpPort = httpPort;
        this.shutdownPort = shutdownPort;
        this.catalinaBasePath = catalinaBasePath;
    }

    public int getHttpPort() {
        return httpPort;
    }

    public int getShutdownPort() {
        return shutdownPort;
    }

    public String getCatalinaBasePath() {
        return catalinaBasePath;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return Objects.toStringHelper(this).add("id", getId()).add("httpPort", getHttpPort()).add("shutdownPort", getShutdownPort())
                .add("catalinaBasePath", getCatalinaBasePath()).toString();
    }

}
