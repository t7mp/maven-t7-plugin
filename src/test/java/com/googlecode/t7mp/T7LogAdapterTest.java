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

import org.junit.Test;

public class T7LogAdapterTest {

    @Test
    public void testT7LogAdapter() {
        PluginLog log = new DefaultPluginLog();
        log.debug("A Debug-Message");
        log.info("A Info-Message");
        log.warn("A Warn-Message");
        log.error("A Error-Message");
        log.error("A Error-Message with Throwable", new RuntimeException("Just for the test."));

        PluginLog inside = new LookInsideLog(log);
        inside.debug("A INSIDE-Debug-Message");
        inside.info("A INSIDE-Info-Message");
        inside.warn("A-INSIDE-Warn-Message");
        inside.error("A INSIDE-Error-Message");
    }

}
