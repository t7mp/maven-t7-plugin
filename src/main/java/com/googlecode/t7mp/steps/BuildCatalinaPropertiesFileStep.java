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
import java.io.FileWriter;
import java.io.Writer;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.runtime.log.LogChute;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import com.googlecode.t7mp.T7Configuration;
import com.googlecode.t7mp.TomcatSetupException;

public class BuildCatalinaPropertiesFileStep implements Step {

    @Override
    public void execute(Context context) {
        final T7Configuration configuration = context.getConfiguration();
        try {
            Velocity.setProperty(Velocity.RUNTIME_LOG_LOGSYSTEM, new LogNothingLogChute());
            Velocity.setProperty(Velocity.RESOURCE_LOADER, "class");
            Velocity.setProperty("class.resource.loader.description", "Velocity Classpath Resource Loader");
            Velocity.setProperty("class.resource.loader.class", ClasspathResourceLoader.class.getName());
            Velocity.init();
            Template template = Velocity.getTemplate("com/googlecode/t7mp/conf/catalina.properties");
            VelocityContext velocityContext = new VelocityContext();
            velocityContext.put("tomcatHttpPort", configuration.getTomcatHttpPort() + "");
            velocityContext.put("tomcatShutdownPort", configuration.getTomcatShutdownPort() + "");
            velocityContext.put("tomcatShutdownCommand", configuration.getTomcatShutdownCommand());
            velocityContext.put("tomcatHostName", configuration.getTomcatHostName());
            Writer writer = new FileWriter(new File(configuration.getCatalinaBase(), "/conf/catalina.properties"));
            template.merge(velocityContext, writer);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            throw new TomcatSetupException(e.getMessage(), e);
        }
    }
    
    /**
     * 
     * @author Joerg Bellmann
     *
     */
    static class LogNothingLogChute implements LogChute {

        @Override
        public void init(RuntimeServices rs) throws Exception {

        }

        @Override
        public void log(int level, String message) {

        }

        @Override
        public void log(int level, String message, Throwable t) {

        }

        @Override
        public boolean isLevelEnabled(int level) {
            return false;
        }

    }

}
