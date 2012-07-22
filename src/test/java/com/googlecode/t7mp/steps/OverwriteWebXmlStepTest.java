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

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.googlecode.t7mp.BaseConfiguration;
import com.googlecode.t7mp.configuration.ChainedArtifactResolver;
import com.googlecode.t7mp.steps.Context;
import com.googlecode.t7mp.steps.DefaultContext;
import com.googlecode.t7mp.steps.OverwriteWebXmlStep;
import com.googlecode.t7mp.steps.Step;

public class OverwriteWebXmlStepTest {

    private File catalinaBaseDir;
    private final BaseConfiguration configuration = Mockito.mock(BaseConfiguration.class);

    //    private final PluginLog log = new DefaultPluginLog();

    @Before
    public void setUp() {
        Mockito.when(configuration.getCatalinaBase()).thenReturn(catalinaBaseDir);
        //        Mockito.when(mojo.getLog()).thenReturn(log);
    }

    @Test
    public void testOverwriteWebXmlIsNull() {
        Mockito.when(configuration.getOverwriteWebXML()).thenReturn(null);
        Context context = new DefaultContext(new ChainedArtifactResolver(), configuration);
        Step step = new OverwriteWebXmlStep();
        step.execute(context);
    }

    @Test
    public void testOverwriteWebXmlDoesNotExist() {
        File notExistentFile = new File("/klaus/peter");
        Mockito.when(configuration.getOverwriteWebXML()).thenReturn(notExistentFile);
        Context context = new DefaultContext(new ChainedArtifactResolver(), configuration);
        Step step = new OverwriteWebXmlStep();
        step.execute(context);
    }

}
