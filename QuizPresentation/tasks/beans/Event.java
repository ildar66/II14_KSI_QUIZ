package ru.cboss.contest.modules.tasks.beans;

import com.cbossgroup.ksi.data.*;

/**
 * Event VO.
 *
 * Created by ishafigullin on 01.08.2017.
 */
public class Event extends BasePeriodicityEntity<Long>{
    private EventTypeEnum eventType;
    private ObjectTypeEnum objectType;
    private Long userId;
    private Long objId;
    String dsc;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public EventTypeEnum getEventType() {
        return eventType;
    }

    public void setEventType(EventTypeEnum eventType) {
        this.eventType = eventType;
    }

    public String getDsc() {
        return dsc;
    }

    public void setDsc(String dsc) {
        this.dsc = dsc;
    }

    public Long getObjId() {
        return objId;
    }

    public void setObjId(Long objId) {
        this.objId = objId;
    }

    public ObjectTypeEnum getObjectType() {
        return objectType;
    }

    public void setObjectType(ObjectTypeEnum objectType) {
        this.objectType = objectType;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventType=" + eventType +
                ", objectType=" + objectType +
                ", userId=" + userId +
                ", objId=" + objId +
                ", dsc='" + dsc + '\'' +
                '}';
    }
}
