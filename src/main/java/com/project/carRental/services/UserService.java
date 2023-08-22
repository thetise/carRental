package com.project.carRental.services;

import com.project.carRental.entities.User;
import com.project.carRental.repos.IUserRepositories;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    IUserRepositories iUserRepositories;

    public UserService(IUserRepositories iUserRepositories) {
        this.iUserRepositories = iUserRepositories;
    }

    public List<User> getAllUsers() {
        return iUserRepositories.findAll();
    }

    public User createOneUser(User newUser) {
        return iUserRepositories.save(newUser);
    }

    public User getOneUser(Long userId) {
        return iUserRepositories.findById(userId).orElse(null);
    }

    //BUARADA USERNAME İLE ÇAĞIRMAK İÇİN BU SERVİSİ YAZDIK.
    //public User getOneUserByName(User userName){
    //    return iUserRepositories.findByUserName(userName);
    //}
    ////////////////

    public User updateOneUser(Long userId, User updateUser) {
        Optional<User> user = iUserRepositories.findById(userId);
        if(user.isPresent()){
            User foundUser = user.get();

            if(updateUser.getUserName() != null){
                foundUser.setUserName(updateUser.getUserName());
            }else{
                foundUser.setUserName(user.get().getUserName());
            }

            if(updateUser.getPassword() != null){
                foundUser.setPassword(updateUser.getPassword());
            }else{
                foundUser.setPassword(user.get().getPassword());
            }

            iUserRepositories.save(foundUser);
            return foundUser;
        }
        else{
            return null;
        }
    }

    public void deleteOneUser(Long userId) {
        iUserRepositories.deleteById(userId);
    }
}