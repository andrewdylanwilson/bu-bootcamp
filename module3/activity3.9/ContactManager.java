import java.util.*;

public class ContactManager {
    public static void main(String[] args) {
        HashMap<String, Contact> contacts = new HashMap<>();

        contacts.put("Ada Lovelace", new Contact("Ada Lovelace", "+1 617 555 0101"));
        contacts.put("Alan Turing", new Contact("Alan Turing", "+1 617 555 0102"));
        contacts.put("Grace Hopper", new Contact("Grace Hopper", "+1 617 555 0103"));
        contacts.put("Katherine Johnson", new Contact("Katherine Johnson", "+1 617 555 0104"));
        contacts.put("Margaret Hamilton", new Contact("Margaret Hamilton", "+1 617 555 0105"));

        Contact found = contacts.get("Ada Lovelace");
        if (found == null) {
            System.out.println("Contact not found.");
        } else {
            System.out.println("Found: " + found);
        }

        Contact missing = contacts.get("Charles Babbage");
        if (missing == null) {
            System.out.println("Contact not found.");
        } else {
            System.out.println("Found: " + missing);
        }

        ArrayList<Contact> sorted = new ArrayList<>(contacts.values());
        sorted.sort((a, b) -> a.getName().compareTo(b.getName()));
        System.out.println("=== All Contacts ===");
        for (Contact c : sorted) {
            System.out.println(c);
        }

        removeContact(contacts, "Grace Hopper");

        ArrayList<Contact> sortedAfterRemoval = new ArrayList<>(contacts.values());
        sortedAfterRemoval.sort((a, b) -> a.getName().compareTo(b.getName()));
        System.out.println("=== All Contacts After Removal ===");
        for (Contact c : sortedAfterRemoval) {
            System.out.println(c);
        }
    }

    public static void removeContact(HashMap<String, Contact> contacts, String name) {
        if (contacts.containsKey(name)) {
            contacts.remove(name);
            System.out.println("Removed: " + name);
        } else {
            System.out.println(name + " was not found in contacts.");
        }
    }
}
