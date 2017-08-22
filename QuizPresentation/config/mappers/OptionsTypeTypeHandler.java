package ru.cboss.config.mappers;

import org.apache.ibatis.type.EnumOrdinalTypeHandler;
import ru.cboss.contest.modules.tasks.beans.OptionsType;

/**
 * TypeHandler for OptionsType.
 *
 * Created by ishafigullin on 12.07.2017.
 */
public class OptionsTypeTypeHandler extends EnumOrdinalTypeHandler<OptionsType> {
    public OptionsTypeTypeHandler() {
        super(OptionsType.class);
    }
}

