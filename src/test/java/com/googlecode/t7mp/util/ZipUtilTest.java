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
package com.googlecode.t7mp.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Joerg Bellmann
 *
 */
public class ZipUtilTest {

    private File unpackDirectory = null;

    @Before
    public void setUp() {
        unpackDirectory = getUnpackDirectory();
    }

    @After
    public void tearDown() {
        try {
            FileUtils.deleteDirectory(unpackDirectory);
        } catch (IOException e) {
            e.printStackTrace();
            Assert.fail("Could not delete unpackDirectory " + unpackDirectory.getAbsolutePath());
        }
    }

    @Test
    public void testUnzipJar() {
        InputStream jarInputStream = getClass().getResourceAsStream("tomcatconfig-0.0.1-SNAPSHOT.jar");
        ZipUtil.unzip(jarInputStream, unpackDirectory);
        Set<File> fileSet = FileUtil.getAllFiles(unpackDirectory, FileFilters.forAll(), false);
        Assert.assertEquals("Sollte eine Datei drin sein.", 1, fileSet.size());
    }

    protected File getUnpackDirectory() {
        File tempDir = new File(System.getProperty("java.io.tmpdir"));
        File upackDirectory = new File(tempDir, UUID.randomUUID().toString());
        upackDirectory.mkdirs();
        return upackDirectory;
    }
}
