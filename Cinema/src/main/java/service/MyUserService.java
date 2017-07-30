package service;

import domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;


public class MyUserService {

    private Map<Integer, User> registeredUsers = new HashMap<>();


    @Nullable
    public User getUserByEmail(@Nonnull String email) {
        for (Map.Entry<Integer, User> entry : registeredUsers.entrySet()) {
            if (entry.getValue().getEmail() == email) {
                return entry.getValue();
            }
        }
        return null;
    }



    /**
     * Save user - new or correction of existing(in case when id is not null)
     * @param user
     * @param id
     */
    public void save(@Nonnull User user, Integer id) {
        if (null == id) {
            id = registeredUsers.size() + 1;
        }
        registeredUsers.put(id, user);
    }

    /**
     * remove user from collection
     * @param id
     */

    int getIdByValue(User user){
        int result = -1;
        for (Map.Entry<Integer, User> entry : registeredUsers.entrySet()) {
            if (entry.getValue().equals(user)) {
                result = entry.getKey();
            }
        }
            return result;
    }

    public void remove(@Nonnull User user) {
        int id = getIdByValue(user);

        registeredUsers.remove(id);
    }

    public User getById(@Nonnull Integer id) {
        return registeredUsers.get(id);
    }

    /**
     * Return full list of users
     * @return
     */
    @Nonnull
    public Map<Integer, User> getAll() {
        return registeredUsers;
    }
}
