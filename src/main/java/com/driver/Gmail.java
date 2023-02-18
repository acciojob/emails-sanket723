package com.driver;

import java.util.ArrayList;
import java.util.Date;

public class Gmail extends Email {

    public ArrayList<Mail> mail = new ArrayList<>();  // i create
    public ArrayList<Mail> trash = new ArrayList<>();  // i create
    int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    public Gmail(String emailId, int inboxCapacity) {
         super(emailId);
         this.inboxCapacity = inboxCapacity;

    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.

        if(getInboxSize()==getInboxCapacity()){
           String s = findOldestMessage();
           deleteMail(s);
        }
        Mail m = new Mail(date,sender,message);
        mail.add(m);

    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing

        for(int i=0; i<mail.size(); i++){
            Mail m = mail.get(i);
            if(m.message.equals(message)){
                trash.add(m);
                mail.remove(i);
            }
        }

    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
        if(mail.size()==0) {
            return null;
        }

        return mail.get(mail.size()-1).message;

    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        int index=0;
        while(mail.get(index)==null){
            index++;
        }
        return mail.get(index).message;

    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        int count=0;
        for(int i=0; i<mail.size(); i++){
            Mail m = mail.get(i);
            Date date = m.date;
            if(date.after(start) && date.before(end)) {
                count++;
            }
        }

        return count;

    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return mail.size();
    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return trash.size();

    }

    public void emptyTrash(){
        // clear all mails in the trash
        trash.clear();

    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return this.inboxCapacity;
    }
}
