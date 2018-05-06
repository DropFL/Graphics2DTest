package res;

import javax.swing.*;
import java.io.File;
import java.net.URI;
import java.net.URL;

public enum ImageResource implements IResource{
	
	DROP_IMAGE("DR.jpg");
	
	private URL url;
	
	ImageResource (String name) {
		url = getClass().getResource("images/" + name);
	}
	
	@Override
	public File getFile () {
		return new File(url.getPath());
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
