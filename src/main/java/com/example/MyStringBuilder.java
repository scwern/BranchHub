package com.example;

import java.util.Arrays;
import java.util.Stack;

public class MyStringBuilder {
    private char[] chars;
    private int size;
    private final Stack<Snapshot> history;

    public MyStringBuilder() {
        this.chars = new char[16];
        this.size = 0;
        this.history = new Stack<>();
        save();
    }

    public void append(String str) {
        if (str == null) {
            throw new IllegalArgumentException();
        }

        save();

        ensureCapacity(size + str.length());
        for (int i = 0; i < str.length(); i++) {
            chars[size++] = str.charAt(i);
        }
    }

    public void delete(int start, int end) {
        if (start < 0 || end > size || start > end) {
            throw new IllegalArgumentException();
        }

        save();

        int length = end - start;
        System.arraycopy(chars, end, chars, start, size - end);
        size -= length;
    }

    public void insert(int offset, String str) {
        if (str == null) {
            throw new IllegalArgumentException();
        }
        if (offset < 0 || offset > size) {
            throw new IllegalArgumentException();
        }

        save();

        ensureCapacity(size + str.length());

        System.arraycopy(chars, offset, chars, offset + str.length(), size - offset);

        for (int i = 0; i < str.length(); i++) {
            chars[offset + i] = str.charAt(i);
        }

        size += str.length();
    }

    public void undo() {
        if (!history.isEmpty()) {
            Snapshot snapshot = history.pop();
            this.chars = snapshot.chars;
            this.size = snapshot.size;
        }
    }

    public void save() {
        history.push(new Snapshot(chars.clone(), size));
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > chars.length) {
            int newCapacity = Math.max(chars.length * 2, minCapacity);
            chars = Arrays.copyOf(chars, newCapacity);
        }
    }

    @Override
    public String toString() {
        return new String(chars, 0, size);
    }

    private static class Snapshot {
        private final char[] chars;
        private final int size;

        public Snapshot(char[] chars, int size) {
            this.chars = chars;
            this.size = size;
        }
    }
}

