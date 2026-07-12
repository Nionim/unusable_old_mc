package delta.cion;

import delta.cion.api.plugins.TopazPlugin;

import delta.cion.handlers.Banner;
import delta.cion.handlers.PlayerHead;
import delta.cion.handlers.Signs;
import net.kyori.adventure.key.Key;
import net.minestom.server.MinecraftServer;
import net.minestom.server.instance.block.BlockManager;

public class BlockHandlers extends TopazPlugin {

	private static TopazPlugin instance;

	public static TopazPlugin getInstance() {
		return instance;
	}

	@Override
	public void onEnable() {
		instance = this;
		saveDefaultConfig();
		registerHandlers();
	}

	private void registerHandlers() {
		BlockManager blockManager = MinecraftServer.getBlockManager();
		Key hangingSign = Key.key("hanging_sign");
		Key banner = Key.key("banner");
		Key skull = Key.key("skull");
		Key sign = Key.key("sign");

		blockManager.registerHandler(hangingSign, Signs.HangingSign::new);
		blockManager.registerHandler(skull, PlayerHead::new);
		blockManager.registerHandler(sign, Signs.Sign::new);
		blockManager.registerHandler(banner, Banner::new);
	}
}
