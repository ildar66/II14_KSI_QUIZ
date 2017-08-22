package com.cboss.ksi.common.entity;

/**
 * Enum Type for Options.
 *
 * Created by ishafigullin on 06.07.2017.
 */
public enum OptionsType {
    NONE(0), UNIQUE(1), MULTIPLE(2);

    private int id;

    private OptionsType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static OptionsType valueOf(int id) {
        for(OptionsType type: OptionsType.values()){
            if(type.getId() == id){
                return type;
            }
        }
        return null;
    }
}
