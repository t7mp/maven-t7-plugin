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

import com.googlecode.t7mp.PluginLog;
import com.googlecode.t7mp.T7Configuration;

/**
 * Delete default tomcat apps from webapps directory
 *
 * @author Florian Chiș
 */
public class DeleteDefaultWebappsSequence extends DefaultStepSequence {

    protected PluginLog logger;
    protected T7Configuration configuration;
    
    public DeleteDefaultWebappsSequence(){
        add(new DeleteRootWebapp());
        add(new DeleteManagerWebapp());
        add(new DeleteHostManagerWebapp());
        add(new DeleteExamplesWebapp());
        add(new DeleteDocsWebapp());
    }

    @Override
    public void execute(Context context) {
        this.logger = context.getLog();
        this.configuration = context.getConfiguration();
        if(configuration.isDeleteDefaultTomcatWebapps()){
            logger.info("Delete all default tomcat webapps");
            try {
                FileUtils.cleanDirectory(new File(configuration.getCatalinaBase(), "/webapps"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            for(Step step : this.sequence){
                step.execute(context);
            }
        }
    }
    
    /**
     * 
     * TODO Comment
     *
     * @author Joerg Bellmann
     *
     */
    static class DeleteRootWebapp implements Step {

        protected PluginLog logger;
        protected T7Configuration configuration;

        @Override
        public void execute(Context context) {
            configuration = context.getConfiguration();
            if(configuration.isDeleteTomcatDefaultRootWebapp()){
                File webappsDirectory = new File(configuration.getCatalinaBase(), "/webapps");
                try {
                    FileUtils.deleteDirectory(new File(webappsDirectory, "/ROOT"));
                } catch (IOException e) {
                    throw new RuntimeException("Could not delete 'ROOT' directory", e);
                }
            }
        }
    }
    
    /**
     * 
     * TODO Comment
     *
     * @author Joerg Bellmann
     *
     */
    static class DeleteManagerWebapp implements Step {

        protected PluginLog logger;
        protected T7Configuration configuration;

        @Override
        public void execute(Context context) {
            configuration = context.getConfiguration();
            if(configuration.isDeleteTomcatDefaultManagerWebapp()){
                File webappsDirectory = new File(configuration.getCatalinaBase(), "/webapps");
                try {
                    FileUtils.deleteDirectory(new File(webappsDirectory, "/manager"));
                } catch (IOException e) {
                    throw new RuntimeException("Could not delete 'manager' directory", e);
                }
            }
        }
    }
    
    /**
     * 
     * TODO Comment
     *
     * @author Joerg Bellmann
     *
     */
    static class DeleteHostManagerWebapp implements Step {

        protected PluginLog logger;
        protected T7Configuration configuration;

        @Override
        public void execute(Context context) {
            configuration = context.getConfiguration();
            if(configuration.isDeleteTomcatDefaultHostManagerWebapp()){
                File webappsDirectory = new File(configuration.getCatalinaBase(), "/webapps");
                try {
                    FileUtils.deleteDirectory(new File(webappsDirectory, "/host-manager"));
                } catch (IOException e) {
                    throw new RuntimeException("Could not delete 'host-manager' directory",e);
                }
            }
        }
    }
    
    /**
     * 
     * TODO Comment
     *
     * @author Joerg Bellmann
     *
     */
    static class DeleteExamplesWebapp implements Step {

        protected PluginLog logger;
        protected T7Configuration configuration;

        @Override
        public void execute(Context context) {
            configuration = context.getConfiguration();
            if(configuration.isDeleteTomcatDefaultExamplesWebapp()){
                File webappsDirectory = new File(configuration.getCatalinaBase(), "/webapps");
                try {
                    FileUtils.deleteDirectory(new File(webappsDirectory, "/examples"));
                } catch (IOException e) {
                    throw new RuntimeException("Could not delete 'examples' directory", e);
                }
            }
        }
    }
    
    /**
     * 
     * TODO Comment
     *
     * @author Joerg Bellmann
     *
     */
    static class DeleteDocsWebapp implements Step {

        protected PluginLog logger;
        protected T7Configuration configuration;

        @Override
        public void execute(Context context) {
            configuration = context.getConfiguration();
            if(configuration.isDeleteTomcatDefaultDocsWebapp()){
                File webappsDirectory = new File(configuration.getCatalinaBase(), "/webapps");
                try {
                    FileUtils.deleteDirectory(new File(webappsDirectory, "/docs"));
                } catch (IOException e) {
                    throw new RuntimeException("Could not delete 'docs' directory", e);
                }
            }
        }
    }
}
