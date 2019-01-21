package at.ac.fhkufstein.Common.Core;

public interface IAuthenticationInput
{
    /**
     * Gets Username from User
     * @return Username that the User has given
     */
    String getUsername();

    /**
     * Gets Password from User
     * @return Password that the User has given
     */
    String getPassword();

    /**
     * Asks the User if he wants to register
     * @return True if User wants to register
     */
    boolean newRegistration();
}
