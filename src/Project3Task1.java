import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Project3Task1
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

    public static String hashMD5(String input) throws NoSuchAlgorithmException
    {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(input.getBytes());
        BigInteger no = new BigInteger(1, messageDigest);
        String hashtext = no.toString(16);
        hashtext = "0".repeat(32 - hashtext.length()) + hashtext;

        return hashtext;
    }

    private static String fileToString(File file) throws Exception
    {
        BufferedReader br=new BufferedReader(new FileReader(file));
        StringBuilder builder = new StringBuilder();
        builder.append(br.readLine());
        while((br.readLine())!=null)
        {
            builder.append(br.readLine());
        }
        return builder.toString();
    }

    private static void timeHashSHA256(String string,int timesRun) throws Exception
    {
        long startTime, stopTime, elapsedTime, totalTime = 0;
        double totAvgtime, totalTime2, timesRun2;

        for (int i = 0; i < timesRun; i++)
        {
            hashSHA256(string);
            startTime = System.currentTimeMillis();
            hashSHA256(string);
            stopTime = System.currentTimeMillis();
            elapsedTime = stopTime - startTime;
            totalTime += elapsedTime;
        }
        totalTime2 = totalTime;
        timesRun2 = timesRun;
        totAvgtime = totalTime2 / timesRun2;

        System.out.println("Approximately " + (1000/totAvgtime) + " hashes can be made in 1 sec.");
    }

    private static void timeHashMD5(String string,int timesRun) throws Exception
    {
        long startTime, stopTime, elapsedTime, totalTime = 0;
        double totAvgtime, totalTime2, timesRun2;

        for (int i = 0; i < timesRun; i++)
        {
            hashMD5(string);
            startTime = System.currentTimeMillis();
            hashMD5(string);
            stopTime = System.currentTimeMillis();
            elapsedTime = stopTime - startTime;
            totalTime += elapsedTime;
        }
        totalTime2 = totalTime;
        timesRun2 = timesRun;
        totAvgtime = totalTime2 / timesRun2;

        System.out.println("Approximately " + (1000/totAvgtime) + " hashes can be made in 1 sec.");
    }

    public static void main(String[] args) throws Exception
    {
        File fileS = new File("./src/smallfile.txt");
        File fileL = new File("./src/largefile.txt");
        String smallString = fileToString(fileS);
        String largeString = fileToString(fileL);

        System.out.print("Small File using Project3Task1:");
        timeHashSHA256(smallString,1000000);
        System.out.print("Large File using Project3Task1:");
        timeHashSHA256(largeString,1000000);

        System.out.print("Small File using MD5:");
        timeHashMD5(smallString,1000000);
        System.out.print("Large File using MD5:");
        timeHashMD5(largeString,1000000);

//        System.out.println("Small File Project3Task1: " + hashSHA256(s1));
//        System.out.println("Large File Project3Task1: " + hashSHA256(s2));
//        System.out.println("Small File MD5: " + hashMD5(s1));
//        System.out.println("Large File MD5: " + hashMD5(s2));
    }
}