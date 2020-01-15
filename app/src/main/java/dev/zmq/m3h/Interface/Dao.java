package dev.zmq.m3h.Interface;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import dev.zmq.m3h.Sign_Up.User;

@android.arch.persistence.room.Dao
public interface Dao {
    @Query("SELECT * FROM User")
    List<User> getAll();

    @Query("SELECT * FROM User where userId LIKE  :userId AND password LIKE :password")
    User findByUserLogin(String userId, String password);

    @Insert
    void insert(User user);

    @Delete
    void delete(User user);

    @Update
    void update(User user);
}
