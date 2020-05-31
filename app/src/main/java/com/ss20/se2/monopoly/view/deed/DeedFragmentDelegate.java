package com.ss20.se2.monopoly.view.deed;

import com.ss20.se2.monopoly.models.Player;
import com.ss20.se2.monopoly.models.fields.deeds.Street;

public interface DeedFragmentDelegate {
	public void performAcquiringDeed(Street street, Player player);
	public void cancelled();
}
