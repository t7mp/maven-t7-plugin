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
package com.googlecode.t7mp.newTests;

import org.junit.Ignore;
import org.junit.Test;

import com.googlecode.t7mp.AbstractBaseTest;
import com.googlecode.t7mp.steps.DefaultContext;
import com.googlecode.t7mp.steps.ResolveTomcatStep;

/**
 * 
 * @author Joerg Bellmann
 *
 */
@Ignore
public class ResolveTomcatStepTest extends AbstractBaseTest {

    @Test
    public void testResolveTomcatStep() {
        DefaultContext context = new DefaultContext(chainedArtifactResolver, this.configuration);
        ResolveTomcatStep step = new ResolveTomcatStep();
        step.execute(context);
        System.out.println(configuration.getCatalinaBase().getAbsolutePath());
    }

}
