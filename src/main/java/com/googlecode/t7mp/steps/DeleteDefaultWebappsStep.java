/*
 * Copyright 2012 Florian Chiș.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.googlecode.t7mp.steps;

import com.googlecode.t7mp.PluginLog;
import com.googlecode.t7mp.T7Configuration;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

/**
 * Delete default tomcat apps from webapps directory
 *
 * @author Florian Chiș
 */
public class DeleteDefaultWebappsStep implements Step {

    protected PluginLog logger;
    protected T7Configuration configuration;

    @Override
    public void execute(Context context) {
        this.logger = context.getLog();
        this.configuration = context.getConfiguration();
        if (configuration.isDownloadDefaultTomcatWebapps()) {
            logger.info("Skip deletion of default tomcat webapps.");
            return;
        }
        try {
            FileUtils.cleanDirectory(new File(configuration.getCatalinaBase(), "/webapps"));
        } catch (IOException e) {
            logger.error("Could not clean the 'webapps' directory", e);
        }
    }
}
