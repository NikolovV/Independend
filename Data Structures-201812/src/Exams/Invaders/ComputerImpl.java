package Exams.Invaders;

import java.util.*;

public class ComputerImpl implements Computer {

    private int energy;
    private final Set<Invader> invaders;
    private final TreeMap<Integer, LinkedList<Invader>> invaderByDistance;

    public ComputerImpl(int energy) {
        if (energy < 0) {
            throw new IllegalArgumentException();
        }
        this.energy = energy;
        this.invaders = new LinkedHashSet<>();
        this.invaderByDistance = new TreeMap<>();
    }

    @Override
    public int getEnergy() {
        return Math.max(this.energy, 0);
    }

    @Override
    public void addInvader(Invader invader) {
        this.invaders.add(invader);
        this.invaderByDistance.putIfAbsent(invader.getDistance(), new LinkedList<>());
        this.invaderByDistance.get(invader.getDistance()).add(invader);
    }

    @Override
    public void skip(int turns) {
        List<Invader> toRemove = new ArrayList<>();
        for (Invader invader : this.invaders) {
            int oldDistance = invader.getDistance();
            int newDistance = invader.getDistance() - turns;
            invader.setDistance(newDistance);
            this.invaderByDistance.get(oldDistance).remove(invader);
            if (newDistance <= 0) {
                this.energy -= invader.getDamage();
                toRemove.add(invader);
            } else {
                this.invaderByDistance.putIfAbsent(newDistance, new LinkedList<>());
                this.invaderByDistance.get(newDistance).add(invader);
            }
        }
        for (Invader invader : toRemove) {
            this.invaders.remove(invader);
        }
    }

    @Override
    public void destroyTargetsInRadius(int radius) {
        List<Integer> keysToRemove = new ArrayList<>();
        for (Map.Entry<Integer, LinkedList<Invader>> pair : this.invaderByDistance.entrySet()) {
            if (pair.getKey() <= radius) {
                keysToRemove.add(pair.getKey());
                for (Invader invader : pair.getValue()) {
                    this.invaders.remove(invader);
                }
            } else {
                break;
            }
        }

        for (Integer integer : keysToRemove) {
            this.invaderByDistance.remove(integer);
        }
    }

    @Override
    public void destroyHighestPriorityTargets(int n) {
        int removedCount = 0;
        for (Map.Entry<Integer, LinkedList<Invader>> pair : this.invaderByDistance.entrySet()) {
            pair.getValue().sort(Comparator.comparing(Invader::getDamage));
            for (int i = pair.getValue().size() - 1; i >= 0; i--) {
                this.invaders.remove(pair.getValue().remove(i));
                removedCount++;
                if (removedCount == n) {
                    return;
                }
            }
        }
    }

    @Override
    public Iterable<Invader> invaders() {
        return this.invaders;
    }
}
