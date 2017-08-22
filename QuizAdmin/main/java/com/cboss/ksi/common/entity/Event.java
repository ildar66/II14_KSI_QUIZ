package com.cboss.ksi.common.entity;

/**
 * Event VO.
 *
 * Created by ishafigullin on 31.07.2017.
 */
public class Event extends BasePeriodicityEntity<Long>{
    private EventType eventType;
    private ObjectType objectType;
    private Long userId;
    private Long objId;
    String dsc;

    public Event(Long id) {
        super(id);
    }

    public EventType getEventType() {
        return eventType;
    }
    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public ObjectType getObjectType() {
        return objectType;
    }
    public void setObjectType(ObjectType objectType) {
        this.objectType = objectType;
    }

    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getObjId() {
        return objId;
    }
    public void setObjId(Long objId) {
        this.objId = objId;
    }

    public String getDsc() {
        return dsc;
    }
    public void setDsc(String dsc) {
        this.dsc = dsc;
    }

    @Override
    public String toString() {
        return "Event{" +
                "eventType=" + eventType +
                ", objectType=" + objectType +
                ", objId=" + objId +
                ", dsc='" + dsc + '\'' +
                '}';
    }
}
