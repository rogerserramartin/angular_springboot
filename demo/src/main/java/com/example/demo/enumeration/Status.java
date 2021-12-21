package com.example.demo.enumeration;

// enum: special Java type used to define collections of constants
public enum Status {
    SERVER_UP("SERVER_UP"),
    SERVER_DOWN("SERVER_DOWN");
    private final String status;

    //private is redundant in enum constructors
    Status(String status) {
        this.status = status;
    }

    public String getStatus(){
        return this.status;
    }
}
