package com.ss20.se2.monopoly.pojo.models.deeds;

public class CommunityCard extends Deed {

    private String description;

    public CommunityCard(String name, String description) {
        super(name);

        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
