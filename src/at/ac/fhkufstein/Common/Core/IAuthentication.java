package at.ac.fhkufstein.Common.Core;

public interface IAuthentication
{
    /**
     * Tries to LogIn User By asking for Username and Password
     * @return True if User has successfully logged In
     */
    boolean logIn();

    /**
     * Will Register User by given username and asking the users chosen password -
     * if Username if null he will get asked for a username
     * @param username Chosen Username if already known
     * @return True if User has been successfully registered
     */
    boolean register(String username);
}
