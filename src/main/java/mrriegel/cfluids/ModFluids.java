package mrriegel.cfluids;

import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

import com.google.common.collect.Maps;

public class ModFluids {
	public static final Map<Integer, Fluid> fluids = Maps.newHashMap();
	public final static Map<Integer, CustomBlockFluid> blocks = Maps
			.newHashMap();

	public static void registerFluids(List<CustomFluid> list) {
		for (int i = 0; i < list.size(); i++) {
			CustomFluid fluid = list.get(i);
			registerFluid(fluid, i);

		}
	}

	private static void registerFluid(CustomFluid f, int index) {
		boolean useOwnFluid = FluidRegistry.registerFluid(f.toFluid());

		if (useOwnFluid) {
			fluids.put(index, FluidRegistry.getFluid(f.name));
			registerFluidBlock(f, index);

		}
	}

	private static void registerFluidBlock(CustomFluid fluid, int index) {
		CustomBlockFluid block = new CustomBlockFluid(fluid);
		block.setRegistryName("fluid." + block.getFluid().getName());
		block.setUnlocalizedName(CustomFluids.MODID + ":"
				+ block.getFluid().getUnlocalizedName());
		block.setCreativeTab(CreativeTab.tab1);
		blocks.put(index, block);
		GameRegistry.registerBlock(blocks.get(index));
		FluidRegistry.addBucketForFluid(FluidRegistry.getFluid(fluid.name));

	}
}
