package mrriegel.cfluids;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Mod(modid = CustomFluids.MODID, name = CustomFluids.MODNAME, version = CustomFluids.VERSION,dependencies = "required-after:Forge@[11.15.1.1761,);")
public class CustomFluids {
	public static final String MODID = "cfluids";
	public static final String VERSION = "1.0.0";
	public static final String MODNAME = "Custom Fluids";

	@Instance(CustomFluids.MODID)
	public static CustomFluids instance;

	public List<CustomFluid> fluids = Lists.newArrayList();
	public Map<Integer, String> fluidMap = Maps.newHashMap();
	public Map<Integer, Block> Blocks = Maps.newHashMap();

	@SidedProxy(clientSide = "mrriegel.cfluids.ClientProxy", serverSide = "mrriegel.cfluids.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) throws IOException {
		proxy.preInit(event);
		EntityPlayer p;
//		p.setFire(seconds);
//		DamageSource
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