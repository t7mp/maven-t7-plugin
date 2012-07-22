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

import com.googlecode.t7mp.PluginLog;
import com.googlecode.t7mp.T7Configuration;
import com.googlecode.t7mp.configuration.PluginArtifactResolver;

/**
 * Defines an execution context that will be passed between steps.
 * 
 * @author Joerg Bellmann
 *
 */
public interface Context {

    PluginLog getLog();

    PluginArtifactResolver getArtifactResolver();

    T7Configuration getConfiguration();

    void put(String key, Object value);

    Object get(String key);

    void clear();

}
