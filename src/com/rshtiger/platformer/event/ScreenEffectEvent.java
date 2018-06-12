package com.rshtiger.platformer.event;

import com.dropfl.effect.ScreenEffect;
import com.dropfl.effect.ScreenEffectIterator;

import java.util.function.Function;

public class ScreenEffectEvent extends TickEvent {
	
	private ScreenEffect effect;
	private ScreenEffectIterator effects;
	
	public ScreenEffectEvent (int since, int duration, Function<Integer, Double[]> formula,
							  ScreenEffectIterator effects, ScreenEffect effect, boolean where) {
		super(since, duration, formula);
		this.effect = effect;
		this.effects = effects;
		
		effects.addScreenEffect(effect, where);
	}
	
	@Override
	public void process (Double[] value) {
		effect.updateProperties(value);
	}
	
	@Override
	public void finish () {
		effects.removeScreenEffect(effect);
	}
}
