package dev.zmq.m3h.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import dev.zmq.m3h.Interface.Dao;
import dev.zmq.m3h.Sign_Up.User;

@Database(entities = {User.class}, version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract Dao dao();
}
