package at.ac.fhkufstein.Infrastructure.Util;

public class EncryptionUtil
{
    /**
     * Encrypts and Decrypts Data if the correct ShiftCount is given
     * @param data either encrypted or plain data
     * @param shiftCount shiftCount for en- or decryption
     * @return either encrypted or plain data
     */
    public static String shifter(String data, char shiftCount)
    {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < data.length(); i++)
        {
            char c = data.charAt(i);
            c += shiftCount;
            sb.append(c);
        }
        return sb.toString();
    }
}
