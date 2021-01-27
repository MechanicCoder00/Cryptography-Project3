import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class Project3Task2
{
    public static String hashSHA256(String input) throws NoSuchAlgorithmException
    {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] messageDigest = md.digest(input.getBytes());
        BigInteger no = new BigInteger(1, messageDigest);
        String hashtext = no.toString(16);
        hashtext = "0".repeat(64 - hashtext.length()) + hashtext;

        return hashtext;
    }

    public static void match2Digits(String stringtoMatch) throws NoSuchAlgorithmException
    {
        String hashtext,s1;
        int i=0;
        Boolean match=false;

        while(!match)
        {
            s1 = Integer.toString(i);
            s1 = "0".repeat(16 - s1.length()) + s1;
            hashtext = hashSHA256(s1);
            if (Objects.equals(stringtoMatch.substring(0, 2), hashtext.substring(0, 2)))
            {
                match = true;
//                System.out.println("\nFirst 2 digits matched");
//                System.out.println("Input string: " + s1);
//                System.out.println("Hash Text: " + hashtext);
            } else {
                i++;
            }
        }
    }

    private static void timeMatch2(String stringtoMatch,int timesRun) throws Exception
    {
        long startTime, stopTime, elapsedTime, totalTime = 0;
        double totAvgtime, totalTime2, timesRun2;

        for (int i = 0; i < timesRun; i++)
        {
            match2Digits(stringtoMatch);
            startTime = System.currentTimeMillis();
            match2Digits(stringtoMatch);
            stopTime = System.currentTimeMillis();
            elapsedTime = stopTime - startTime;
            totalTime += elapsedTime;
        }
        totalTime2 = totalTime;
        timesRun2 = timesRun;
        totAvgtime = totalTime2 / timesRun2;

        System.out.println("Approximate time for first 2 digit match: " + totAvgtime + "ms");
    }

        public static void match4Digits(String stringtoMatch) throws NoSuchAlgorithmException
        {
            String hashtext,s1;
            int i = 0;
            Boolean match = false;

            while (!match)
            {
                s1 = Integer.toString(i);
                s1 = "0".repeat(16 - s1.length()) + s1;
                hashtext = hashSHA256(s1);
                if (Objects.equals(stringtoMatch, hashtext.substring(0, 4)))
                {
                    match = true;
//                    System.out.println("\nFirst 4 digits matched");
//                    System.out.println("Input string: " + s1);
//                    System.out.println("Hash Text: " + hashtext);
                } else {
                    i++;
                }
            }
        }

    private static void timeMatch4(String stringtoMatch,int timesRun) throws Exception
    {
        long startTime, stopTime, elapsedTime, totalTime = 0;
        double totAvgtime, totalTime2, timesRun2;

        for (int i = 0; i < timesRun; i++)
        {
            match4Digits(stringtoMatch);
            startTime = System.currentTimeMillis();
            match4Digits(stringtoMatch);
            stopTime = System.currentTimeMillis();
            elapsedTime = stopTime - startTime;
            totalTime += elapsedTime;
        }
        totalTime2 = totalTime;
        timesRun2 = timesRun;
        totAvgtime = totalTime2 / timesRun2;

        System.out.println("Approximate time for first 4 digit match: " + totAvgtime + "ms");
    }


    public static void main(String[] args) throws Exception
    {
        String stringtoMatch="0916";

        timeMatch2(stringtoMatch,10);
        timeMatch4(stringtoMatch,10);

//        match2Digits(stringtoMatch);
//        match4Digits(stringtoMatch);



//        hashtext = hashSHA256(s1);
//        System.out.println("Hash Text: " + hashtext);
    }
}
