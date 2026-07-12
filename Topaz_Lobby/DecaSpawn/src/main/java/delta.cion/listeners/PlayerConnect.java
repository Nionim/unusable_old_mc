package delta.cion.listeners;

import delta.cion.DecaSpawn;
import delta.cion.spawn.WorldLoader;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.serializer.ComponentSerializer;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;
import net.minestom.server.event.EventListener;
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent;
import net.minestom.server.instance.InstanceContainer;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.net.SocketAddress;

public class PlayerConnect implements EventListener<AsyncPlayerConfigurationEvent> {

	private static final ComponentSerializer<Component, TextComponent, String> SERIALIZER = PlainTextComponentSerializer.plainText();
	private static final Logger LOGGER = DecaSpawn.getInstance().getLogger();

	@Override
	public @NotNull Class<AsyncPlayerConfigurationEvent> eventType() {
		return AsyncPlayerConfigurationEvent.class;
	}

	@Override
	public @NotNull Result run(@NotNull AsyncPlayerConfigurationEvent event) {
		Player player = event.getPlayer();

		SocketAddress playerAddress = player.getPlayerConnection().getRemoteAddress();
		String playerIP = playerAddress.toString().substring(1);
		String playerName = SERIALIZER.serialize(player.getName());

		LOGGER.info("{}[{}] trying to connect", playerName, playerIP);

		WorldLoader loader = new WorldLoader();
		InstanceContainer world = loader.getWorld();
		Pos spawn = WorldLoader.getSpawnPos();
		if (world == null) return isFalse(player, "World cant be load, lmao");
		player.setRespawnPoint(spawn);
		event.setSpawningInstance(world);
		return Result.SUCCESS;
	}

	private Result isFalse(Player player, String message) {
		Component component = SERIALIZER.deserialize(message);
		player.kick(component);
		LOGGER.error(message);
		return Result.SUCCESS;
	}
}
