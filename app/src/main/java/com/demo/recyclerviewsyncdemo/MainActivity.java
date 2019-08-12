package com.demo.recyclerviewsyncdemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private int[] myImageList = new int[]{R.drawable.apple, R.drawable.mango, R.drawable.straw, R.drawable.pineapple, R.drawable.orange, R.drawable.blue, R.drawable.water};
    private String[] myImageNameList = new String[]{"Apple", "Mango", "Strawberry", "Pineapple", "Orange", "Blueberry", "Watermelon"};

    private int mTouchedRvTag;

    private FruitAdapter fruitAdapter;

    private AttributesAdapter attributesAdapter;

    private ArrayList<Fruit> fruits;
    private ArrayList<Attribute> attributes;

    private LinearLayout linearLayoutContainer;

    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if ((int) recyclerView.getTag() == mTouchedRvTag) {
                for (int noOfRecyclerView = 0; noOfRecyclerView < 2; noOfRecyclerView++) {
                    if (noOfRecyclerView != (int) recyclerView.getTag()) {
                        RecyclerView tempRecyclerView = linearLayoutContainer.findViewWithTag(noOfRecyclerView);
                        tempRecyclerView.scrollBy(dx, dy);
                    }
                }
            }
        }

        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }
    };

    private RecyclerView.OnItemTouchListener onItemTouchListener = new RecyclerView.OnItemTouchListener() {
        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            mTouchedRvTag = (int) rv.getTag();
            return false;
        }

        @Override
        public void onTouchEvent(@NonNull RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    };

    private RecyclerView recyclerViewFruits, recyclerViewFruitAttributes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        initData();

        fruitAdapter = new FruitAdapter(fruits);
        attributesAdapter = new AttributesAdapter(this, attributes);

        recyclerViewFruits.setTag(0);
        recyclerViewFruits.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewFruits.setAdapter(fruitAdapter);
        recyclerViewFruits.setHasFixedSize(true);
        recyclerViewFruits.setNestedScrollingEnabled(false);

        recyclerViewFruitAttributes.setTag(1);
        recyclerViewFruitAttributes.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewFruitAttributes.setAdapter(attributesAdapter);
        recyclerViewFruitAttributes.setHasFixedSize(true);
        recyclerViewFruitAttributes.setNestedScrollingEnabled(false);

        // Scroll and Touch listeners
        recyclerViewFruits.addOnScrollListener(onScrollListener);
        recyclerViewFruits.addOnItemTouchListener(onItemTouchListener);

        recyclerViewFruitAttributes.addOnScrollListener(onScrollListener);
        recyclerViewFruitAttributes.addOnItemTouchListener(onItemTouchListener);

    }

    private void initData() {
        fruits = getFruits();
        attributes = getAttributes();
    }

    private ArrayList<Fruit> getFruits() {

        ArrayList<Fruit> list = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            Fruit fruit = new Fruit();
            fruit.setName(myImageNameList[i]);
            fruit.setImage_drawable(myImageList[i]);
            list.add(fruit);
        }

        return list;
    }

    private ArrayList<Attribute> getAttributes() {
        ArrayList<KeyValue> keyValues = new ArrayList<>();
        keyValues.add(new KeyValue("Key1", "Value1"));
        keyValues.add(new KeyValue("Key2", "Value2"));
        keyValues.add(new KeyValue("Key3", "Value3"));
        keyValues.add(new KeyValue("Key4", "Value4"));
        keyValues.add(new KeyValue("Key5", "Value5"));
        keyValues.add(new KeyValue("Key6", "Value6"));
        keyValues.add(new KeyValue("Key7", "Value7"));

        ArrayList<Attribute> attributes = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            attributes.add(new Attribute(keyValues));
        }

        return attributes;
    }

    private void initViews() {
        linearLayoutContainer = findViewById(R.id.ll_container);
        recyclerViewFruits = findViewById(R.id.rv_fruits);
        recyclerViewFruitAttributes = findViewById(R.id.rv_fruit_attributes);
    }
}
