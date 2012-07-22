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

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import com.googlecode.t7mp.BaseConfiguration;
import com.googlecode.t7mp.configuration.PluginArtifactResolver;

/**
 * 
 * @author Joerg Bellmann
 *
 */
public class DefaultContextTest {

    private final BaseConfiguration baseConfiguration = Mockito.mock(BaseConfiguration.class);
    private final PluginArtifactResolver artifactResolver = Mockito.mock(PluginArtifactResolver.class);

    //    private final PluginLog log = Mockito.mock(PluginLog.class);

    @Test
    public void testDefaultContext() {
        Context context = new DefaultContext(artifactResolver, baseConfiguration);
        context.put("TEST_KEY", "TEST_VALUE");
        Assert.assertNotNull(context.get("TEST_KEY"));
        Assert.assertEquals("TEST_VALUE", context.get("TEST_KEY"));
        Assert.assertNotNull(context.getConfiguration());
        Assert.assertNotNull(context.getArtifactResolver());
        context.clear();
        Assert.assertNull(context.get("TEST_KEY"));
    }

}
