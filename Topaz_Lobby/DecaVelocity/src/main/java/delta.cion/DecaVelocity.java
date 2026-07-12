package delta.cion;

import delta.cion.api.plugins.TopazPlugin;
import delta.cion.command.Connect;
import net.minestom.server.extras.velocity.VelocityProxy;

public class DecaVelocity extends TopazPlugin {

	private static TopazPlugin instance;

	public static TopazPlugin getInstance() {
		return instance;
	}

	private static String getVelocityToken() {
		return getInstance().getConfig().getString("velocity-token");
	}

	private static void enableVelocity() {
		String token = getVelocityToken();
		if (token != null && !token.isBlank()) VelocityProxy.enable(token);
		else getInstance().getLogger().error("Velocity token is null!");
	}

	@Override
	public void onEnable() {
		instance = this;
		saveDefaultConfig();

		getLogger().info("Trying to start Velocity!");
		getCommandNode().addToNode(new Connect());
		getCommandNode().registerNode();
		enableVelocity();
	}

	@Override
	public void onDisable() {

	}
}
