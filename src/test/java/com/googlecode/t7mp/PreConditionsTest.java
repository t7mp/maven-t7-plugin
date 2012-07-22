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

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class PreConditionsTest {

    private final PluginLog log = Mockito.mock(PluginLog.class);

    @Test(expected = TomcatSetupException.class)
    public void testWrongVersion_5() {
        PreConditions.checkConfiguredTomcatVersion(log, "5.x");
    }

    @Test(expected = TomcatSetupException.class)
    public void testWrongVersion_8() {
        PreConditions.checkConfiguredTomcatVersion(log, "8.x");
    }

    @Test
    public void testCorrectVersion_7() {
        PreConditions.checkConfiguredTomcatVersion(log, "7.x");
    }

    @Test
    public void testCorrectVersion_6() {
        PreConditions.checkConfiguredTomcatVersion(log, "6.x");
    }

    @Test
    public void testPrivateConstructor() {
        try {
            Invoke.privateConstructor(PreConditions.class);
        } catch (Exception e) {
            Assert.assertEquals("Dont call this private constructor", e.getCause().getMessage());
        }
    }

}
