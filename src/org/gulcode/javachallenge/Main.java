package org.gulcode.javachallenge;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Contact>contacts;
    private static Scanner scanner;
    private static int id=0;
    public static void main(String[] args) {
        contacts=new ArrayList<>();
        System.out.println("Welcome to gulcode world for programing");
        showInitialOptions();

    }

    private static void showInitialOptions() {
        System.out.println("Please select one :"+
                "\n\t1. Manage Contact"+
                "\n\t2. Messages"+
                "\n\t3.Quit");
        scanner=new Scanner(System.in);
        int choice=scanner.nextInt();

        switch (choice){
            case 1:
                manageContacts();
                break;
            case 2:
                manageMessage();
                break;
            default:
                break;

        }
    }

    private static void manageMessage() {
        System.out.println("Please select one :"+
                "\n\t1. Show all message" +
                "\n\t2. Send a new message"+
                "\n\t3. Go Back");
        int choice=scanner.nextInt();
        switch (choice){
            case 1:
                showAllMessage();
                break;
            case 2:
                sendNewMessage();
                break;
            default:
                showInitialOptions();
                break;
        }
    }

    private static void sendNewMessage() {
        System.out.println("Who are you going to send message");
        String name=scanner.next();
        if (name.equals("")){
            System.out.println("Please enter the name of contact ");
            sendNewMessage();
        }else {
            boolean doesExist=false;
            for (Contact c:contacts){
                if (c.getName().equals(name)){
                    doesExist=true;
                }
            }
            if (doesExist){
                System.out.println("What are you going to say ?");
                String text=scanner.next();
                if (text.equals("")){
                    System.out.println("Please enter message");
                    sendNewMessage();
                }else {
                    id++;
                    Massage newMassage=new Massage(text,name,id);
                    for (Contact c:contacts){
                        if (c.getName().equals(name)){
                            ArrayList<Massage>newMassages= c.getMassages();
                            newMassages.add(newMassage);
                            c.setMassages(newMassages);
                        }
                    }
                }

            }else {
                System.out.println("There is no such contact");
            }
        }
        showInitialOptions();
    }

    private static void showAllMessage() {
        ArrayList<Massage>allMassages=new ArrayList<Massage>();
        for (Contact c:contacts){
            allMassages.addAll(c.getMassages());
        }
        if (allMassages.size()>0){
            for (Massage m:allMassages){
                m.getDetails();
                System.out.println("********************");
            }
        }else {
            System.out.println("You do not have message");
        }
        showInitialOptions();
    }

    private static void manageContacts() {
        System.out.println("Please select one:" +
                "\n\t1. Show all contacts"+
                "\n\t2. Add a new contact"+
                "\n\t3. Search for a contact"+
                "\n\t4. Delete a contact"+
                "\n\t5. Go Back");
        int choice=scanner.nextInt();

        switch (choice){
            case 1:
                showAllContact();
                break;
            case 2:
                addNewContact();
                break;
            case 3:
                searchForContact();
                break;
            case 4:
                deleteContact();
                break;
            default:
                showInitialOptions();
                break;
        }
    }

    private static void deleteContact() {
        System.out.println("Please enter the name :");
        String name =scanner.next();
        if (name.equals("")){
            System.out.println("Please enter the name");
            deleteContact();
        }else {
            boolean doesExist=false;
            for (Contact c:contacts){
                if (c.getName().equals(name)){
                    doesExist=true;
                    contacts.remove(c);
                }

            }
            if (!doesExist){
                System.out.println("There is no such contact");
            }
        }
        showInitialOptions();
    }

    private static void searchForContact() {
        System.out.println("Enter the contact name:");
        String name=scanner.next();
        if (name.equals("")){
            System.out.println("Please enter the name ");
            searchForContact();
        }else {
            boolean doesExist=false;
            for (Contact c:contacts) {
                if (c.getName().equals(name)){
                    doesExist=true;
                    c.getDetails();
                }

            }
            if (!doesExist){
                System.out.println("There is no such contact in your phone");
            }
        }
        showInitialOptions();
    }

    private static void addNewContact() {
        System.out.println("Adding a new contact..."+
                "\nPlease enter contact's name:");
        String name=scanner.next();
        System.out.println("Please enter contact's number :");
        String number=scanner.next();
        System.out.println("Please enter contact's Email :");
        String email=scanner.next();
        if (name.equals("") || number.equals("") || email.equals("")){
            System.out.println("Please enter all of the information");
            addNewContact();
        }else {
            boolean doesExist=false;
            for (Contact c:contacts){
                if (c.getName().equals(name)){
                    doesExist=true;
                }
            }
            if (doesExist){
                System.out.println("We have contact name "+name+" save on this device");
                addNewContact();
            }else {
                Contact contact=new Contact(name,number,email);
                contacts.add(contact);
                System.out.println("Name added successfully");
            }

        }
        showInitialOptions();

    }

    private static void showAllContact() {
        if (contacts.size()>0){
            for (Contact c:contacts) {
                c.getDetails();
                System.out.println("***********************");
            }
            showInitialOptions();
        }else {
            System.out.println("you do not have contact");
            showInitialOptions();
        }

    }

    
}
