package com.googlecode.t7mp.maven;

import com.googlecode.t7mp.steps.DefaultStepSequence;
import com.googlecode.t7mp.steps.deployment.TomcatSetupSequence;

/**
 * TomcatSetupSequence in a Maven-Environment.
 * 
 * @author Joerg Bellmann
 *
 */
public class MavenTomcatSetupSequence extends DefaultStepSequence {

    public MavenTomcatSetupSequence() {
        this.add(new CheckT7ArtifactsStep());
        // add the default
        this.add(new TomcatSetupSequence());
    }

}
