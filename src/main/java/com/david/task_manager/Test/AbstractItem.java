package com.david.task_manager.Test;

public abstract class AbstractItem {
    protected String name;

    public String getName() {
        return name;
    }

    public abstract void use();
}
