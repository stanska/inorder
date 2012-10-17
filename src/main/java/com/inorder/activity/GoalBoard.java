package com.inorder.activity;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.inorder.dragndrop.DragListener;
import com.inorder.dragndrop.DragNDropListView;
import com.inorder.dragndrop.DropListener;
import com.inorder.dragndrop.RemoveListener;
import com.inorder.goal.GoalRegistry;

public class GoalBoard extends Activity {
    public final static String EXTRA_MESSAGE = "com.inorder.MESSAGE";
    private GoalRegistry goalRegistry;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_board);
        goalRegistry = new GoalRegistry(this);
        refreshGoalsListView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_goal_board, menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        return super.onMenuItemSelected(featureId, item);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case (R.id.menu_add): {
            Intent intent = new Intent(this, AddGoalActivity.class);
            startActivity(intent);
        }
        }
        return super.onOptionsItemSelected(item);
    }

    private void refreshGoalsListView() {
        List<Map<String, String>> goalsMap = goalRegistry.getGoalsDisplayMap();

        SimpleAdapter adapter = new SimpleAdapter(this, goalsMap,
                R.layout.activity_goal_board,
                new String[] { "name", "priority" }, new int[] { R.id.name /*
                                                                            * ,
                                                                            * R
                                                                            * .id
                                                                            * .
                                                                            * ready
                                                                            * ,
                                                                            * /
                                                                            * *R
                                                                            * .
                                                                            * id
                                                                            * .
                                                                            * TO_CELL
                                                                            */});
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);
        ((DragNDropListView) listView).setDropListener(mDropListener);
        ((DragNDropListView) listView).setRemoveListener(mRemoveListener);
        ((DragNDropListView) listView).setDragListener(mDragListener);
    }

    private final DropListener mDropListener = new DropListener() {
        public void onDrop(int from, int to) {
            // ListAdapter adapter = getListAdapter();
            // if (adapter instanceof DragNDropAdapter) {
            // ((DragNDropAdapter)adapter).onDrop(from, to);
            // getListView().invalidateViews();
            // }
        }
    };

    private final RemoveListener mRemoveListener = new RemoveListener() {
        public void onRemove(int which) {
            // ListAdapter adapter = getListAdapter();
            // if (adapter instanceof DragNDropAdapter) {
            // ((DragNDropAdapter)adapter).onRemove(which);
            // getListView().invalidateViews();
            // }
        }
    };

    private final DragListener mDragListener = new DragListener() {

        int backgroundColor = 0xe0103010;
        int defaultBackgroundColor;

        public void onDrag(int x, int y, ListView listView) {
            // TODO Auto-generated method stub
        }

        public void onStartDrag(View itemView) {
            itemView.setVisibility(View.INVISIBLE);
            defaultBackgroundColor = itemView.getDrawingCacheBackgroundColor();
            itemView.setBackgroundColor(backgroundColor);
            TextView iv = (TextView) itemView.findViewById(R.id.name);
            if (iv != null)
                iv.setVisibility(View.INVISIBLE);
        }

        public void onStopDrag(View itemView) {
            itemView.setVisibility(View.VISIBLE);
            itemView.setBackgroundColor(defaultBackgroundColor);
            TextView iv = (TextView) itemView.findViewById(R.id.name);
            if (iv != null)
                iv.setVisibility(View.VISIBLE);
        }

    };

    @Override
    protected void onStart() {
        super.onStart();
        refreshGoalsListView();
    }
}
