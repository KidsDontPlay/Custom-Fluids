package mrriegel.cfluids;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class CreativeTab {
	public static CreativeTabs tab1 = new CreativeTabs(CustomFluids.MODID) {

		@Override
		public Item getTabIconItem() {
			return Items.bucket;
		}

		@Override
		public String getTranslatedTabLabel() {
			return CustomFluids.MODNAME;
		}
	};
}
