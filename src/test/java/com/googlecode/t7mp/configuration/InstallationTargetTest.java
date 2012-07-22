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
package com.googlecode.t7mp.configuration;

import java.io.File;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Joerg Bellmann
 *
 */
public class InstallationTargetTest {

    private static Logger LOG = LoggerFactory.getLogger(InstallationTargetTest.class);

    @Rule
    public static TemporaryFolder temporyFolder = new TemporaryFolder();

    @Test
    public void testDefaultInstallationTarget() {
        TomcatDirectoryLayout target = new DefaultInstallationTarget(temporyFolder.newFolder("tomcat"));
        assertDirectory(target.getBinDirectory());
        assertDirectory(target.getConfDirectory());
        assertDirectory(target.getLibDirectory());
        assertDirectory(target.getLogsDirectory());
        assertDirectory(target.getTempDirectory());
        assertDirectory(target.getWebappsDirectory());
        assertDirectory(target.getWorkDirectory());
    }

    private void assertDirectory(File directory) {
        Assert.assertNotNull(directory);
        Assert.assertTrue(directory.exists());
        Assert.assertTrue(directory.isDirectory());
        LOG.info("Path : " + directory.getAbsolutePath());
    }

    @Test(expected = RuntimeException.class)
    public void testNullArgument() {
        new DefaultInstallationTarget(null);
    }

    @Test
    public void testRootNotExistent() {
        File existentTempDirectory = temporyFolder.newFolder("exist");
        File notExistentDirectory = new File(existentTempDirectory, "notExistentDirectory");
        new DefaultInstallationTarget(notExistentDirectory);
    }

    @Test(expected = RuntimeException.class)
    public void testRootNotExistentAndNotCreateable() {
        File existentTempDirectory = new File("/");
        File notExistentDirectory = new File(existentTempDirectory, "notExistentDirectory/morePath/segments");
        new DefaultInstallationTarget(notExistentDirectory);
    }

    @Test(expected = RuntimeException.class)
    public void testDefaultInstallationTargetWhenMkDirsReturnFalse() {
        DefaultInstallationTarget target = new DefaultInstallationTarget(temporyFolder.newFolder("tomcat"));
        target.createFile("klaus/ralf/egon");
    }

}
