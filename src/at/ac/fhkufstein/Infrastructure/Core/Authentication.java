package at.ac.fhkufstein.Infrastructure.Core;

import at.ac.fhkufstein.Common.Dto.RegistrationDto;
import at.ac.fhkufstein.Common.Core.IAuthentication;
import at.ac.fhkufstein.Common.Core.IAuthenticationInput;
import at.ac.fhkufstein.Common.Repository.IRegistrationRepository;
import at.ac.fhkufstein.Infrastructure.Util.EncryptionUtil;

public class Authentication implements IAuthentication
{
    private IRegistrationRepository regRepo;
    private IAuthenticationInput authInput;
    private final char encryptionKey = 2656;
    private AuthenticationLogger Logger;

    public Authentication(IRegistrationRepository regRepo,
                          IAuthenticationInput authInput,
                          String logFilePath)
    {
        this.regRepo = regRepo;
        this.authInput = authInput;
        this.Logger = new AuthenticationLogger(logFilePath);
    }

    /**
     * Tries to LogIn User By asking for Username and Password
     * @return True if User has successfully logged In
     */
    public boolean logIn()
    {
        String username = authInput.getUsername();

        RegistrationDto user = regRepo.getByUsername(username);
        if (user != null)
        {
            String password = authInput.getPassword();

            char decryptionKey = (char) (0 - encryptionKey); //using Overflow to generate backshift key
            boolean result = EncryptionUtil.shifter(user.getPassword(), decryptionKey).equals(password);
            Logger.add(username, password, result);
            if(result)
            {
                System.out.println("Failed Logins: " + Logger.countFailedLogInByUsername(username));
            }
            return result;
        }
        //Ask User if he wants to register
        if(username != null && authInput.newRegistration())
        {
            return register(username);
        }
        return false;
    }

    /**
     * Will Register User by given username and asking the users chosen password -
     * if Username if null he will get asked for a username
     * @param username Chosen Username if already known
     * @return True if User has been successfully registered
     */
    public boolean register(String username)
    {
        //If registration is called without username, get again
        if (username == null)
        {
            username = authInput.getUsername();
        }

        //get for password
        String password = EncryptionUtil.shifter(authInput.getPassword(), encryptionKey);

        //either one not given = no registration possible
        if(password == null || username == null)
        {
            return false;
        }
        regRepo.add(new RegistrationDto(username, password));
        return true;
    }
}
