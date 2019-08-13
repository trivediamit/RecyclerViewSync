package com.demo.recyclerviewsyncdemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

    private RecyclerView recyclerViewFruits, recyclerViewFruitAttributes;

    private final RecyclerView.OnScrollListener mLeftOSL = new SelfRemovingOnScrollListener() {
        @Override
        public void onScrolled(@NonNull final RecyclerView recyclerView, final int dx, final int dy) {
            super.onScrolled(recyclerView, dx, dy);
            recyclerViewFruitAttributes.scrollBy(dx, dy);
        }
    }, mRightOSL = new SelfRemovingOnScrollListener() {

        @Override
        public void onScrolled(@NonNull final RecyclerView recyclerView, final int dx, final int dy) {
            super.onScrolled(recyclerView, dx, dy);
            recyclerViewFruits.scrollBy(dx, dy);
        }
    };

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
        recyclerViewFruits.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            private int mLastY;

            @Override
            public boolean onInterceptTouchEvent(@NonNull final RecyclerView rv, @NonNull final
            MotionEvent e) {
                Log.d("debug", "LEFT: onInterceptTouchEvent");

                final Boolean ret = rv.getScrollState() != RecyclerView.SCROLL_STATE_IDLE;
                if (!ret) {
                    onTouchEvent(rv, e);
                }
                return Boolean.FALSE;
            }

            @Override
            public void onTouchEvent(@NonNull final RecyclerView rv, @NonNull final MotionEvent e) {
                Log.d("debug", "LEFT: onTouchEvent");

                final int action;
                if ((action = e.getAction()) == MotionEvent.ACTION_DOWN && recyclerViewFruitAttributes
                        .getScrollState() == RecyclerView.SCROLL_STATE_IDLE) {
                    mLastY = rv.getScrollY();
                    rv.addOnScrollListener(mLeftOSL);
                } else {
                    if (action == MotionEvent.ACTION_UP && rv.getScrollY() == mLastY) {
                        rv.removeOnScrollListener(mLeftOSL);
                    }
                }
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(final boolean disallowIntercept) {
                Log.d("debug", "LEFT: onRequestDisallowInterceptTouchEvent");
            }
        });

        recyclerViewFruitAttributes.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

            private int mLastY;

            @Override
            public boolean onInterceptTouchEvent(@NonNull final RecyclerView rv, @NonNull final
            MotionEvent e) {
                Log.d("debug", "RIGHT: onInterceptTouchEvent");

                final Boolean ret = rv.getScrollState() != RecyclerView.SCROLL_STATE_IDLE;
                if (!ret) {
                    onTouchEvent(rv, e);
                }
                return Boolean.FALSE;
            }

            @Override
            public void onTouchEvent(@NonNull final RecyclerView rv, @NonNull final MotionEvent e) {
                Log.d("debug", "RIGHT: onTouchEvent");

                final int action;
                if ((action = e.getAction()) == MotionEvent.ACTION_DOWN && recyclerViewFruits
                        .getScrollState
                                () == RecyclerView.SCROLL_STATE_IDLE) {
                    mLastY = rv.getScrollY();
                    rv.addOnScrollListener(mRightOSL);
                } else {
                    if (action == MotionEvent.ACTION_UP && rv.getScrollY() == mLastY) {
                        rv.removeOnScrollListener(mRightOSL);
                    }
                }
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(final boolean disallowIntercept) {
                Log.d("debug", "RIGHT: onRequestDisallowInterceptTouchEvent");
            }
        });
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

    public class SelfRemovingOnScrollListener extends RecyclerView.OnScrollListener {

        @Override
        public final void onScrollStateChanged(@NonNull final RecyclerView recyclerView, final int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                recyclerView.removeOnScrollListener(this);
            }
        }
    }
}
