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

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.googlecode.t7mp.steps.ArtifactDeploymentSequence;
import com.googlecode.t7mp.steps.ConfigFilesSequence;
import com.googlecode.t7mp.steps.CopyConfigResourcesFromClasspathSequence;
import com.googlecode.t7mp.steps.ResolveTomcatStep;
import com.googlecode.t7mp.steps.SetSystemPropertiesStep;
import com.googlecode.t7mp.steps.Step;
import com.googlecode.t7mp.steps.TomcatSetupSequence;
import com.googlecode.t7mp.steps.WebappSequence;

public class TomcatSetupSequenceTest {
    
    @Test
    public void testTomcatSetupSequence(){
        TestTomcatSetupSequence sequence = new TestTomcatSetupSequence();
        Assert.assertEquals("6 Steps expected", 6, sequence.getSteps().size());
        Step three = sequence.getSteps().get(0);
        Assert.assertTrue(three instanceof ResolveTomcatStep);
        Step four = sequence.getSteps().get(1);
        Assert.assertTrue(four instanceof CopyConfigResourcesFromClasspathSequence);
        Step five = sequence.getSteps().get(2);
        Assert.assertTrue(five instanceof ConfigFilesSequence);
        Step six = sequence.getSteps().get(3);
        Assert.assertTrue(six instanceof ArtifactDeploymentSequence);
        Step seven = sequence.getSteps().get(4);
        Assert.assertTrue(seven instanceof WebappSequence);
        Step eight = sequence.getSteps().get(5);
        Assert.assertTrue(eight instanceof SetSystemPropertiesStep);
    }
    
    static class TestTomcatSetupSequence extends TomcatSetupSequence {

        public List<Step> getSteps(){
            return super.sequence;
        }
    }

}
