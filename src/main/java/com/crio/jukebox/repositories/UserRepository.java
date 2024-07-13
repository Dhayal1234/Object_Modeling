package com.crio.jukebox.repositories;

import com.crio.jukebox.entities.User;

import java.util.List;

public interface UserRepository {
    void addUser(User user);
    User getUser(int id);
    List<User> getAllUsers();
}
