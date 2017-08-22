package com.cboss.ksi.common.entity;

/**
 * Enum Type for KSI Objects.
 *
 * Created by ishafigullin on 28.07.2017.
 */
public enum ObjectType {
    PROBLEM(3), QUESTION(5), ANSWER(6);

    private int id;

    private ObjectType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static ObjectType valueOf(int id) {
        for(ObjectType type: ObjectType.values()){
            if(type.getId() == id){
                return type;
            }
        }
        return null;
    }
}
