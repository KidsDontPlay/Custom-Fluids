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

import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CommonProxy {
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
			CustomFluid dia = new CustomFluid("dia", "diamant", "Common", 7,
					400, 270, 500, false, Arrays.asList("desert", "jungle"),
					Arrays.asList(new CustomFluid.Effect("speed", 3, 10),
							new CustomFluid.Effect("sad", 1, 2)));
			fw.write(new GsonBuilder().setPrettyPrinting().create().toJson(dia));
			fw.close();
			files.add(diaa);
		}

		for (File f : files)
			CustomFluids.instance.fluids.add((CustomFluid) new Gson().fromJson(
					new BufferedReader(new FileReader(f)),
					new TypeToken<CustomFluid>() {
					}.getType()));
		System.out.println("zip: ");
		for (CustomFluid cf : CustomFluids.instance.fluids) {
			FluidRegistry.registerFluid(cf.toFluid());
			GameRegistry.registerBlock(new CustomBlockFluid(cf), cf.name);
			if (!CustomFluids.instance.fluidMap.containsValue(cf.name)) {
				int index = 0;
				while (CustomFluids.instance.fluidMap.containsKey(index))
					index++;
				CustomFluids.instance.fluidMap.put(index, cf.name);
			}
		}

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
//		GameRegistry.registerWorldGenerator(new LakeGenerator(), 1);
	}

	public void postInit(FMLPostInitializationEvent event) {
	}
}
