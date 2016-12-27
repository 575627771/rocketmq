/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.apache.rocketmq.broker.filtersrv;

import org.slf4j.Logger;


public class FilterServerUtil {
    public static void callShell(final String shellString, final Logger log) {
        Process process = null;
        try {
            String[] cmdArray = splitShellString(shellString);
            process = Runtime.getRuntime().exec(cmdArray);
            process.waitFor();
            log.info("callShell: <{}> OK", shellString);
        } catch (Throwable e) {
            log.error("callShell: readLine IOException, " + shellString, e);
        } finally {
            if (null != process)
                process.destroy();
        }
    }

    private static String[] splitShellString(final String shellString) {
        return shellString.split(" ");
    }
}
