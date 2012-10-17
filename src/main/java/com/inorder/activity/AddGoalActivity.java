package com.inorder.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.inorder.goal.Goal;
import com.inorder.goal.GoalRegistry;

public class AddGoalActivity extends Activity {
    private GoalRegistry goalRegistry ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        goalRegistry = new GoalRegistry(this);
        setContentView(R.layout.activity_add_goal);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_add_goal, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void addGoal(View view) {
        EditText addGoalText = (EditText) findViewById(R.id.add_goal_text);
        String goalName = addGoalText.getText().toString();
        if ( goalName != null && !goalName.trim().equals("")) {
            goalRegistry.addGoal(new Goal(goalName, 0l));
        }
        this.finish();
    }

}
