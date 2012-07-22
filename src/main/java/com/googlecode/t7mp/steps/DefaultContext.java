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

import java.util.HashMap;
import java.util.Map;

import com.googlecode.t7mp.DefaultPluginLog;
import com.googlecode.t7mp.PluginLog;
import com.googlecode.t7mp.T7Configuration;
import com.googlecode.t7mp.configuration.PluginArtifactResolver;

/**
 * 
 * Implementation of an {@link Context}.
 * 
 * @author Joerg Bellmann
 *
 */
public class DefaultContext implements Context {

    protected Map<String, Object> context = new HashMap<String, Object>();

    protected PluginArtifactResolver artifactResolver;
    protected T7Configuration configuration;

    public DefaultContext(PluginArtifactResolver artifactResolver, T7Configuration configuration) {
        this.artifactResolver = artifactResolver;
        this.configuration = configuration;
    }

    @Override
    public PluginLog getLog() {
        return new DefaultPluginLog();
    }

    @Override
    public PluginArtifactResolver getArtifactResolver() {
        return artifactResolver;
    }

    @Override
    public T7Configuration getConfiguration() {
        return configuration;
    }

    @Override
    public void put(String key, Object value) {
        this.context.put(key, value);
    }

    @Override
    public Object get(String key) {
        return this.context.get(key);
    }

    @Override
    public void clear() {
        this.context.clear();
    }

}
