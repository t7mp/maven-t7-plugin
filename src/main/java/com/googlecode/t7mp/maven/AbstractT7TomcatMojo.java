package com.googlecode.t7mp.maven;

import com.googlecode.t7mp.T7Configuration;
import com.googlecode.t7mp.TomcatArtifact;
import com.googlecode.t7mp.configuration.ChainedArtifactResolver;
import com.googlecode.t7mp.steps.Context;
import com.googlecode.t7mp.steps.DefaultContext;

/**
 * 
 * Takes configurations for t7 Tomcat only (no TomEE).
 *
 * @author Joerg Bellmann
 *
 */
public abstract class AbstractT7TomcatMojo extends AbstractT7BaseMojo implements T7Configuration {

    /**
     * 
     * @parameter default-value="false"
     */
    protected boolean downloadTomcatExamples = false;

    @Override
    public boolean isDownloadTomcatExamples() {
        return downloadTomcatExamples;
    }

    public void setDownloadTomcatExamples(boolean downloadTomcatExamples) {
        this.downloadTomcatExamples = downloadTomcatExamples;
    }

    @Override
    public TomcatArtifact getTomcatArtifact() {
        return new TomcatArtifact();
    }

    protected Context buildParentContext() {
        ChainedArtifactResolver artifactResolver = new ChainedArtifactResolver();
        artifactResolver.addPluginArtifactResolver(new MyArtifactResolver(this));
        DefaultContext defaultContext = new DefaultContext(artifactResolver, this);
        return defaultContext;
    }
}
