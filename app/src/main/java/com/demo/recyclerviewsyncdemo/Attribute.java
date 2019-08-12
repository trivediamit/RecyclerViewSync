package com.demo.recyclerviewsyncdemo;

import java.util.ArrayList;

public class Attribute {

    private ArrayList<KeyValue> attributes;

    public Attribute(ArrayList<KeyValue> attributes) {
        this.attributes = attributes;
    }

    public ArrayList<KeyValue> getAttributes() {
        return attributes;
    }

    public void setAttributes(ArrayList<KeyValue> attributes) {
        this.attributes = attributes;
    }
}