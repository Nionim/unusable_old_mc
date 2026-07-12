package delta.cion.listeners;

import net.minestom.server.event.EventListener;
import net.minestom.server.event.player.PlayerBlockInteractEvent;
import net.minestom.server.event.player.PlayerUseItemEvent;
import net.minestom.server.instance.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PlayerInteract implements EventListener<PlayerBlockInteractEvent> {

	private final ArrayList<Block> BLOCkS = new ArrayList<>(List.of(
		Block.POLISHED_BLACKSTONE_BUTTON
		// Another blocks maybe
	));

	@Override
	public @NotNull Class<PlayerBlockInteractEvent> eventType() {
		return PlayerBlockInteractEvent.class;
	}

	@Override
	public @NotNull Result run(@NotNull PlayerBlockInteractEvent event) {
		if (BLOCkS.contains(event.getBlock())) return Result.SUCCESS;
		event.setCancelled(true);
		return Result.SUCCESS;
	}
}
