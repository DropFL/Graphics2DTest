package com.dropfl.effect;

import java.awt.*;
import java.awt.image.VolatileImage;
import java.util.Arrays;
import java.util.List;

public class ScreenEffectIterator extends ScreenEffect {
	
	private List<ScreenEffect> iterator;
	
	public ScreenEffectIterator (List<ScreenEffect> effects) {
		iterator = effects;
	}
	
	public ScreenEffectIterator (ScreenEffect... args) {
		this(Arrays.asList(args));
	}
	
	@Override
	public void apply (VolatileImage image, RenderingHints hints) {
		for (ScreenEffect elem : iterator)
			elem.apply(image, hints);
	}
}
