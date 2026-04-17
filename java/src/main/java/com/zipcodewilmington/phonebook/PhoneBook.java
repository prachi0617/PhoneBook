package com.zipcodewilmington.phonebook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by leon on 1/23/18.
 * Made WAY better by kristofer 6/16/20
 * Bugs fixed: import typo, new ArrayList<>() typo, addAll varargs loop,
 * hasEntry(name, phoneNumber) overload added.
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

    // ── BUG 1 FIXED ─────────────────────────────────────────────────────────────
    // Was: phonebook.put(name, newArrayList<>()); ← missing space
    // Fix: phonebook.put(name, new ArrayList<>());
    // ────────────────────────────────────────────────────────────────────────────
    public void add(String name, String phoneNumber) {
        if (!phonebook.containsKey(name)) {
            phonebook.put(name, new ArrayList<>());
        }
        phonebook.get(name).add(phoneNumber);
    }

    // ── BUG 2 FIXED ─────────────────────────────────────────────────────────────
    // Was: phonebook.get(name).add(phoneNumber); ← wrong var (singular, undefined)
    // Fix: iterate the varargs array and add each element
    // ────────────────────────────────────────────────────────────────────────────
    public void addAll(String name, String... phoneNumbers) {
        if (!phonebook.containsKey(name)) {
            phonebook.put(name, new ArrayList<>());
        }
        for (String phoneNumber : phoneNumbers) {
            phonebook.get(name).add(phoneNumber);
        }
    }

    public void remove(String name) {
        phonebook.remove(name);
    }

    // ── BUG 3 FIXED ─────────────────────────────────────────────────────────────
    // RemoveTest & ReverseLookupTest call hasEntry(name, phoneNumber) — two args.
    // The original only had a one-arg version. Added an overload that checks both
    // the key AND whether the list contains the given phone number.
    // ────────────────────────────────────────────────────────────────────────────
    public Boolean hasEntry(String name) {
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