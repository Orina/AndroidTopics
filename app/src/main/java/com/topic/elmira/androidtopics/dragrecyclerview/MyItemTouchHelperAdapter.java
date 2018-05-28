package com.topic.elmira.androidtopics.dragrecyclerview;

/**
 * Created by Elmira on 3/13/2018.
 */

public interface MyItemTouchHelperAdapter {

    boolean onItemMove(int fromPos, int toPos);

    void onItemDismiss(int pos);
}
