package com.david.task_manager.Test;

public class Item extends AbstractItem {
    private int durability;

    @Override
    public void use() {
        System.out.println("Using " + name);
        durability--;
    }

    public int getDurability() {
        return durability;
    }
}
