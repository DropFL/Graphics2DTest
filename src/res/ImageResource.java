package res;

import javax.swing.*;
import java.io.File;
import java.net.URI;

public enum ImageResource implements IResource{
	
	DROP_IMAGE("DR.jpg");
	
	private String path;
	
	ImageResource (String name) {
		path = "src/res/images/" + name;
	}
	
	@Override
	public File getFile () {
		return new File(path);
	}
	
	@Override
	public URI getURI () {
		return getFile().toURI();
	}
	
	public ImageIcon getImageIcon () {
		try {
			return new ImageIcon(getURI().toURL());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
