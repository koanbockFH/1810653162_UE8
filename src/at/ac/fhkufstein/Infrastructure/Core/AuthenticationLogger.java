package at.ac.fhkufstein.Infrastructure.Core;

import at.ac.fhkufstein.Common.Dto.RegistrationDto;

import java.io.*;
import java.util.Date;

class AuthenticationLogger
{
    private String filePath;

    AuthenticationLogger(String filePath)
    {
        this.filePath = filePath;
    }

    /**
     * Counts Failed Logins for the given username
     * @param username username of User
     * @return failed Logins in History
     */
    int countFailedLogInByUsername(String username)
    {
        File f = new File(filePath);
        if(!f.exists()) // check if File exists
        {
            return 0;
        }

        int failCounter = 0;
        try(BufferedReader br = new BufferedReader(new FileReader(filePath)))
        {
            String line;
            while((line = br.readLine()) != null)
            {
                String[] parts = line.split(";");
                //Check if Line has all parts and matches username
                if(parts.length >= 4 && parts[1].equals(username) && parts[3].equals("false"))
                {
                    failCounter++;
                }
            }
        }
        catch(IOException ioEx)
        {
            ioEx.printStackTrace();
        }
        return failCounter;
    }

    /**
     * Add Authentication Login Try
     * @param username username used to login
     * @param password password used to login
     * @param successfulLogIn login result
     */
    void add(String username, String password, boolean successfulLogIn)
    {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true)))
        {
            bw.write(String.format("%s;%s;%s;%s%s", new Date(), username, getMaskedPassword(password), successfulLogIn, System.lineSeparator()));
        }
        catch(IOException ioEx)
        {
            ioEx.printStackTrace();
        }
    }

    /**
     * Mask Password
     * @param password password used in login try
     * @return masked password
     */
    private String getMaskedPassword(String password)
    {
        StringBuilder result = new StringBuilder();
        result.append(password, 0, 2);
        result.append("###");
        result.append(password, password.length()-2, password.length());

        return result.toString();
    }
}
