package ru.cboss.config.mappers;

import org.apache.ibatis.annotations.Insert;
import ru.cboss.contest.modules.tasks.beans.Event;

/**
 *  myBatis Mapper for Event.
 *
 * Created by ishafigullin on 01.08.2017.
 */
public interface EventMapper {
    final String INSERT =
      "insert into ksi2016_event( n, fd, d_event, d_obj, obj_id, r_user_id, dsc) " +
      "values( ksi2016_id_seq.nextval, sysdate, #{eventType.typeId}, #{objectType.typeId}, #{objId}, #{userId}, #{dsc} )";

    @Insert(INSERT)
    // @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Event event);
}
