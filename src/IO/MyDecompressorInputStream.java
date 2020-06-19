package IO;

import sun.misc.IOUtils;


import java.io.IOException;
import java.io.InputStream;

import java.util.Collections;

public class MyDecompressorInputStream extends InputStream {
    InputStream in;

    public MyDecompressorInputStream(InputStream inputStream) {
        this.in = inputStream;
    }

    @Override
    public int read() throws IOException {
        return in.read();
    }

//    @Override
//    public int read(byte[] b) throws IOException {
//        int index = 0;
//        int leftover = 0;
//        if ((b.length - 12) % 8 != 0) {
//            leftover = 1;
//        }
//        int size = ((b.length - 12) / 8) + leftover + 12;
//        byte[] uncompressed = new byte[size];
//        //byte[] uncompressed = new byte[b.length];
//        in.read(uncompressed);
//        for (int i = 0; i < 12; i++) {
//            b[index] = uncompressed[i];
//            index++;
//        }
//        for (int i = 12; i < size; i++) {
//            int val = Byte.toUnsignedInt(uncompressed[i]);
//            String str = Integer.toBinaryString(val);
//            int zeroTimes = 8 - str.length();
//            if (i == size - 1) {
//                zeroTimes = b.length - index - str.length();
//            }
//            str = String.join("", Collections.nCopies(zeroTimes, "0")) + str;
//            for(int k=0;k<str.length();k++)
//            {
//                int x = Character.getNumericValue(str.charAt(k));
//                b[index] = ((byte) x);
//                index++;
//            }
//        }
//        return 1;
//    }
//}


    public int read(byte[] b) throws IOException {
        int index = 0;
        while (in.available() > 0) {
            if (index < 12) {
                b[index] = (byte) in.read();
                index++;
            }
            else {
                int val = Byte.toUnsignedInt((byte) in.read());
                String str = Integer.toBinaryString(val);
                int zeroTimes;
                if (in.available() > 0) {
                    zeroTimes = 8 - str.length();
                }
                else {
                    zeroTimes = b.length - index - str.length();
                }
                str = String.join("", Collections.nCopies(zeroTimes, "0")) + str;

                for (int k = 0; k < str.length(); k++) {
                    int x = Character.getNumericValue(str.charAt(k));
                    b[index] = ((byte) x);
                    index++;
                }
            }
        }
        return 1;
    }
}

