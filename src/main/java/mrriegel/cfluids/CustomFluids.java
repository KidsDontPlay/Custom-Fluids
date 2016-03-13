package mrriegel.cfluids;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = CustomFluids.MODID, name = CustomFluids.MODNAME, version = CustomFluids.VERSION, guiFactory = "mrriegel.storagenetwork.config.GuiFactory")
public class CustomFluids {
	public static final String MODID = "cfluids";
	public static final String VERSION = "1.0.0";
	public static final String MODNAME = "Custom Fluids";

	@Instance(CustomFluids.MODID)
	public static CustomFluids instance;

	@SidedProxy(clientSide = "mrriegel.cfluids.ClientProxy", serverSide = "mrriegel.cfluids.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}

}