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
package com.googlecode.t7mp.steps;

import java.io.File;

import org.junit.Test;

import com.googlecode.t7mp.AbstractBaseTest;
import com.googlecode.t7mp.ConfigurationArtifact;
import com.googlecode.t7mp.TomcatSetupException;
import com.googlecode.t7mp.steps.DefaultContext;
import com.googlecode.t7mp.steps.ResolveConfigurationArtifactStep;

/**
 * 
 * @author Joerg Bellmann
 *
 */
public class ResolveConfigurationArtifactStepTest extends AbstractBaseTest {

    @Test
    public void testResolveConfigurationArtifactStep() {
        ConfigurationArtifact artifact = new ConfigurationArtifact();
        artifact.setSystemPath(getPath());
        configuration.setConfigArtifact(artifact);
        DefaultContext context = new DefaultContext(chainedArtifactResolver, configuration);
        ResolveConfigurationArtifactStep step = new ResolveConfigurationArtifactStep();
        step.execute(context);
    }

    @Test(expected = TomcatSetupException.class)
    public void testResolveConfigurationArtifactStepWithResolutionException() {
        ConfigurationArtifact artifact = new ConfigurationArtifact();
        artifact.setSystemPath(getPath() + "xxx");
        configuration.setConfigArtifact(artifact);
        DefaultContext context = new DefaultContext(chainedArtifactResolver, configuration);
        ResolveConfigurationArtifactStep step = new ResolveConfigurationArtifactStep();
        step.execute(context);
    }

    private String getPath() {
        File userDir = new File(System.getProperty("user.dir"));
        File resource = new File(userDir, "/src/test/resources/com/googlecode/t7mp/util/tomcatconfig-0.0.1-SNAPSHOT.jar");
        return resource.getAbsolutePath();
    }

}

