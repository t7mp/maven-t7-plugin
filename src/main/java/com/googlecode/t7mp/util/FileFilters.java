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
package com.googlecode.t7mp.util;

import java.io.File;
import java.io.FileFilter;

/**
 * Static methods to create configured {@link FileFilter} objects.
 * 
 * @author Joerg Bellmann
 *
 */
public final class FileFilters {

    public static final String XML_SUFFIX = ".xml";
    public static final String TXT_SUFFIX = ".txt";
    public static final String PROPERTIES_SUFFIX = ".properties";
    public static final String JAVA_SUFFIX = ".java";

    private FileFilters() {
        // hide constructor
    }

    public static FileFilter forAll() {
        return new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isFile();
            }
        };
    }

    public static FileFilter forXmlFiles() {
        return new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isFile() && file.getName().endsWith(XML_SUFFIX);
            }
        };
    }

    public static FileFilter forTxtFiles() {
        return new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isFile() && file.getName().endsWith(TXT_SUFFIX);
            }
        };
    }

    public static FileFilter forPropertyFiles() {
        return new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isFile() && file.getName().endsWith(PROPERTIES_SUFFIX);
            }
        };
    }

    public static FileFilter forJavaFiles() {
        return new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isFile() && pathname.getName().endsWith(JAVA_SUFFIX);
            }
        };
    }
}
