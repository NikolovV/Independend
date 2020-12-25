package _17_QuadKDIntervalTreesHashTables_Exercise._3_Phonebook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Phonebook {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        HashTable<String, String> contacts = new HashTable<>();
        String input = reader.readLine();

        while (!"search".equals(input)) {
            String[] contactData = input.split("-");
            String contactName = contactData[0];
            String contactPhone = contactData[1];

            contacts.addOrReplace(contactName, contactPhone);
            input = reader.readLine();
        }
        input = reader.readLine();

        while (!"end".equals(input)) {
            String currentContact = input;

            if (contacts.containsKey(currentContact)) {
                System.out.printf("%s -> %s%n", currentContact, contacts.get(currentContact));
            } else {
                System.out.printf("Contact %s does not exist.%n", currentContact);
            }

            input = reader.readLine();
        }
    }
}
