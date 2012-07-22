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
import java.io.IOException;

import junit.framework.Assert;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.google.common.io.Files;
import com.googlecode.t7mp.BaseConfiguration;
import com.googlecode.t7mp.configuration.ChainedArtifactResolver;
import com.googlecode.t7mp.steps.Context;
import com.googlecode.t7mp.steps.CopySetenvScriptStep;
import com.googlecode.t7mp.steps.DefaultContext;
import com.googlecode.t7mp.steps.Step;

public class CopySetenvScripStepTest {

    private File catalinaBaseDir;
    private final BaseConfiguration configuration = Mockito.mock(BaseConfiguration.class);

    //    private final PluginLog log = new DefaultPluginLog();

    @Before
    public void setUp() {
        catalinaBaseDir = Files.createTempDir();
        Assert.assertTrue(catalinaBaseDir.exists());
        Assert.assertNotNull(catalinaBaseDir);
        Assert.assertTrue(catalinaBaseDir.exists());

        Mockito.when(configuration.getCatalinaBase()).thenReturn(catalinaBaseDir);
        //        Mockito.when(mojo.getLog()).thenReturn(log);
    }

    @After
    public void tearDown() throws IOException {
        FileUtils.deleteDirectory(catalinaBaseDir);
    }

    @Test
    public void testCopySetenvScriptStepNotExist() {
        Context context = new DefaultContext(new ChainedArtifactResolver(), configuration);
        Step step = new CopySetenvScriptStep();
        step.execute(context);
    }

    @Test
    public void testCopySetenvScriptStep() {
        String setenvScriptPath = getClass().getResource("/com/googlecode/t7mp/bin/setenv.sh").getPath();
        File t7mpDirectory = new File(setenvScriptPath).getParentFile().getParentFile();
        File confDirectory = new File(t7mpDirectory, "/conf/");
        Assert.assertNotNull(confDirectory);
        Assert.assertTrue(confDirectory.exists());
        Mockito.when(configuration.getTomcatConfigDirectory()).thenReturn(new File(t7mpDirectory, "/conf/"));
        Context context = new DefaultContext(new ChainedArtifactResolver(), configuration);
        Step step = new CopySetenvScriptStep();
        step.execute(context);
        File binDirectory = new File(catalinaBaseDir, "/bin/");
        Assert.assertNotNull(binDirectory);
        Assert.assertTrue(binDirectory.exists());
        File copyiedSetenvScript = new File(binDirectory, "setenv.sh");

        Assert.assertNotNull(copyiedSetenvScript);
        Assert.assertTrue(copyiedSetenvScript.exists());
    }

}
