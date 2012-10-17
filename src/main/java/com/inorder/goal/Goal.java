package com.inorder.goal;

public class Goal {
    private final Long id;
    private final String name;
    private final Long prioirity;

    public Goal(Long id, String name, Long prioirity) {
        this.id = id;
        this.name = name;
        this.prioirity = prioirity;
    }


    public Goal(String name, Long prioirity) {
        this.id = null;
        this.name = name;
        this.prioirity = prioirity;
    }

    public Goal(Long id, Goal goal) {
        this(id, goal.name, goal.prioirity);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getPrioirity() {
        return prioirity;
    };

    @Override
    public String toString() {
        return "Goal [id=" + id + ", name=" + name + ", prioirity=" + prioirity
                + "]";
    }
}
