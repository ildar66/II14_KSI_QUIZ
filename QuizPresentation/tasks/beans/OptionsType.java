package ru.cboss.contest.modules.tasks.beans;

/**
 * Type for Options.
 *
 * Created by ishafigullin on 10.07.2017.
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
