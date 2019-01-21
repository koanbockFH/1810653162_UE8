package at.ac.fhkufstein.Infrastructure.Repository;

import at.ac.fhkufstein.Common.Dto.RegistrationDto;
import at.ac.fhkufstein.Common.Repository.IRegistrationRepository;

import java.io.*;

public class RegistrationRepository implements IRegistrationRepository
{
    private String separator;
    private String filePath;

    /**
     * Create new Registration Repository that accesses DataStorage
     * @param filePath FilePath of Datafile
     * @param separator DataSeparator
     */
    public RegistrationRepository(String filePath, String separator)
    {
        this.filePath = filePath;
        this.separator = separator;
    }

    /**
     * Adds Registration to DataStorage
     * @param registration new Registration
     * @return True if adding was successful
     */
    public boolean add(RegistrationDto registration)
    {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true)))
        {
            bw.write(registration.getUsername() + separator + registration.getPassword() + System.lineSeparator());
        }
        catch(IOException ioEx)
        {
            ioEx.printStackTrace();
            return false; //not possible to add
        }
        return true;
    }

    /**
     * Searches for Registration in DataStorage by Username
     * @param username username to search for
     * @return Registration Details of searched user
     */
    public RegistrationDto getByUsername(String username)
    {
        File f = new File(filePath);
        if(!f.exists()) // check if File exists
        {
            return null;
        }
        try(BufferedReader br = new BufferedReader(new FileReader(filePath)))
        {
            String line;
            while((line = br.readLine()) != null)
            {
                String[] parts = line.split(separator);
                //Check if Line has all parts and matches username
                if(parts.length >= 2 && parts[0].equals(username))
                {
                    return new RegistrationDto(parts[0], parts[1]);
                }
            }
        }
        catch(IOException ioEx)
        {
            ioEx.printStackTrace();
        }
        return null;
    }
}
