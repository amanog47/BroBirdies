package com.hungryfool.brobirdies.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.hungryfool.brobirdies.BroBirdies;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = BroBirdies.WIDTH;
		config.height = BroBirdies.HEIGHT;
		config.title = BroBirdies.TITLE;
	new LwjglApplication(new BroBirdies(), config);
}
}
