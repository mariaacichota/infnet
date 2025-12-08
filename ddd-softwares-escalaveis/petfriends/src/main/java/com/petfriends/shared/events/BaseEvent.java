package com.petfriends.shared.events;

import java.time.Instant;

public abstract class BaseEvent {

    private final String aggregateId;
    private final Instant occurredAt;

    protected BaseEvent(String aggregateId) {
        this.aggregateId = aggregateId;
        this.occurredAt = Instant.now();
    }

    public String getAggregateId() {
        return aggregateId;
    }

    public Instant getOccurredAt() {
        return occurredAt;
    }

    public String getType() {
        return this.getClass().getSimpleName();
    }
}