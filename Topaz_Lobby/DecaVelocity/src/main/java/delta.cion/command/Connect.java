package delta.cion.command;

import delta.cion.DecaVelocity;
import delta.cion.api.files.utils.SenderUtils;
import delta.cion.api.plugins.TopazPlugin;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.serializer.ComponentSerializer;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import net.minestom.server.command.CommandSender;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.CommandContext;
import net.minestom.server.command.builder.CommandExecutor;
import net.minestom.server.command.builder.arguments.ArgumentType;
import net.minestom.server.entity.Player;
import net.minestom.server.network.NetworkBuffer;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class Connect extends Command implements CommandExecutor {

	private final TopazPlugin plugin = DecaVelocity.getInstance();
	private final SenderUtils senderUtils = plugin.getSenderUtils();

	private final ComponentSerializer<Component, TextComponent, String> SERIALIZER = PlainTextComponentSerializer.plainText();

	private final String[] SERVERS = plugin.getConfig().getStringList("servers");

	public Connect() {
		super("connect");
		addSyntax(this, ArgumentType.String("server"));
	}

	@Override
	public void apply(@NotNull CommandSender sender, @NotNull CommandContext commandContext) {
		if (!(sender instanceof Player)) {isFalse(sender, "for-players-only"); return;}
		String server = commandContext.get("server");
		if (server == null || server.isBlank()) {isFalse(sender, "enter-server-name"); return;}
		if (!Arrays.stream(SERVERS).toList().contains(server)) {isFalse(sender, "server-not-found"); return;}
		((Player) sender).sendPluginMessage("bungeecord:main", NetworkBuffer.makeArray(buffer -> {
			buffer.write(NetworkBuffer.STRING_IO_UTF8, "Connect");
			buffer.write(NetworkBuffer.STRING_IO_UTF8, server);
		}));
	}

	private void isFalse(CommandSender sender, String message) {
		String msg = senderUtils.getFromKey(message);
		sender.sendMessage(SERIALIZER.deserialize(msg));
	}
}
