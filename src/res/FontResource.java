package res;

import java.awt.*;
import java.io.File;

public enum FontResource {
	
	PACIFITO(Font.TRUETYPE_FONT, "Pacifico.ttf");
	
	private Font font;
	private static GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
	
	FontResource (int type, String name) {
		try {
			font = Font.createFont(type, new File(getClass().getResource("fonts/" + name).getPath()));
		} catch (Exception e) {
			e.printStackTrace();
			font = null;
		}
	}
	
	public Font getFont (int style, float size) {
		return font.deriveFont(style, size);
	}
	
	public static void registerFonts () {
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		for (FontResource font : FontResource.values())
			env.registerFont(font.font);
	}
}
