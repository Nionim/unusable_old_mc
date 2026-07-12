package delta.cion.spawn;

import delta.cion.DecaSpawn;
import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.anvil.AnvilLoader;
import org.slf4j.Logger;

import java.io.File;
import java.io.FileFilter;
import java.nio.file.Path;
import java.util.Objects;

public class WorldLoader {

	private final InstanceContainer CONTAINER = MinecraftServer.getInstanceManager().createInstanceContainer();

	private final Logger LOGGER = DecaSpawn.getInstance().getLogger();

	private static final File PLUGIN_DIR = DecaSpawn.getInstance().getPluginDir();
	private static final Path WORLD_PATH = new File(PLUGIN_DIR, "world").toPath();;

	private static final File LEVEL_DAT = new File(WORLD_PATH.toFile(), "level.dat");
	private static final File REGIONS_DIR = new File(WORLD_PATH.toFile(), "region");

	public WorldLoader() {
		if (!LEVEL_DAT.exists() || !LEVEL_DAT.isFile())
			isFalse("level.dat is not exists");
		if (!REGIONS_DIR.exists() || REGIONS_DIR.isFile())
			isFalse("world/region dir is not exists");
		if (Objects.requireNonNull(REGIONS_DIR.listFiles(isMCA())).length == 0)
			isFalse("world/region is empty or not contains .mca files!");
	}

	public InstanceContainer getWorld() {
		this.CONTAINER.setChunkLoader(new AnvilLoader(WORLD_PATH));
		this.CONTAINER.setTime(18000);
		this.CONTAINER.setTimeRate(0);
		return this.CONTAINER;
	}

	public static Pos getSpawnPos() {
		Integer[] cords = DecaSpawn.getInstance().getConfig().getIntList("spawn-cords");
		return new Pos(cords[0], cords[1], cords[2]);
	}

	private void isFalse(String message) {
		LOGGER.error(message);
		DecaSpawn.getInstance().disable();
	}

	private static FileFilter isMCA() {
		return pathname -> pathname.getName().endsWith(".mca");
	}
}
