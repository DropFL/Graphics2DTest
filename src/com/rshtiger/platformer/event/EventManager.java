package com.rshtiger.platformer.event;

import com.dropfl.effect.ImageOverlayEffect;
import com.dropfl.effect.ScaleEffect;
import com.dropfl.effect.ScreenEffectIterator;
import com.dropfl.effect.TextOverlayEffect;
import com.rshtiger.platformer.Engine;
import com.rshtiger.platformer.entity.*;
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
		int startTerm = 0, bit = 56, basetime = 0;
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
		
		//term == 56s
		addEvent(new EntityEvent(112 + bit * startTerm++, 300, new Formula() {
			@Override
			public Double[] apply(Integer integer) {
				if(integer == 0)
					initialize(toPlayerset(10.0, 600.0, engine));

				Double[] vec = (Double[])getInit();
				Double[] res = {10.0 + vec[0] * 8 * integer, 600.0 + vec[1] * 8 * integer, vec[2]};
				return res;
			}
		}, engine.getEntities(), new Bullet(-100, -100, 50, 100)));
		addEvent(new EntityEvent(112 + bit * startTerm++, 300, new Formula() {
			@Override
			public Double[] apply(Integer integer) {
				if(integer == 0)
					initialize(toPlayerset(1300.0, 500.0, engine));

				Double[] vec = (Double[])getInit();
				Double[] res = {1300.0 + vec[0] * 8 * integer, 500.0 + vec[1] * 8 * integer, vec[2]};
				return res;
			}
		}, engine.getEntities(), new Bullet(-100, -100, 50, 100)));
		addEvent(new EntityEvent(112 + bit * startTerm++, 300, new Formula() {
			@Override
			public Double[] apply(Integer integer) {
				if(integer == 0)
					initialize(toPlayerset(600.0, 00.0, engine));

				Double[] vec = (Double[])getInit();
				Double[] res = {600.0 + vec[0] * 8 * integer, 0.0 + vec[1] * 8 * integer, vec[2]};
				return res;
			}
		}, engine.getEntities(), new Bullet(-100, -100, 50, 100)));
		addEvent(new EntityEvent(112 + bit * startTerm++, 300, new Formula() {
			@Override
			public Double[] apply(Integer integer) {
				if(integer == 0)
					initialize(toPlayerset(700.0, 820.0, engine));

				Double[] vec = (Double[])getInit();
				Double[] res = {700.0 + vec[0] * 8 * integer, 800.0 + vec[1] * 8 * integer, vec[2]};
				return res;
			}
		}, engine.getEntities(), new Bullet(-100, -100, 50, 100)));


		bulletToplayer(1300.0, 0.0, engine, 112 + bit * startTerm++);
		bulletToplayer(0.0, 0.0, engine, 112 + bit * startTerm++);
		bulletToplayer(1300.0, 800.0, engine, 112 + bit * startTerm++);
		bulletToplayer(0.0, 800.0, engine, 112 + bit * startTerm++);

		addEvent(new EntityEvent(112 + bit * startTerm++, 300, new Formula() {
			@Override
			public Double[] apply(Integer integer) {
				if(integer == 0)
					initialize(toPlayerset(10.0, 600.0, engine));

				Double[] vec = (Double[])getInit();
				Double[] res = {10.0 + vec[0] * 8 * integer, 600.0 + vec[1] * 8 * integer, vec[2]};
				return res;
			}
		}, engine.getEntities(), new Bullet(-100, -100, 50, 100)));
		addEvent(new EntityEvent(112 + bit * startTerm++, 300, new Formula() {
			@Override
			public Double[] apply(Integer integer) {
				if(integer == 0)
					initialize(toPlayerset(1300.0, 500.0, engine));

				Double[] vec = (Double[])getInit();
				Double[] res = {1300.0 + vec[0] * 8 * integer, 500.0 + vec[1] * 8 * integer, vec[2]};
				return res;
			}
		}, engine.getEntities(), new Bullet(-100, -100, 50, 100)));
		addEvent(new EntityEvent(112 + bit * startTerm++, 300, new Formula() {
			@Override
			public Double[] apply(Integer integer) {
				if(integer == 0)
					initialize(toPlayerset(600.0, 00.0, engine));

				Double[] vec = (Double[])getInit();
				Double[] res = {600.0 + vec[0] * 8 * integer, 0.0 + vec[1] * 8 * integer, vec[2]};
				return res;
			}
		}, engine.getEntities(), new Bullet(-100, -100, 50, 100)));
		addEvent(new EntityEvent(112 + bit * startTerm++, 300, new Formula() {
			@Override
			public Double[] apply(Integer integer) {
				if(integer == 0)
					initialize(toPlayerset(700.0, 820.0, engine));

				Double[] vec = (Double[])getInit();
				Double[] res = {700.0 + vec[0] * 8 * integer, 800.0 + vec[1] * 8 * integer, vec[2]};
				return res;
			}
		}, engine.getEntities(), new Bullet(-100, -100, 50, 100)));


		bulletToplayer(1300.0, 0.0, engine, 112 + bit * startTerm++);
		bulletToplayer(0.0, 0.0, engine, 112 + bit * startTerm++);
		bulletToplayer(1300.0, 800.0, engine, 112 + bit * startTerm++);
		bulletToplayer(0.0, 800.0, engine, 112 + bit * startTerm++);


		addEvent(new EntityEvent(112 + bit * startTerm++, 300, new Formula() {
			@Override
			public Double[] apply(Integer integer) {
				if(integer == 0)
					initialize(toPlayerset(640.0, 360.0, engine));

				Double[] vec = (Double[])getInit();
				Double[] res = {640.0 + vec[0] * 8 * integer, 360.0 + vec[1] * 8 * integer, vec[2]};
				return res;
			}
		}, engine.getEntities(), new Bullet(-100, -100, 50, 100)));
		addEvent(new EntityEvent(112 + bit * startTerm++, 300, new Formula() {
			@Override
			public Double[] apply(Integer integer) {
				if(integer == 0)
					initialize(toPlayerset(640.0, 360.0, engine));

				Double[] vec = (Double[])getInit();
				Double[] res = {640.0 + vec[0] * 8 * integer, 360.0 + vec[1] * 8 * integer, vec[2]};
				return res;
			}
		}, engine.getEntities(), new Bullet(-100, -100, 50, 100)));
		addEvent(new EntityEvent(112 + bit * startTerm++, 300, new Formula() {
			@Override
			public Double[] apply(Integer integer) {
				if(integer == 0)
					initialize(toPlayerset(640.0, 360.0, engine));

				Double[] vec = (Double[])getInit();
				Double[] res = {640.0 + vec[0] * 8 * integer, 360.0 + vec[1] * 8 * integer, vec[2]};
				return res;
			}
		}, engine.getEntities(), new Bullet(-100, -100, 50, 100)));
		addEvent(new EntityEvent(112 + bit * startTerm++, 300, new Formula() {
			@Override
			public Double[] apply(Integer integer) {
				if(integer == 0)
					initialize(toPlayerset(640.0, 360.0, engine));

				Double[] vec = (Double[])getInit();
				Double[] res = {640.0 + vec[0] * 8 * integer, 360.0 + vec[1] * 8 * integer, vec[2]};
				return res;
			}
		}, engine.getEntities(), new Bullet(-100, -100, 50, 100)));

		for(int i = 0; i < 11; i++){
			bulletToplayer(1000.0 * Math.random(), 700.0 * Math.random(), engine, 112 + bit * startTerm++);
		}

		bit = 58;
		startTerm = 0;
		basetime = 1904;
		addEvent(new EntityEvent(basetime + bit * startTerm, 100, (Integer integer) -> {
			Double[] res = {10.0 * integer, 600.0 , -90.0};
			return res;
		}, engine.getEntities(), new Bullet(-100, -100, 50, 100)));
		addEvent(new EntityEvent(basetime + bit * startTerm + 10, 100, (Integer integer) -> {
			Double[] res = {1300 -10.0 * integer, 500.0 , 90.0};
			return res;
		}, engine.getEntities(), new Bullet(100, -100, 50, 100)));
		addEvent(new EntityEvent(basetime + bit * startTerm + 13, 100, (Integer integer) -> {
			Double[] res = {600.0, 10.0 *integer , 0.0};
			return res;
		}, engine.getEntities(), new Bullet(100, -100, 50, 100)));
		addEvent(new EntityEvent(basetime + bit * startTerm + 18, 100, (Integer integer) -> {
			Double[] res = {700.0, 820 - 10.0 *integer , 180.0};
			return res;
		}, engine.getEntities(), new Bullet(100, -100, 50, 100)));
		addEvent(new EntityEvent(basetime + bit * startTerm++ + 28, 100, (Integer integer) -> {
			Double[] res = {10.0 * integer, 600.0 , -90.0};
			return res;
		}, engine.getEntities(), new Bullet(-100, -100, 50, 100)));

		addEvent(new EntityEvent(basetime + bit * startTerm, 100, (Integer integer) -> {
			Double[] res = {10.0 * integer, 300.0 , -90.0};
			return res;
		}, engine.getEntities(), new Bullet(-100, -100, 50, 100)));
		addEvent(new EntityEvent(basetime + bit * startTerm + 10, 100, (Integer integer) -> {
			Double[] res = {1300 -10.0 * integer, 800.0 , 90.0};
			return res;
		}, engine.getEntities(), new Bullet(100, -100, 50, 100)));
		addEvent(new EntityEvent(basetime + bit * startTerm + 13, 100, (Integer integer) -> {
			Double[] res = {800.0, 10.0 *integer , 0.0};
			return res;
		}, engine.getEntities(), new Bullet(100, -100, 50, 100)));
		addEvent(new EntityEvent(basetime + bit * startTerm + 18, 100, (Integer integer) -> {
			Double[] res = {900.0, 820 - 10.0 *integer , 180.0};
			return res;
		}, engine.getEntities(), new Bullet(100, -100, 50, 100)));
		addEvent(new EntityEvent(basetime + bit * startTerm++ + 28, 100, (Integer integer) -> {
			Double[] res = {10.0 * integer, 800.0 , -90.0};
			return res;
		}, engine.getEntities(), new Bullet(-100, -100, 50, 100)));

		addEvent(new EntityEvent(basetime + bit * startTerm, 100, (Integer integer) -> {
			Double[] res = {10.0 * integer, 300.0 + 5.0 * integer , getRadian(10.0, 5.0)};
			return res;
		}, engine.getEntities(), new Bullet(-100, -100, 50, 100)));
		addEvent(new EntityEvent(basetime + bit * startTerm + 10, 100, (Integer integer) -> {
			Double[] res = {1300 -10.0 * integer, 800.0 - 7.0 * integer , getRadian(-10.0, -7.0)};
			return res;
		}, engine.getEntities(), new Bullet(100, -100, 50, 100)));
		addEvent(new EntityEvent(basetime + bit * startTerm + 13, 100, (Integer integer) -> {
			Double[] res = {800.0 - 5.0 * integer, 10.0 *integer , getRadian(-5.0, 10.0)};
			return res;
		}, engine.getEntities(), new Bullet(100, -100, 50, 100)));
		addEvent(new EntityEvent(basetime + bit * startTerm + 18, 100, (Integer integer) -> {
			Double[] res = {900.0 - 2.0 * integer, 820 - 10.0 *integer , getRadian(-2.0, -10.0)};
			return res;
		}, engine.getEntities(), new Bullet(100, -100, 50, 100)));
		addEvent(new EntityEvent(basetime + bit * startTerm++ + 28, 100, (Integer integer) -> {
			Double[] res = {10.0 * integer, 800.0 , 0.0};
			return res;
		}, engine.getEntities(), new Bullet(-100, -100, 50, 100)));

		for(int i = 0; i < 8; i++) {
			final int final_i = i;
			addEvent(new EntityEvent(basetime + bit * startTerm, 300, (Integer integer) -> {
				Double[] res = {10.0 * integer, 50.0 + 100 * final_i , -90.0};
				return res;
			}, engine.getEntities(), new Bullet(-100, -100, 50, 100)));
		}

		for(int i = 0; i < 12; i++){
			final int final_i = i;
			addEvent(new EntityEvent(basetime + bit * startTerm + 9, 300, (Integer integer) -> {
				Double[] res = {100.0 * final_i, 10.0 *integer , 0.0};
				return res;
			}, engine.getEntities(), new Bullet(100, -100, 50, 100)));
		}

		for(int i = 0; i < 8; i++){
			final int final_i = i;
			addEvent(new EntityEvent(basetime + bit * startTerm + 18, 300, (Integer integer) -> {
				Double[] res = {1300 - 10.0 * integer, 100.0 * final_i , 90.0};
				return res;
			}, engine.getEntities(), new Bullet(100, -100, 50, 100)));
		}

		for(int i = 0; i < 12; i++){
			final int final_i = i;
			addEvent(new EntityEvent(basetime + bit * startTerm + 27, 300, (Integer integer) -> {
				Double[] res = {50 + 100.0 * final_i, 800 - 10.0 *integer , 180.0};
				return res;
			}, engine.getEntities(), new Bullet(100, -100, 50, 100)));
		}

		addEvent(new EntityEvent(basetime + bit * ++startTerm, 100, (Integer integer) -> {
			Double[] res = {10.0 * integer, 600.0 , -90.0};
			return res;
		}, engine.getEntities(), new Bullet(-100, -100, 50, 100)));
		addEvent(new EntityEvent(basetime + bit * startTerm + 10, 100, (Integer integer) -> {
			Double[] res = {1300 -10.0 * integer, 500.0 , 90.0};
			return res;
		}, engine.getEntities(), new Bullet(100, -100, 50, 100)));
		addEvent(new EntityEvent(basetime + bit * startTerm + 13, 100, (Integer integer) -> {
			Double[] res = {600.0, 10.0 *integer , 0.0};
			return res;
		}, engine.getEntities(), new Bullet(100, -100, 50, 100)));
		addEvent(new EntityEvent(basetime + bit * startTerm + 18, 100, (Integer integer) -> {
			Double[] res = {700.0, 820 - 10.0 *integer , 180.0};
			return res;
		}, engine.getEntities(), new Bullet(100, -100, 50, 100)));
		addEvent(new EntityEvent(basetime + bit * startTerm++ + 28, 100, (Integer integer) -> {
			Double[] res = {0.0, 600.0 , -90.0};
			return res;
		}, engine.getEntities(), new Laser(-100, -100, 150, 1300)));

		addEvent(new EntityEvent(basetime + bit * startTerm, 100, (Integer integer) -> {
			Double[] res = {10.0 * integer, 300.0 , -90.0};
			return res;
		}, engine.getEntities(), new Bullet(-100, -100, 50, 100)));
		addEvent(new EntityEvent(basetime + bit * startTerm + 10, 100, (Integer integer) -> {
			Double[] res = {1300 -10.0 * integer, 800.0 , 90.0};
			return res;
		}, engine.getEntities(), new Bullet(100, -100, 50, 100)));
		addEvent(new EntityEvent(basetime + bit * startTerm + 13, 100, (Integer integer) -> {
			Double[] res = {800.0, 10.0 *integer , 0.0};
			return res;
		}, engine.getEntities(), new Bullet(100, -100, 50, 100)));
		addEvent(new EntityEvent(basetime + bit * startTerm + 18, 100, (Integer integer) -> {
			Double[] res = {900.0, 820 - 10.0 *integer , 180.0};
			return res;
		}, engine.getEntities(), new Bullet(100, -100, 50, 100)));
		addEvent(new EntityEvent(basetime + bit * startTerm++ + 28, 100, (Integer integer) -> {
			Double[] res = {0.0, 200.0 , -90.0};
			return res;
		}, engine.getEntities(), new Laser(-100, -100, 150, 1300)));

		addEvent(new EntityEvent(basetime + bit * startTerm, 100, (Integer integer) -> {
			Double[] res = {10.0 * integer, 300.0 + 5.0 * integer , getRadian(10.0, 5.0)};
			return res;
		}, engine.getEntities(), new Bullet(-100, -100, 50, 100)));
		addEvent(new EntityEvent(basetime + bit * startTerm + 10, 100, (Integer integer) -> {
			Double[] res = {1300 -10.0 * integer, 800.0 - 7.0 * integer , getRadian(-10.0, -7.0)};
			return res;
		}, engine.getEntities(), new Bullet(100, -100, 50, 100)));
		addEvent(new EntityEvent(basetime + bit * startTerm + 13, 100, (Integer integer) -> {
			Double[] res = {800.0 - 5.0 * integer, 10.0 *integer , getRadian(-5.0, 10.0)};
			return res;
		}, engine.getEntities(), new Bullet(100, -100, 50, 100)));
		addEvent(new EntityEvent(basetime + bit * startTerm++ + 18, 100, (Integer integer) -> {
			Double[] res = {900.0 - 2.0 * integer, 820 - 10.0 *integer , getRadian(-2.0, -10.0)};
			return res;
		}, engine.getEntities(), new Bullet(100, -100, 50, 100)));
		addEvent(new EntityEvent(basetime + bit * startTerm++ + 28, 100, (Integer integer) -> {
			Double[] res = {0.0, 400.0 , -90.0};
			return res;
		}, engine.getEntities(), new Laser(-100, -100, 150, 1300)));

		addEvent(new EntityEvent(basetime + bit * startTerm, 100, (Integer integer) -> {
			Double[] res = {100.0, 0.0 , 0.0};
			return res;
		}, engine.getEntities(), new Laser(-100, -100, 150, 1300)));
		addEvent(new EntityEvent(basetime + bit * startTerm + 9, 100, (Integer integer) -> {
			Double[] res = {1200.0, 0.0 , 0.0};
			return res;
		}, engine.getEntities(), new Laser(-100, -100, 150, 1300)));
		addEvent(new EntityEvent(basetime + bit * startTerm + 18, 100, (Integer integer) -> {
			Double[] res = {0.0, 600.0 , -90.0};
			return res;
		}, engine.getEntities(), new Laser(-100, -100, 150, 1300)));
		addEvent(new EntityEvent(basetime + bit * startTerm + 27, 100, (Integer integer) -> {
			Double[] res = {0.0, 300.0 , -90.0};
			return res;
		}, engine.getEntities(), new Laser(-100, -100, 150, 1300)));
		addEvent(new EntityEvent(basetime + bit * startTerm + 27, 100, (Integer integer) -> {
			Double[] res = {0.0, 300.0 , -90.0};
			return res;
		}, engine.getEntities(), new Laser(-100, -100, 150, 1300)));


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

	private Double getRadian(Double x, Double y){
		return Math.atan2(y, x) * 180 / Math.PI - 90;
	}

	private Double[] toPlayer(Double startX, Double startY, Engine engine, Integer integer){
		Double pcentx = (engine.getPlayerRightX() + engine.getPlayerLeftX()) / 2.0f;
		Double pcenty = (engine.getPlayerBottomY() + engine.getPlayerTopY()) / 2.0f;
		Double vlen = Math.sqrt((pcentx - startX)*(pcentx - startX) + (pcenty - startY)*(pcenty - startY));
		Double[] res = {startX + (pcentx - startX) / vlen * 8 * integer, startY + (pcenty - startY) / vlen * 8 * integer, getRadian((pcentx - startX), (pcenty - startY))};
		return res;
	}

	private Double[] toPlayerset(Double startX, Double startY, Engine engine){
		Double pcentx = (engine.getPlayerRightX() + engine.getPlayerLeftX()) / 2.0f;
		Double pcenty = (engine.getPlayerBottomY() + engine.getPlayerTopY()) / 2.0f;
		Double vlen = Math.sqrt((pcentx - startX)*(pcentx - startX) + (pcenty - startY)*(pcenty - startY));
		Double[] res = {(pcentx - startX) / vlen, (pcenty - startY) / vlen, getRadian((pcentx - startX), (pcenty - startY))};
		return res;
	}

	private Double[] toCenterRand(Double startX, Double startY, Integer integer){
		Double pcentx = 640.0;
		Double pcenty = 360.0;
		Double vlen = Math.sqrt((pcentx - startX)*(pcentx - startX) + (pcenty - startY)*(pcenty - startY));
		Double[] res = {startX + (pcentx - startX) / vlen * 8 * integer, startY + (pcenty - startY) / vlen * 8 * integer, getRadian((pcentx - startX), (pcenty - startY))};
		return res;
	}
	private void bulletToplayer(Double startX, Double startY, Engine engine, int since){

		addEvent(new EntityEvent(since, 300, new Formula() {
			@Override
			public Double[] apply(Integer integer) {
				if(integer == 0)
					initialize(toPlayerset(startX, startY, engine));

				Double[] vec = (Double[])getInit();
				Double[] res = {startX + vec[0] * 8 * integer, startY + vec[1] * 8 * integer, vec[2]};
				return res;
			}
		}, engine.getEntities(), new Bullet(-100, -100, 50, 100)));
	}
}
