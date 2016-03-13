package mrriegel.cfluids;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import mrriegel.cfluids.CustomFluid.Effect;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.FluidRegistry;

public class CustomBlockFluid extends BlockFluidClassic {
	CustomFluid fluid;

	public CustomBlockFluid(CustomFluid fluid) {
		super(fluid.toFluid(), getMaterial(fluid.material));
		this.fluid = fluid;
		this.setUnlocalizedName(CustomFluids.MODID + ":" + fluid.name);
	}

	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos,
			Entity entityIn) {
		if (entityIn instanceof EntityLivingBase && fluid.effects != null) {
			for (Effect e : fluid.effects) {
				e.giveEffect((EntityLivingBase) entityIn);

			}
		}
		if (ConfigHandler.temperatureBurn < fluid.temperature)
			entityIn.setFire(3);
	}

	public static Material getMaterial(String s) {
		if (s == null)
			return Material.water;
		try {
			Material m = Material.water;
			Field field = m.getClass().getField(s.toLowerCase());
			field.setAccessible(true);
			Object value = field.get(m);
			return (Material) value;
		} catch (Exception e) {
			return Material.water;
		}
	}
}
