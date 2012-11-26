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
package com.googlecode.t7mp;

import com.google.common.base.Objects;

/**
 * Holds configuration for a single instance.
 * 
 * 
 * @author Joerg Bellmann
 *
 */
class InstanceConfiguration {

    private int id;
    private int httpPort;
    private int shutdownPort;
    private String catalinaBasePath;

    public InstanceConfiguration(int id, int httpPort, int shutdownPort, String catalinaBasePath) {
        this.id = id;
        this.httpPort = httpPort;
        this.shutdownPort = shutdownPort;
        this.catalinaBasePath = catalinaBasePath;
    }

    public int getHttpPort() {
        return httpPort;
    }

    public int getShutdownPort() {
        return shutdownPort;
    }

    public String getCatalinaBasePath() {
        return catalinaBasePath;
    }

    public int getId() {
        return id;
    }

    public String toString() {
        return Objects.toStringHelper(this).add("id", getId()).add("httpPort", getHttpPort()).add("shutdownPort", getShutdownPort())
                .add("catalinaBasePath", getCatalinaBasePath()).toString();
    }

}
