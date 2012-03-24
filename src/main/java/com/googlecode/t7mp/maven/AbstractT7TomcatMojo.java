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
