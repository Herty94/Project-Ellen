package sk.tuke.kpi.oop.game.items;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sk.tuke.kpi.gamelib.ActorContainer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Backpack implements ActorContainer<Collectible> {

    private final String name;
    private final int capacity;
    private final List<Collectible> collectible;

    public Backpack(String name, int capacity){
        this.name= name;
        this.capacity = capacity;
        this.collectible = new ArrayList<>();
    }
    @Override
    public @NotNull List<Collectible> getContent() {
        return new ArrayList<>(collectible);
    }

    @Override
    public int getCapacity() {
        return capacity;
    }

    @Override
    public int getSize() {
        if(collectible==null)
            return 0;
        return collectible.size();
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
           throw new IllegalAccessError("Backpack is full");
    }

    @Override
    public void remove(@NotNull Collectible actor) {

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
            Collections.rotate(collectible,1);
    }

    @NotNull
    @Override
    public Iterator<Collectible> iterator() {
        return collectible.iterator();
    }
}
