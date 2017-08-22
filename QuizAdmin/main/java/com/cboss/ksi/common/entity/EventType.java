package com.cboss.ksi.common.entity;

/**
 * Enum Type for KSI Event.
 *
 * Created by ishafigullin on 28.07.2017.
 */
public enum EventType {
    OPEN_PROBLEM(6), SOLVE_PROBLEM(7), REOPEN_PROBLEM(15);

    private int id;

    private EventType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static EventType valueOf(int id) {
        for(EventType type: EventType.values()){
            if(type.getId() == id){
                return type;
            }
        }
        return null;
    }
}
