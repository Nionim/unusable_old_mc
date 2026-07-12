package delta.cion.listeners;

import delta.cion.spawn.WorldLoader;
import net.minestom.server.event.EventListener;
import net.minestom.server.event.player.PlayerMoveEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerMove implements EventListener<PlayerMoveEvent> {
	@Override
	public @NotNull Class<PlayerMoveEvent> eventType() {
		return PlayerMoveEvent.class;
	}

	@Override
	public @NotNull Result run(@NotNull PlayerMoveEvent event) {
		if (event.getNewPosition().blockY() >= -50) return Result.SUCCESS;
		event.getPlayer().teleport(WorldLoader.getSpawnPos());
		return Result.SUCCESS;
	}
}
