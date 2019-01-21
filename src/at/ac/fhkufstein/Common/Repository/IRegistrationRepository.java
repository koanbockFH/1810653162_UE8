package at.ac.fhkufstein.Common.Repository;

import at.ac.fhkufstein.Common.Dto.RegistrationDto;

public interface IRegistrationRepository
{
    /**
     * Adds Registration to DataStorage
     * @param registration new Registration
     * @return True if adding was successful
     */
    boolean add(RegistrationDto registration);

    /**
     * Searches for Registration in DataStorage by Username
     * @param username username to search for
     * @return Registration Details of searched user
     */
    RegistrationDto getByUsername(String username);
}
