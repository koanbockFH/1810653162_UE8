package at.ac.fhkufstein.Application;

import at.ac.fhkufstein.Infrastructure.Core.Authentication;
import at.ac.fhkufstein.Application.Core.AuthenticationInput;
import at.ac.fhkufstein.Common.Core.IAuthentication;
import at.ac.fhkufstein.Common.Core.IAuthenticationInput;
import at.ac.fhkufstein.Common.Repository.IRegistrationRepository;
import at.ac.fhkufstein.Infrastructure.Repository.RegistrationRepository;

public class Application
{
    public static void main(String[] args)
    {
        String filePath = "C:\\temp\\test.txt";
        String logFilePath = "C:\\temp\\log.txt";
        String separator = ";";
        IRegistrationRepository regRepo = new RegistrationRepository(filePath, separator);
        IAuthenticationInput authInput = new AuthenticationInput();
        IAuthentication auth = new Authentication(regRepo, authInput,logFilePath);

        while(true)
        {
            if(auth.logIn())
            {
                secret();
                return;
            }
        }
    }
    private static void secret()
    {
        System.out.println("The answer to the Ultimate Question of Life, The Universe, and Everything is......... 42");
    }
}
