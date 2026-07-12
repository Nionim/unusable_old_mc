package delta.cion.handlers;

import net.kyori.adventure.key.Key;
import net.minestom.server.instance.block.BlockHandler;
import net.minestom.server.tag.Tag;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;


public class Banner implements BlockHandler {

	private static final Key KEY = Key.key("banner");

	private static final List<Tag<?>> TAGS = List.of(
		Tag.NBT("patterns")
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
