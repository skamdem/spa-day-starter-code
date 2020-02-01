package org.launchcode.spaday.data;

import org.launchcode.spaday.models.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kamdem
 */
public class UserData {
    // need a place to put users (Integer are the IdS)
    private static final Map<Integer, User> users = new HashMap<>();

    // get all users
    public static Collection<User> getAll(){
        return  users.values();
    }

    // get a single user
    public static User getById(int id){
        return users.get(id);
    }

    // add a user
    public static void add(User user){
        users.put(user.getId(), user);
    }

    // remove a user
    public static void removeById(int id){
        users.remove(id);
    }
}
