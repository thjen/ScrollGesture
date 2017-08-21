package com.example.qthjen.scrollgesture;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> list;
    ArrayAdapter adapter;
    int i;
    ListView lv;
    int Ymin = 200;
    GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.lv);
        list = new ArrayList<>();

        for ( i = 0; i < 5; i++) {
            list.add("Index: " + i);
        }
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        lv.setAdapter(adapter);
        gestureDetector = new GestureDetector(this, new MyGesture());
        lv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                gestureDetector.onTouchEvent(motionEvent);
                return true;
            }
        });

    }

    class MyGesture extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            /** e1 e2 là các vị trí cham vào và thả ra
             * distanceX = e1 - e2 theo chiều ngang
             * distanceY = e1 - e2 theo chiều dọc **/

            // scroll down
            if ( (e2.getY() - e1.getY()) > Ymin) {
                Toast.makeText(MainActivity.this, "Down", Toast.LENGTH_SHORT).show();
            } else if ( (e1.getY() - e2.getY()) > Ymin ) { // scroll up
                list.add("Index: " + i++);
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "Up", Toast.LENGTH_SHORT).show();
            }

            return super.onScroll(e1, e2, distanceX, distanceY);
        }
    }

}
