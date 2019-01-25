package me.calebjones.spacelaunchnow.spacestation.detail.adapter;

import me.calebjones.spacelaunchnow.data.models.main.spacestation.Expedition;

public class ExpeditionItem implements ListItem {

    private Expedition expedition;

    public ExpeditionItem(Expedition expedition){
        this.expedition = expedition;
    }

    public Expedition getExpedition() {
        return expedition;
    }

    public void setExpedition(Expedition expedition) {
        this.expedition = expedition;
    }

    @Override
    public int getListItemType() {
        return ListItem.TYPE_PAST_EXPEDITION;
    }
}
