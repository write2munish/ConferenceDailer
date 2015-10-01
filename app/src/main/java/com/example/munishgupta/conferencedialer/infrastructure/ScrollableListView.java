package com.example.munishgupta.conferencedialer.infrastructure;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by munishgupta on 23/09/15.
 */
public class ScrollableListView extends ListView {
    public ScrollableListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public ScrollableListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollableListView(Context context) {
        super(context);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        final int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.i("VerticalScrollview", "onInterceptTouchEvent: DOWN super false");
                super.onTouchEvent(ev);
                break;

            case MotionEvent.ACTION_MOVE:
                return false; // redirect MotionEvents to ourself

            case MotionEvent.ACTION_CANCEL:
                Log.i("VerticalScrollview", "onInterceptTouchEvent: CANCEL super false");
                super.onTouchEvent(ev);
                break;

            case MotionEvent.ACTION_UP:
                Log.i("VerticalScrollview", "onInterceptTouchEvent: UP super false");
                return false;

            default:
                Log.i("VerticalScrollview", "onInterceptTouchEvent: " + action);
                break;
        }

        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        super.onTouchEvent(ev);
        Log.i("VerticalScrollview", "onTouchEvent. action: " + ev.getAction());
        return true;
    }
}
