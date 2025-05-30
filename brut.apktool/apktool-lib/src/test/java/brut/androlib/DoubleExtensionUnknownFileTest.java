/*
 *  Copyright (C) 2010 Ryszard Wiśniewski <brut.alll@gmail.com>
 *  Copyright (C) 2010 Connor Tumbleson <connor.tumbleson@gmail.com>
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package brut.androlib;

import brut.androlib.meta.ApkInfo;
import brut.common.BrutException;
import brut.directory.ExtFile;
import org.apache.commons.lang3.StringUtils;

import org.junit.*;
import static org.junit.Assert.*;

public class DoubleExtensionUnknownFileTest extends BaseTest {
    private static final String TEST_APK = "issue1244.apk";

    @BeforeClass
    public static void beforeClass() throws Exception {
        TestUtils.copyResourceDir(DoubleExtensionUnknownFileTest.class, "issue1244", sTmpDir);
    }

    @Test
    public void multipleExtensionUnknownFileTest() throws BrutException {
        ExtFile testApk = new ExtFile(sTmpDir, TEST_APK);
        ExtFile testDir = new ExtFile(testApk + ".out");
        new ApkDecoder(testApk, sConfig).decode(testDir);

        ApkInfo testInfo = ApkInfo.load(testDir);
        for (String path : testInfo.getDoNotCompress()) {
            if (StringUtils.countMatches(path, ".") > 1) {
                assertTrue(path.equals("assets/bin/Data/sharedassets1.assets.split0"));
            }
        }
    }
}
