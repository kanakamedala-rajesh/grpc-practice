package com.venkatasudha.entities;

public class Student {
    private final int _ID;
    private final String name;
    private final Group group;

    public Student(int _ID, String name, Group group) {
        this._ID = _ID;
        this.name = name;
        this.group = group;
    }

    public int getID() {
        return _ID;
    }

    public String getName() {
        return name;
    }

    public Group getGroup() {
        return group;
    }
}
