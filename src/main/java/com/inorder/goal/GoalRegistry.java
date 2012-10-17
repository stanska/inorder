package com.inorder.goal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GoalRegistry extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "inOrderDB";

    public static final String GOAL_TABLE_NAME = "goal";
    public static final String GOAL_ID = "id";
    public static final String GOAL_COLUMN_NAME = "name";
    public static final String GOAL_COLUMN_PROIRITY = "proirity";

    private static final String GOAL_TABLE_CREATE = "CREATE TABLE "
            + GOAL_TABLE_NAME + " ( "
            + GOAL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + GOAL_COLUMN_NAME + " TEXT, "
            + GOAL_COLUMN_PROIRITY + " INTEGER);";

    public GoalRegistry(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("createing db");
        db.execSQL(GOAL_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
    }

    public Goal addGoal(Goal goal) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues values = new ContentValues();
            values.put(GoalRegistry.GOAL_COLUMN_NAME, goal.getName());
            values.put(GoalRegistry.GOAL_COLUMN_PROIRITY, goal.getPrioirity());
            long id = db.insert(GoalRegistry.GOAL_TABLE_NAME, null, values);
            if (id == -1) {
                throw new SQLException("no primary key generated:" + id);
            }

            Goal addedGoal = new Goal(id, goal);
            return addedGoal;
        } finally {
            db.close();
        }
    }

    public List<Goal> getAllGoals() {
        SQLiteDatabase db = this.getWritableDatabase();
        List<Goal> result = new ArrayList<Goal>();
        try {
            db = this.getReadableDatabase();
            Cursor cursor = db.query(GoalRegistry.GOAL_TABLE_NAME, null, null, null, null, null, null);
            while (cursor.moveToNext()) {
              result.add(new Goal(cursor.getLong(0), cursor.getString(1), cursor.getLong(2)));
            }
        } finally {
            db.close();
        }
        return result;
    }

    public List<Map<String, String>> getGoalsDisplayMap() {
        List<Goal> goals = getAllGoals();
        List<Map<String, String>> res = new ArrayList<Map<String,String>>();
        for ( Goal goal : goals) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("name", goal.getName());
           // map.put("priority", goal.getPrioirity() + "");
            res.add(map);
//            HashMap<String, String> map = new HashMap<String, String>();
//            map.put("train", "101");
//            map.put("from", "6:30 AM");
//            map.put("to", "7:40 AM");
//            mylist.add(map);
        }
        return res;
    }
}