package com.github.simpleactions;

public class Simple {
    public static void main(String[] args) {
        System.out.println("Hello World!" + new Simple().getName());
    }

    public String getName() {
        return Simple.class.getSimpleName();
    }
}
