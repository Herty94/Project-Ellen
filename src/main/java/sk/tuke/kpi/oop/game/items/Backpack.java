package sk.tuke.kpi.oop.game.items;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.Actor;
import sk.tuke.kpi.gamelib.ActorContainer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Backpack implements ActorContainer<Collectible> {

    private String name;
    private int capacity;
    private List<Collectible> collectible;

    public Backpack(String name, int capacity){
        this.name= name;
        this.capacity = capacity;
        this.collectible = new ArrayList<>();
    }
    @Override
    public @NotNull List<Collectible> getContent() {
         List<Collectible> colList=collectible;
         return colList;
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public @NotNull String getName() {
        return name;
    }

    @Override
    public void add(@NotNull Collectible actor) {
        if(capacity>collectible.size())
        collectible.add(actor);
        else
           throw new IllegalAccessError(name+" is full");
    }

    @Override
    public void remove(@NotNull Collectible actor) {
        if(actor==null)
            return;
        collectible.remove(actor);
    }

    @Override
    public @Nullable Collectible peek() {
        if(!collectible.isEmpty())
            return collectible.get(collectible.size()-1);
        return null;
    }

    @Override
    public void shift() {
        Collections.swap(collectible,0,collectible.size()-1);
    }

    @NotNull
    @Override
    public Iterator<Collectible> iterator() {
        return collectible.iterator();
    }
}
