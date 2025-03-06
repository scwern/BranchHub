package com.example;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {
        MyStringBuilder sb = new MyStringBuilder();
        sb.append("Hello");
        System.out.println(sb);

        sb.insert(5, " World");
        System.out.println(sb);

        sb.delete(5, 11);
        System.out.println(sb);

        sb.undo();
        System.out.println(sb);
    }
}
