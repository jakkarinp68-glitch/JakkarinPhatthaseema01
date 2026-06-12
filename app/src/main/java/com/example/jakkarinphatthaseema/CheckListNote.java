package com.example.jakkarinphatthaseema;

import java.util.ArrayList;
import java.util.List;

public class CheckListNote extends Note {
    //attribute
    public List<String> item = new ArrayList<>();

    @Override
    public String getSummary() {
        if (item == null || item.isEmpty()) return "Empty checklist";
        return String.join(", ", item);
    }

    public void addItem(String item) {
        if (this.item == null) this.item = new ArrayList<>();
        this.item.add(item);
    }

    public void removeItem(String item) {
        if (this.item != null) this.item.remove(item);
    }

    public float getProgressComplete() {
        return 0;
    }
}
