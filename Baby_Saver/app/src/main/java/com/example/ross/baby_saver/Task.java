package com.example.ross.baby_saver;

/**
 * Created by Ross on 1/18/2017.
 */

public class Task {
    private String taskName;
    private int status;
    private int id;


    public Task(){
        this.taskName = null;
        this.status=0;

    }

    public Task(String taskName){
        this.taskName = taskName;
        this.status = status;
    }


    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setTaskName(String taskName){
        this.taskName = taskName;
    }

    public String getTaskName(){
        return this.taskName;
    }

    public void setStatus(int status){
        this.status = status;
    }

    public int getStatus(){
        return this.status;
    }
}
