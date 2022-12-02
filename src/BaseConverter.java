import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
/**
 * Prints a conversion for a variety of numbers as specified by a data file.
 * @version 11/16/22
 * @author 24mebane
 */
public class BaseConverter {
    private final String DIGITS = "0123456789ABCDEF";
    /**
     * Convert a String num in fromBase to base-10 int.
     * @param num the original number
     * @param fromBase the original from base
     * @return a base-10 int of num base fromBase
     */
    public int strToInt(String num, String fromBase)    {
        int value = 0, decimalBase, exp = 0;
        /*
        start at the right-most digit of num
        run a loop for x or i digits of num -- backwards
            pull out the character at index 'i'
            find the indexOf that character in DIGITS
            value += indexOf character * Math.pow(fromBase, exp);
         */
        for(int i = num.length()-1; i>=0; i--){
            value += DIGITS.indexOf(num.charAt(i)) * Math.pow(Integer.parseInt(fromBase), exp);
            exp++;
        }

        return value;
    }

    /**
     * Converts an int num in base-10 to a String that is in a new base
     * @param num on original base-10 number
     * @param toBase the new base that that number is converting to
     * @return the base-10 number converted to a new base
     */
    public String intToStr(int num, int toBase) {
        String toNum = new String();
        int index = -1;
    while(num > 0 ) {
        toNum = "" + DIGITS.charAt(num % toBase) + toNum;
        num /= toBase;
    }
        return (toNum.equals("")) ? "0" : toNum;
    }

    /**
     * Take values from a data file
     * convert the values to a new base and write it to converted.dat (using strToInt and intToStr)
     */
    public void inputConvertPrintWrite()    {
        Scanner in = null;
        PrintWriter out = null;
        try {
            in = new Scanner(new File("datafiles/values30.dat"));
            out = new PrintWriter(new File("datafiles/converted.dat"));
            String[] line; //this is an array of string called line
            String output;
            while(in.hasNext()) {
                line = in.nextLine().split("\t");
                // TO WRITE TO THE FILE:
                // write the original number - STRING
                // tabb
                // write the original base - STRING
                // tab
                // write the converted number
                // tab
                // write the toBase - STRING
              if(Integer.parseInt(line[1]) < 2 || Integer.parseInt(line[1]) > 16)
                  System.out.println("Invalid input base " + line[1]);
               else if(Integer.parseInt(line[2]) < 2 || Integer.parseInt(line[2]) > 16)
                    System.out.println("Invalid output base " + line[1]);
               else {
                   output = intToStr(strToInt(line[0], line[1]), Integer.parseInt(line[2]));
                  out.println(line[0] + "\t" + line[1] + "\t" + output + "\t" + line[2]);
                  System.out.println(line[0] + " base " + line[1] + " = " + output + " base " + line[2]);
              }
            }

            if(out != null)
                out.close();
            if(in != null)
                in.close();
            //System.out.println("The revolution will not be televised. ");
        }
        catch(Exception e) {
            System.out.println("Something bad happened. Details here: " + e.toString());
        }
    }

    /**
     * Main method of class BaseConverter
     * @param args Command-line arguments, if needed.
     */
    public static void main(String[] args) {
        BaseConverter bc = new BaseConverter();
        bc.inputConvertPrintWrite();
    }
}