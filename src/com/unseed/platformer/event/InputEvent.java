package com.unseed.platformer.event;

import com.unseed.platformer.Engine;

import java.util.function.Function;

public class InputEvent extends TickEvent{
	private Engine engine;
	
	public InputEvent (int since, int duration, Function<Integer, Double[]> formula, Engine engine) {
		super(since, duration, formula);
		this.engine = engine;
	}
	
	@Override
	public void process (Double[] value) {
		engine.setInputAvailable(value[0] != 0);
	}
	
	@Override
	public void finish () {
		engine.setInputAvailable(true);
	}
}
