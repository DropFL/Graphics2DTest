package com.rshtiger.platformer.event;

import com.dropfl.effect.ScreenEffectIterator;
import com.rshtiger.platformer.Engine;

import java.util.ArrayList;

public class EventManager {
	
	private ArrayList<TickEvent> events;
	private ArrayList<TickEvent> active;
	private ArrayList<TickEvent> remove;
	
	public EventManager (Engine engine, ScreenEffectIterator effects) {
		
		events = new ArrayList<>();
		active = new ArrayList<>();
		remove = new ArrayList<>();
		
		// Hardcode from here!
		// addEvent(some_event);
	}
	
	public void update (int ticks) {
		while (!events.isEmpty() && events.get(0).getSince() <= ticks)
			active.add(events.remove(0));
		
		for (TickEvent event : active)
			if (event.update(ticks)) remove.add(event);
		
		active.removeAll(remove);
		
		for (TickEvent event : remove)
			event.finish();
		
		remove.clear();
	}
	
	private void addEvent (TickEvent event) {
		events.add(event);
	}
}
