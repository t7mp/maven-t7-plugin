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

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.google.common.io.Files;
import com.googlecode.t7mp.BaseConfiguration;
import com.googlecode.t7mp.DefaultPluginLog;
import com.googlecode.t7mp.PluginLog;
import com.googlecode.t7mp.steps.Context;
import com.googlecode.t7mp.steps.CreateTomcatDirectoryStep;
import com.googlecode.t7mp.steps.Step;

public class CreateTomcatDirectoryStepTest {

    private File catalinaBaseDir;
    private final Context context = Mockito.mock(Context.class);
    private final BaseConfiguration configuration = Mockito.mock(BaseConfiguration.class);
    private final PluginLog log = new DefaultPluginLog();

    @Before
    public void setUp() {
        catalinaBaseDir = Files.createTempDir();
        Mockito.when(context.getConfiguration()).thenReturn(configuration);
        Mockito.when(context.getLog()).thenReturn(log);
        Mockito.when(configuration.getCatalinaBase()).thenReturn(catalinaBaseDir);
    }

    @After
    public void tearDown() throws IOException {
        FileUtils.deleteDirectory(catalinaBaseDir);
    }

    @Test
    public void testCreateDirectoryStep() {
        testDirectory("conf");
        testDirectory("lib");
        testDirectory("webapps");
        testDirectory("work");
        testDirectory("temp");
        testDirectory("logs");
    }

    private void testDirectory(String directory) {
        Step step = new CreateTomcatDirectoryStep(directory);
        step.execute(context);
        Assert.assertTrue(new File(catalinaBaseDir, directory).exists());
        Assert.assertTrue(new File(catalinaBaseDir, directory).isDirectory());
    }

}
