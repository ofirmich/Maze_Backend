package IO;
//new
import java.io.IOException;
import java.io.OutputStream;



public class MyCompressorOutputStream extends OutputStream {
    OutputStream out;
    public MyCompressorOutputStream(OutputStream outputStream){
        this.out = outputStream;
    }

    @Override
    public void write(int b) throws IOException {
        out.write(b);
    }

    @Override
    public void write(byte[] b) throws IOException {
//        ArrayList<Byte> listOfBytes = new ArrayList<Byte>();
//        ArrayList<Byte> compList = new ArrayList<Byte>();
//
//        for(int i = 0; i< 12 ; i++) {
//            listOfBytes.add(b[i]);
//        }
        int leftover=0;
        if((b.length-12) % 8 != 0 ){
            leftover =1;
        }
        byte[] bComp= new byte[((b.length-12) / 8) + leftover + 12];
        for(int i = 0; i< 12 ; i++) {
            bComp[i] = b[i];
        }

        int index = 12;

        String temp2="";
      //  try{
            int counter = 0;
            for(int j =12 ; j< b.length ; j++) {
                temp2 += Byte.toString(b[j]);
                counter++;
                if (counter % 8 == 0 || j == b.length - 1) {
                    int arr = Integer.parseInt(temp2, 2);
                    temp2 = "";
                    bComp[index] = (byte)(arr);
                    index++;
                    counter = 0;
                }
            }
  //      }catch(Exception e){
    //        int k =9;
     //   }

        out.write(bComp);
    }

}
