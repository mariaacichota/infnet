package com.petfriends.shared.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public abstract class BaseCommand {

    @TargetAggregateIdentifier
    private final String aggregateId;

    protected BaseCommand(String aggregateId) {
        this.aggregateId = aggregateId;
    }

    public String getAggregateId() {
        return aggregateId;
    }
}
