package com.david.task_manager.Test;

public class Main {
    public static void main(String[] args) {
        AbstractItem abstractItem = new Item();

        // Isso funciona (método de AbstractItem)
        abstractItem.getName();

        // Isso funciona (método sobrescrito em Item)
        abstractItem.use();

        // Isso NÃO funciona (método específico de Item)
        // abstractItem.getDurability(); // Erro de compilação

        // Mas você pode fazer um cast se tiver certeza do tipo
        if (abstractItem instanceof Item) {
            int durability = ((Item) abstractItem).getDurability();
            System.out.println("Durability: " + durability);
        }
    }
}
