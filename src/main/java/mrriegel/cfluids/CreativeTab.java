package mrriegel.cfluids;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.UniversalBucket;

import com.google.common.collect.Lists;

public class CreativeTab {

	public static int timeSinceChance = 250;
	public static List<ItemStack> lis = Lists.newArrayList();

	public static CreativeTabs tab1 = new CreativeTabs(CustomFluids.MODID) {

		@Override
		public void displayAllReleventItems(List<ItemStack> p_78018_1_) {
			// TODO Auto-generated method stub
			super.displayAllReleventItems(p_78018_1_);
		}

		@Override
		public Item getTabIconItem() {
			return ForgeModContainer.getInstance().universalBucket;
		}

		@Override
		public String getTranslatedTabLabel() {
			return CustomFluids.MODNAME;
		}
	};
}
