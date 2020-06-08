package com.ss20.se2.monopoly.view.deed;

import com.ss20.se2.monopoly.models.Player;
import com.ss20.se2.monopoly.models.fields.deeds.Deed;

public interface DeedFragmentDelegate {
	public void performAcquiringDeed(Deed deed, Player player);
	public void cancelled();
}
