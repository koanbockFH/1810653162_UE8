package Aufgabe_5;

import java.io.*;
import java.util.Random;

 class Sniffer
{
    private String filePath;

    Sniffer(String filePath)
    {
        this.filePath = filePath;
    }

    int countNumber(int number)
    {
        File f = new File(filePath);
        if(!f.exists()) // check if File exists
        {
            return 0;
        }

        int counter = 0;
        try(BufferedReader br = new BufferedReader(new FileReader(filePath)))
        {
            String line;
            while((line = br.readLine()) != null)
            {
                int lineNumber = Integer.parseInt(line);
                if(lineNumber == number)
                {
                    counter++;
                }
            }
        }
        catch(IOException ioEx)
        {
            ioEx.printStackTrace();
        }
        return counter;
    }

    void generateFile()
    {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filePath)))
        {
            for(int i = 0; i < 50; i++)
            {
                Random rnd = new Random();
                int number = rnd.nextInt(50);
                bw.write(number + System.lineSeparator());
            }
        }
        catch(IOException ioEx)
        {
            ioEx.printStackTrace();
        }
    }
}
