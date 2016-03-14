package mrriegel.cfluids;

import java.io.IOException;
import java.util.Map.Entry;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

	@Override
	public void preInit(FMLPreInitializationEvent event) throws IOException {
		super.preInit(event);
		registerRenderers();
		for (Entry<Integer, Fluid> e:ModFluids.fluids.entrySet()) {
			Fluid f=e.getValue();
			Item fluid = Item.getItemFromBlock(f.getBlock());
			ModelBakery.registerItemVariants(fluid);
			final ModelResourceLocation fluidLocation = new ModelResourceLocation(
					CustomFluids.MODID + ":fluid");
			ModelLoader.setCustomMeshDefinition(fluid,
					new ItemMeshDefinition() {
						public ModelResourceLocation getModelLocation(
								ItemStack stack) {
							return fluidLocation;
						}
					});
			ModelLoader.setCustomStateMapper(f.getBlock(),
					new StateMapperBase() {
						protected ModelResourceLocation getModelResourceLocation(
								IBlockState state) {
							return fluidLocation;
						}
					});
		}
	}

	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
		registerItemModels();
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
	}

	public void registerItemModels() {
	}

	public void registerRenderers() {
	}

}
