package res;

import javax.swing.*;
import java.awt.*;

public enum ImageResource {
	
	DROP_IMAGE("DR.jpg"),
	UNIT_IMAGE("Unit.png"),
	TEMP_UNIT_100("TmpUnit100.jpg"),
	WP_720p("720p_wp.jpg"),
	MAP_1("Map1_1280x720.jpg"),
	BLOCK_1("TmpBlock1.jpg"),
	SHIELD("Shield.png"),
	GHOST_1("Ghost_type1.png"),
	BULLET_ORB_1("bullet_type1_orb1.png"),
	BULLET_ORB_2("bullet_type1_orb2.png"),
	LASER("laser.jpg"),
	SAMPLE_BUTTON("sample_button.png");
	
	private ImageIcon imageIcon;
	
	ImageResource (String name) {
		try {
			imageIcon = new ImageIcon(getClass().getResource("images/" + name));
		} catch (Exception e) {
			e.printStackTrace();
			imageIcon = null;
		}
	}
	
	public ImageIcon getImageIcon () {
		return imageIcon;
	}
	public ImageIcon getImageIcon (int width, int height) {
		return new ImageIcon(imageIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
	}
	
	public static ImageResource getImageResource (String id) {
		switch (id) {
			case "UNIT":
				return UNIT_IMAGE;
		}
		
		return null;
	}
}
