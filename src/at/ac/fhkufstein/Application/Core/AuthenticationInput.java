package at.ac.fhkufstein.Application.Core;

import at.ac.fhkufstein.Common.Core.IAuthenticationInput;

import javax.swing.*;

public class AuthenticationInput implements IAuthenticationInput
{
    public AuthenticationInput()
    {
    }

    /**
     * Gets Username from User
     * @return Username that the User has given
     */
    public String getUsername()
    {
        return getRequiredData("Gib dein Usernamen ein:");
    }

    /**
     * Gets Password from User
     * @return Password that the User has given
     */
    public String getPassword()
    {
        String password;
        while(true)
        {
            password = getRequiredData("Gib dein Passwort ein!");

            if(password == null || password.length() > 5)
            {
                break;
            }

            JOptionPane.showMessageDialog(
                    null,
                    "Passwort muss mind. 6 Zeichen lang sein!",
                    "Passwort Format fehlerhaft",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        return password;
    }

    /**
     * Asks the User if he wants to register
     * @return True if User wants to register
     */
    public boolean newRegistration()
    {
        return JOptionPane.showConfirmDialog(null, "Willst du dich registrieren?", "Registrierung", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

    /**
     * Gets required Data from User, empty Strings are not valid
     * @param message message to the user
     * @return Input of User, null shows Cancellation by user
     */
    private String getRequiredData(String message)
    {
        String data = "";
        while(data.equals(""))
        {
            data = JOptionPane.showInputDialog(message);
            //Exit Chance for User -> Cancel
            if(data == null)
            {
                break;
            }
        }
        return data;
    }
}
