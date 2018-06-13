package com.unseed.platformer.event;

import com.unseed.platformer.Engine;

import java.util.function.Function;

public class GravityEvent extends TickEvent {
	
	private Engine engine;
	
	public GravityEvent (int since, int duration, Function<Integer, Double[]> formula, Engine engine) {
		super(since, duration, formula);
		this.engine = engine;
	}
	
	@Override
	public void process (Double[] values) {
		engine.setGravity(values[0]);
	}
}
