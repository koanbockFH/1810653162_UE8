package Aufgabe_5;

import javax.swing.*;

public class Main
{
    public static void main(String[] args)
    {
        Sniffer snif = new Sniffer("C:\\temp\\Aufgabe5.txt");
        snif.generateFile();

        int number = Integer.parseInt(JOptionPane.showInputDialog("Gib nummer her:"));

        System.out.println("Vorkommen: " + snif.countNumber(number));
    }
}
