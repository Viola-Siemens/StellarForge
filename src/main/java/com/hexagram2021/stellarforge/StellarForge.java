package com.hexagram2021.stellarforge;

import com.hexagram2021.stellarforge.client.ClientProxy;
import com.hexagram2021.stellarforge.common.SFContent;
import com.hexagram2021.stellarforge.common.config.SFCommonConfig;
import com.hexagram2021.stellarforge.common.util.RegistryChecker;
import com.hexagram2021.stellarforge.common.util.SFLogger;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLLoader;
import org.apache.logging.log4j.LogManager;

import java.util.function.Supplier;

import static com.hexagram2021.stellarforge.common.util.RegistryHelper.getRegistryName;

@Mod(StellarForge.MODID)
public class StellarForge {
	public static final String MODID = "stellarforge";
	public static final String MODNAME = "StellarForge";
	public static final String VERSION = ModList.get().getModFileById(MODID).versionString();

	public static <T>
	Supplier<T> bootstrapErrorToXCPInDev(Supplier<T> in) {
		if(FMLLoader.isProduction()) {
			return in;
		}
		return () -> {
			try {
				return in.get();
			} catch(BootstrapMethodError e) {
				throw new RuntimeException(e);
			}
		};
	}

	public StellarForge() {
		SFLogger.logger = LogManager.getLogger(MODID);
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		SFContent.modConstruction(bus);
		DistExecutor.safeRunWhenOn(Dist.CLIENT, bootstrapErrorToXCPInDev(() -> ClientProxy::modConstruction));

		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, SFCommonConfig.getConfig());

		bus.addListener(SFContent::onNewRegistry);
		MinecraftForge.EVENT_BUS.addListener(SFContent::registerCommands);
		MinecraftForge.EVENT_BUS.addListener(this::onServerAboutToStart);
		MinecraftForge.EVENT_BUS.register(this);
	}

	private void onServerAboutToStart(ServerAboutToStartEvent event) {
		if(SFCommonConfig.ENABLE_REGISTRY_CHECK.get()) {
			RegistryChecker.registryCheck(event.getServer().getLootData());
		}
	}
}
