package com.zipcodewilmington.phonebook;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by leon on 1/23/18.
 * Made WAY better by kristofer 6/16/20
 */
public class PhoneBook {

    private final Map<String, List<String>> phonebook;
    private final Map<String, String> reverse_phonebook;

    public PhoneBook(Map<String, List<String>> map) {
        this.phonebook = map;
        this.reverse_phonebook = new TreeMap<>();
    }

    public PhoneBook() {
        this.phonebook = new TreeMap<>();
        this.reverse_phonebook = new TreeMap<>();
    }

    private void addRevNumber(String number, String name) {
        this.reverse_phonebook.put(number, name);
    }

    public void add(String name, String phoneNumber) {
        if (this.hasEntry(name)) {
            List<String> l = this.phonebook.get(name);
            l.add(phoneNumber);
            addRevNumber(phoneNumber, name);
        } else {
            List<String> l = new ArrayList<>();
            l.add(phoneNumber);
            addRevNumber(phoneNumber, name);
            this.phonebook.put(name, l);
        }
    }

    public void addAll(String name, String... phoneNumbers) {
        if (this.hasEntry(name)) {
            List<String> l = this.phonebook.get(name);
            loopThruAndAdd(l, name, phoneNumbers);
        } else {
            List<String> l = new ArrayList<>();
            loopThruAndAdd(l, name, phoneNumbers);
            this.phonebook.put(name, l);
        }
    }

    private void loopThruAndAdd(List<String> foo, String name, String... strings) {
        for (String s : strings) {
            foo.add(s);
            addRevNumber(s, name);
        }
    }

    public List<String> lookup(String name) {
        return this.phonebook.get(name);
    }

    public String reverseLookup(String phoneNumber) {
        if (this.reverse_phonebook.containsKey(phoneNumber)) {
            return this.reverse_phonebook.get(phoneNumber);
        }
        return "not found.";
    }

    public void remove(String name) {
     tis.po

    
    public Boolean hasEntry(String name) {
        return this.phonebook.containsKey(name);
    }


    return new ArrayList<>(this.phonebook.keySet());
    }

    public Map<String, List<String>> getMap() {
        return this.phonebook;
    }

    public static void main(String[] args) {
        PhoneBook pb = new PhoneBook();

    }
}