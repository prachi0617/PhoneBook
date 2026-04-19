package com.zipcodewilmington.phonebook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by leon on 1/23/18.
 * Made WAY better by kristofer 6/16/20
 */

public class PhoneBook {

    private final Map<String, List<String>> phonebook;

    public PhoneBook(Map<String, List<String>> map) {
        if (map != null) {
            this.phonebook = map;
        } else {
            this.phonebook = new LinkedHashMap<>();
        }
    }

    public PhoneBook() {
        this(new LinkedHashMap<>());
    }

    public void add(String name, String phoneNumber) {
        if (!phonebook.containsKey(name)) {
            phonebook.put(name, new ArrayList<>());
        }
        phonebook.get(name).add(phoneNumber);
    }

    public void addAll(String name, String... phoneNumbers) {
        if (!phonebook.containsKey(name)) {
            phonebook.put(name, new ArrayList<>());
        }
        for (String phoneNumber : phoneNumbers) {
            phonebook.get(name).add(phoneNumber);
        }
    }


    public void remove(String name) {
        if (this.phonebook.containsKey(name)) {
            // also remove all numbers from reverse_phonebook
            List<String> numbers = this.phonebook.get(name);
            for (String number : numbers) {
                this.reverse_phonebook.remove(number);
            }
            this.phonebook.remove(name);
            System.out.println("Removed: " + name);
        } else {
            System.out.println("Contact not found: " + name);
        }
    }

    public void removeNumber(String name, String phoneNumber) {
        if (this.phonebook.containsKey(name)) {
            this.phonebook.get(name).remove(phoneNumber);
            this.reverse_phonebook.remove(phoneNumber);
            System.out.println("Removed number " + phoneNumber + " from " + name);
            // if no numbers left, remove the contact entirely
            if (this.phonebook.get(name).isEmpty()) {
                this.phonebook.remove(name);
                System.out.println("No numbers left. Removed contact: " + name);
            }
        } else {
            System.out.println("Contact not found: " + name);
        }
    }

    

    public boolean hasEntry(String name) {
        return phonebook.containsKey(name);
    }

    
    public Boolean hasEntry(String name, String phoneNumber) {
        return phonebook.containsKey(name) && phonebook.get(name).contains(phoneNumber);
    }

    public List<String> lookup(String name) {
        return phonebook.getOrDefault(name, new ArrayList<>());
    }

    public String reverseLookup(String phoneNumber) {
        for (Map.Entry<String, List<String>> entry : phonebook.entrySet()) {
            if (entry.getValue().contains(phoneNumber)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public List<String> getAllContactNames() {
        return new ArrayList<>(phonebook.keySet());
    }

    public Map<String, List<String>> getMap() {
        return phonebook;
    }
}

public static void main(String[] args) {
        PhoneBook pb = new PhoneBook();
        List<String> phlist = pb.getAllContactNames();
        System.out.println("My Phone Book:");
        for (String name : phlist) {
            System.out.println(name);
        }
    }

}