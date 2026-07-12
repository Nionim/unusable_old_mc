package delta.cion;

import delta.cion.api.plugins.TopazPlugin;
import delta.cion.listeners.*;

public class DecaSpawn extends TopazPlugin {

	private static TopazPlugin instance;

	public static TopazPlugin getInstance() {
		return instance;
	}

	@Override
	public void onEnable() {
		instance = this;
		saveDefaultConfig();
		registerLobbyEvents();
	}

	private void registerLobbyEvents() {
		getInstance().getEventNode().addListener(new PlayerConnect());
		getInstance().getEventNode().addListener(new BlockBreak());
		getInstance().getEventNode().addListener(new PlayerChat());
		getInstance().getEventNode().addListener(new PlayerInteract());
		getInstance().getEventNode().addListener(new PlayerMove());
	}
}
