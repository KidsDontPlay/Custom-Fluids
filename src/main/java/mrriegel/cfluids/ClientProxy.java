package mrriegel.cfluids;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		registerRenderers();
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
		ItemModelMesher mesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
		mesher.register(Item.getItemFromBlock(ModBlocks.kabel), 0, new ModelResourceLocation(StorageNetwork.MODID + ":kabel", "inventory"));
		mesher.register(Item.getItemFromBlock(ModBlocks.exKabel), 0, new ModelResourceLocation(StorageNetwork.MODID + ":exKabel", "inventory"));
		mesher.register(Item.getItemFromBlock(ModBlocks.storageKabel), 0, new ModelResourceLocation(StorageNetwork.MODID + ":storageKabel", "inventory"));
		mesher.register(Item.getItemFromBlock(ModBlocks.imKabel), 0, new ModelResourceLocation(StorageNetwork.MODID + ":imKabel", "inventory"));
		mesher.register(Item.getItemFromBlock(ModBlocks.vacuumKabel), 0, new ModelResourceLocation(StorageNetwork.MODID + ":vacuumKabel", "inventory"));
		mesher.register(Item.getItemFromBlock(ModBlocks.fexKabel), 0, new ModelResourceLocation(StorageNetwork.MODID + ":fexKabel", "inventory"));
		mesher.register(Item.getItemFromBlock(ModBlocks.fstorageKabel), 0, new ModelResourceLocation(StorageNetwork.MODID + ":fstorageKabel", "inventory"));
		mesher.register(Item.getItemFromBlock(ModBlocks.fimKabel), 0, new ModelResourceLocation(StorageNetwork.MODID + ":fimKabel", "inventory"));
		mesher.register(Item.getItemFromBlock(ModBlocks.master), 0, new ModelResourceLocation(StorageNetwork.MODID + ":master", "inventory"));
		mesher.register(Item.getItemFromBlock(ModBlocks.request), 0, new ModelResourceLocation(StorageNetwork.MODID + ":request", "inventory"));
		mesher.register(Item.getItemFromBlock(ModBlocks.frequest), 0, new ModelResourceLocation(StorageNetwork.MODID + ":frequest", "inventory"));
		for (int i = 0; i < ItemUpgrade.NUM; i++) {
			ModelBakery.registerItemVariants(ModItems.upgrade, new ResourceLocation(StorageNetwork.MODID + ":upgrade_" + i));
			mesher.register(ModItems.upgrade, i, new ModelResourceLocation(StorageNetwork.MODID + ":upgrade_" + i, "inventory"));
		}
		for (int i = 0; i < 2; i++) {
			ModelBakery.registerItemVariants(ModItems.remote, new ResourceLocation(StorageNetwork.MODID + ":remote_" + i));
			mesher.register(ModItems.remote, i, new ModelResourceLocation(StorageNetwork.MODID + ":remote_" + i, "inventory"));
		}
		mesher.register(ModItems.coverstick, 0, new ModelResourceLocation(StorageNetwork.MODID + ":coverstick", "inventory"));
		mesher.register(Item.getItemFromBlock(ModBlocks.cover), 0, new ModelResourceLocation(StorageNetwork.MODID + ":cover", "inventory"));
		for (int i = 0; i < 2; i++) {
			ModelBakery.registerItemVariants(ModItems.template, new ResourceLocation(StorageNetwork.MODID + ":template_" + i));
			mesher.register(ModItems.template, i, new ModelResourceLocation(StorageNetwork.MODID + ":template_" + i, "inventory"));
		}
	}

	public void registerRenderers() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileKabel.class, new CableRenderer());
	}

}
