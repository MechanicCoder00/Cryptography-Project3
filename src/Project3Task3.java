import java.security.NoSuchAlgorithmException;
import java.math.*;

public class Project3Task3
{
    private static void hashNSeconds(int n) throws NoSuchAlgorithmException
    {
        long startTime,stopTime,elapsedTime;
        String lowestHashtext,comparisonHashtext,stringtoHash;
        int incrementforString=0;
        BigInteger totalDecValueofHex = BigInteger.valueOf(0);
        BigInteger averageDec;
        for (int i=0;i<10;i++)
        {
            lowestHashtext=null;
            elapsedTime=0;
            for (;elapsedTime<(n*1000);incrementforString++)
            {
                startTime = System.currentTimeMillis();

                stringtoHash = Integer.toString(incrementforString);
                stringtoHash = "0".repeat(16 - stringtoHash.length()) + stringtoHash;
                if(lowestHashtext==null)
                {
                    lowestHashtext = Project3Task1.hashSHA256(stringtoHash);
                }
                else
                {
                    comparisonHashtext = Project3Task1.hashSHA256(stringtoHash);
                    if(lowestHashtext.compareTo(comparisonHashtext)>0)
                    {
                        lowestHashtext=comparisonHashtext;
                    }
                }
                stopTime = System.currentTimeMillis();
                elapsedTime += (stopTime-startTime);
            }

            BigInteger currentLowestDecValue = new BigInteger(lowestHashtext, 16);
            totalDecValueofHex = totalDecValueofHex.add(currentLowestDecValue);
        }
        averageDec = totalDecValueofHex.divide(BigInteger.valueOf(10));
        String lowestHashaverage = averageDec.toString(16);
        lowestHashaverage = "0".repeat(64 - lowestHashaverage.length()) + lowestHashaverage;
//        displayMath(lowestHashaverage);
        System.out.println("Average lowest hex value after hashing " + n + " seconds: " + lowestHashaverage);
    }

    private static void displayMath(String s1)
    {
        BigInteger num1 = new BigInteger(s1, 16);
        BigInteger num2 = new BigInteger("100000000000000000000000000000000000000000000000000000000000000000000");
        num1 = num1.divide(num2);
        System.out.println("Number: " + num1);
    }

    public static void main(String[] args) throws Exception
    {
        hashNSeconds(10); //1:40
        hashNSeconds(20); //3:20
        hashNSeconds(30); //5:00
//        hashNSeconds(40); //6:40
//        hashNSeconds(50); //8:20
//        hashNSeconds(60); //10:00
    }
}
