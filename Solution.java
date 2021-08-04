import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;



public class Solution {
    private static final boolean[] hval = {true,true,false,true,false,false,true};
    private static boolean matrix[][];
    private static int count90;
    private static int count180;
    private static int count270;
    private static boolean h(long n){
        int index = (int) (n % 7);
        return hval[index];
    }

    private static void buildMatrix(int n){
        matrix = new boolean[n][n];
        for(int i = 1;i <= n;i++){
            for(int j = 1;j <= n;j++){
                long val = (long)i * j;
                val *= val;
                matrix[i-1][j-1] = h(val);
            }
        }
    }
    
    private static void countDiffs(){
        final int maxIndex = matrix.length - 1;
        count90 = 0;
        count180 = 0;
        count270 = 0;
        for(int i = 0;i <= maxIndex;i++){
            for(int j = 0;j <= maxIndex;j++){
                if(matrix[i][j] != matrix[j][maxIndex - i])
                    count90++;
                if(matrix[i][j] != matrix[maxIndex - i][maxIndex - j])
                    count180++;
                if(matrix[i][j] != matrix[maxIndex - j][i])
                    count270++;
            }
        }
    }
     
    private static int countDiffAfterRot(int angle){
        angle %= 360;
        switch(angle){
            case 90:
                return count90;
            case 180:
                return count180;
            case 270:
                return count270;
        }
        return 0;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);
        
        buildMatrix(n);
        countDiffs();
        
        int q = Integer.parseInt(firstMultipleInput[1]);

        for (int qItr = 0; qItr < q; qItr++) {
            int angle = Integer.parseInt(bufferedReader.readLine().trim()) % 360;
            System.out.println(countDiffAfterRot(angle));
            // Write your code here
        }

        bufferedReader.close();
    }
}
