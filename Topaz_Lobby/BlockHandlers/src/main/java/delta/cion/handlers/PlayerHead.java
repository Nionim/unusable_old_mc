package delta.cion.handlers;

import net.kyori.adventure.key.Key;
import net.minestom.server.instance.block.BlockHandler;
import net.minestom.server.tag.Tag;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

public class PlayerHead implements BlockHandler {

	private static final Key KEY = Key.key("skull");

	private static final List<Tag<?>> TAGS = List.of(
		Tag.NBT("profile")
	);

	@Override
	public @NotNull Key getKey() {
		return KEY;
	}

	@Override
	public @NotNull Collection<Tag<?>> getBlockEntityTags() {
		return TAGS;
	}
}
