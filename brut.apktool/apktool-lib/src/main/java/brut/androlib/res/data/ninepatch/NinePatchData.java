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
package brut.androlib.res.data.ninepatch;

import brut.util.ExtDataInput;

import java.io.IOException;

public class NinePatchData {
    public final int padLeft, padRight, padTop, padBottom;
    public final int[] xDivs, yDivs;

    public NinePatchData(int padLeft, int padRight, int padTop, int padBottom, int[] xDivs, int[] yDivs) {
        this.padLeft = padLeft;
        this.padRight = padRight;
        this.padTop = padTop;
        this.padBottom = padBottom;
        this.xDivs = xDivs;
        this.yDivs = yDivs;
    }

    public static NinePatchData decode(ExtDataInput in) throws IOException {
        in.skipBytes(1); // wasDeserialized
        byte numXDivs = in.readByte();
        byte numYDivs = in.readByte();
        in.skipBytes(1); // numColors
        in.skipBytes(8); // xDivs/yDivs offset
        int padLeft = in.readInt();
        int padRight = in.readInt();
        int padTop = in.readInt();
        int padBottom = in.readInt();
        in.skipBytes(4); // colorsOffset
        int[] xDivs = in.readIntArray(numXDivs);
        int[] yDivs = in.readIntArray(numYDivs);

        return new NinePatchData(padLeft, padRight, padTop, padBottom, xDivs, yDivs);
    }
}
