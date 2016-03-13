package mrriegel.cfluids;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class CommonProxy {
	public void preInit(FMLPreInitializationEvent event) {
		ConfigHandler.refreshConfig(event.getSuggestedConfigurationFile());
//		PacketHandler.init();
		ModBlocks.init();
		ModItems.init();
//		CraftingRecipes.init();
	}

	public void init(FMLInitializationEvent event) {
	}

	public void postInit(FMLPostInitializationEvent event) {
	}
}
