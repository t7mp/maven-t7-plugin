/**
 * Copyright (C) 2010-2011 Joerg Bellmann <joerg.bellmann@googlemail.com>
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
package com.googlecode.t7mp.maven;

import com.google.common.base.Predicate;
import com.googlecode.t7mp.AbstractArtifact;
import com.googlecode.t7mp.PluginLog;

/**
 * 
 * @author Joerg Bellmann
 *
 */
class NoVersionPredicate implements Predicate<AbstractArtifact> {

    private final PluginLog log;

    public NoVersionPredicate(PluginLog log) {
        this.log = log;
    }

    @Override
    public boolean apply(AbstractArtifact artifact) {
        boolean apply = (artifact.getVersion() == null);
        if (apply) {
            log.debug("Artifact " + artifact.toString() + " has no version configured.");
        }
        return apply;
    }
}
