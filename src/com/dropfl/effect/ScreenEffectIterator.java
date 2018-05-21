package com.dropfl.effect;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

public class ScreenEffectIterator implements IScreenEffect {
	
	private List<IScreenEffect> iterator;
	
	public ScreenEffectIterator (List<IScreenEffect> effects) {
		iterator = effects;
	}
	
	public ScreenEffectIterator (IScreenEffect... args) {
		this(Arrays.asList(args));
	}
	
	@Override
	public void apply (BufferedImage image) {
		for (IScreenEffect elem : iterator)
			elem.apply(image);
	}
}
