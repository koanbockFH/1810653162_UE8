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

    public Authentication(IRegistrationRepository regRepo,
                          IAuthenticationInput authInput)
    {
        this.regRepo = regRepo;
        this.authInput = authInput;
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
            char decryptionKey = (char) (0 - encryptionKey); //using Overflow to generate backshift key
            return EncryptionUtil.shifter(user.getPassword(), decryptionKey).equals(authInput.getPassword());
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
