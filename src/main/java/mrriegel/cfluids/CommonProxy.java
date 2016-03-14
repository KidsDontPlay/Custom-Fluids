package mrriegel.cfluids;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import mrriegel.cfluids.CustomFluid.Design;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

import com.google.common.collect.Lists;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CommonProxy {
	static {
		FluidRegistry.enableUniversalBucket();
	}

	public void preInit(FMLPreInitializationEvent event) throws IOException {
		File configDir = new File(event.getModConfigurationDirectory(),
				CustomFluids.MODNAME);
		ConfigHandler.refreshConfig(new File(configDir, "config.cfg"));
		File map = new File(configDir, "handsOff.json");
		if (map.exists())
			CustomFluids.instance.fluidMap = new Gson().fromJson(
					new BufferedReader(new FileReader(map)),
					new TypeToken<Map<Integer, String>>() {
					}.getType());

		List<File> files = new ArrayList<File>();
		for (final File fileEntry : configDir.listFiles()) {
			if (fileEntry.getName().endsWith(".json")
					&& !fileEntry.getName().equals("handsOff.json"))
				files.add(fileEntry);
		}

		if (files.isEmpty()) {
			File diaa = new File(configDir, "dia.json");
			diaa.createNewFile();
			FileWriter fw = new FileWriter(diaa);
			CustomFluid dia = new CustomFluid("dia", "diamant", 70, 400, 2700,
					500, false, Arrays.asList("desert", "jungle"),
					Arrays.asList(new CustomFluid.Effect("speed", 3, 10),
							new CustomFluid.Effect("sad", 1, 2)), false, false,
					"custom", false, 3, 3, 0x00FF00, Design.WATER);
			fw.write(new GsonBuilder().setPrettyPrinting().create().toJson(dia));
			fw.close();
			files.add(diaa);
		}
		List<CustomFluid> list = Lists.newArrayList();
		for (File f : files)
			list.add((CustomFluid) new Gson().fromJson(new BufferedReader(
					new FileReader(f)), new TypeToken<CustomFluid>() {
			}.getType()));
		ModFluids.registerFluids(list);
		System.out.println("blocko1: "
				+ FluidRegistry.getFluid("dia").getBlock());

		FileWriter fw = new FileWriter(map);
		fw.write(new GsonBuilder().setPrettyPrinting().create()
				.toJson(CustomFluids.instance.fluidMap));
		fw.close();

		// PacketHandler.init();
		// ModBlocks.init();
		// ModItems.init();
		// CraftingRecipes.init();
	}

	public void init(FMLInitializationEvent event) {
		// GameRegistry.registerWorldGenerator(new LakeGenerator(), 1);
	}

	public void postInit(FMLPostInitializationEvent event) {
	}
}
