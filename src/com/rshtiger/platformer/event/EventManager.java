package com.rshtiger.platformer.event;

import com.dropfl.effect.ImageOverlayEffect;
import com.dropfl.effect.ScaleEffect;
import com.dropfl.effect.ScreenEffectIterator;
import com.dropfl.effect.TextOverlayEffect;
import com.rshtiger.platformer.Engine;
import com.rshtiger.platformer.entity.Block;
import com.rshtiger.platformer.entity.Ghost;
import res.FontResource;
import res.ImageResource;

import java.awt.*;
import java.util.ArrayList;

public class EventManager {
	
	private ArrayList<TickEvent> events;
	private ArrayList<TickEvent> active;
	private ArrayList<TickEvent> remove;
	
	public EventManager (Engine engine, ScreenEffectIterator effects) {
		
		final Ghost ghost = new Ghost(300, 600, 32, 32);
		
		events = new ArrayList<>();
		active = new ArrayList<>();
		remove = new ArrayList<>();
		
		// Block initialize
		addEvent(new BlockEvent(0, Integer.MAX_VALUE,
				(Integer integer) -> {return null;}, engine.getBlocks(), new Block(-1, 0, 1, 719)));
		addEvent(new BlockEvent(0, Integer.MAX_VALUE,
				(Integer integer) -> {return null;}, engine.getBlocks(), new Block(1280, 0, 1, 719)));
		addEvent(new BlockEvent(0, Integer.MAX_VALUE,
				(Integer integer) -> {return null;}, engine.getBlocks(), new Block(0, 720, 1280, 100)));
		addEvent(new BlockEvent(0, Integer.MAX_VALUE,
				(Integer integer) -> {return null;}, engine.getBlocks(), new Block(800, 680, 200, 30)));
		addEvent(new BlockEvent(0, Integer.MAX_VALUE,
				(Integer integer) -> {return null;}, engine.getBlocks(), new Block(300, 680, 200, 30)));
		addEvent(new BlockEvent(0, Integer.MAX_VALUE,
				(Integer integer) -> {return null;}, engine.getBlocks(), new Block(600, 640, 200, 30)));
		addEvent(new BlockEvent(0, Integer.MAX_VALUE,
				(Integer integer) -> {return null;}, engine.getBlocks(), new Block(0, 600, 100, 50)));
		
		
		addEvent(new ScreenEffectEvent(120, 30, (Integer integer) -> {
			Double[] res = {0., 0., 0., (30 - integer) / 30.};
			return res;
		}, effects,
				new ImageOverlayEffect(0, 0, 0, ImageResource.HIT.getImageIcon().getImage(), 1), ScreenEffectIterator.AT_LAST));

		// BEHIND...
		addEvent(new SpeedEvent(508, 54, (Integer integer) -> {
			Double[] res = {0.1};
			return res;
		}, engine));
		addEvent(new ScreenEffectEvent(499, 54, (Integer integer) -> {
			Double[] res = {0., 720., (double)((54 - integer) * 128 / 54)};
			return res;
		}, effects,
				new TextOverlayEffect(0, 0, "BEHIND",
										FontResource.BLACK_HAN_SANS.getFont(Font.PLAIN, 108), Color.BLACK),
				ScreenEffectIterator.AT_FIRST));
	}
	
	public void update (int ticks) {
		while (!events.isEmpty() && events.get(0).getSince() <= ticks) {
			TickEvent event = events.remove(0);
			active.add(event);
			event.start();
		}
		
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
