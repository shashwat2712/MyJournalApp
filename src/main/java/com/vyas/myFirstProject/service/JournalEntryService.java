package com.vyas.myFirstProject.service;

import com.vyas.myFirstProject.entity.JournalEntry;
import com.vyas.myFirstProject.entity.User;
import com.vyas.myFirstProject.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {


    @Autowired
    private JournalEntryRepository journalEntryRepository;


    @Autowired
    UserService userService;


    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
        try{
            User user = userService.findByUsername(userName);
            journalEntry.setDate((LocalDateTime.now()));
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.saveUser(user);
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException(e);//this is to inform to the transactional manager that error has
            // occurred and rollback
        }
    }

    public List<JournalEntry> getAll(){

        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){

        return journalEntryRepository.findById(id);
    }
    @Transactional
    public boolean deleteById(ObjectId id, String userName){
        boolean removed = false;
        try {

            User user = userService.findByUsername(userName);
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if(removed){
                userService.saveUser(user);
                journalEntryRepository.deleteById(id);
            }
            return removed;
        }
        catch (Exception e){
            System.out.println(e);
            //we need to explicitly invoke the exception
            throw new RuntimeException("An error occurred while deleting the entry");
        }
    }


    public void saveEntry(JournalEntry journalEntry) {
        journalEntryRepository.save(journalEntry);
    }




}
