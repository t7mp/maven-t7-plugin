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

import com.googlecode.t7mp.TomcatSetupException;
import com.googlecode.t7mp.util.TomcatUtil;

/**
 * 
 * @author Joerg Bellmann
 *
 */
public class CopyJuliJarStep implements Step {

    private static final String JAR_NAME = "tomcat-juli.jar";

    @Override
    public void execute(Context context) {
        try {
            File juliJarFileSource = new File(TomcatUtil.getBinDirectory(context.getConfiguration().getCatalinaBase()), JAR_NAME);
            File juliJarFileDestination = new File(TomcatUtil.getLibDirectory(context.getConfiguration().getCatalinaBase()), JAR_NAME);
            FileUtils.copyFile(juliJarFileSource, juliJarFileDestination);
        } catch (IOException e) {
            throw new TomcatSetupException(e.getMessage(), e);
        }
    }

}
