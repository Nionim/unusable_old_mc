package delta.cion.listeners;

import delta.cion.api.util.Sender;
import net.minestom.server.event.EventListener;
import net.minestom.server.event.player.PlayerChatEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerChat implements EventListener<PlayerChatEvent> {

	@Override
	public @NotNull Class<PlayerChatEvent> eventType() {
		return PlayerChatEvent.class;
	}

	@Override
	public @NotNull Result run(@NotNull PlayerChatEvent event) {
		Sender.send(event.getPlayer(), "Это лобби.. ( . _.) \nВ нем пока нельзя отправлять сообщения.. <3");
		event.setCancelled(true);
		return Result.SUCCESS;
	}
}
