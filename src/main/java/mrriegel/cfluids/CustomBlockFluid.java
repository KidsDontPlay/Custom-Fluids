package mrriegel.cfluids;

import java.lang.reflect.Field;

import mrriegel.cfluids.CustomFluid.Effect;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class CustomBlockFluid extends BlockFluidClassic {
	CustomFluid fluid;

	public CustomBlockFluid(CustomFluid fluid) {
		super(FluidRegistry.getFluid(fluid.name), getMaterial(fluid));
		setLightOpacity(0);
		this.fluid = fluid;
//		this.setUnlocalizedName(CustomFluids.MODID + ":" + fluid.name);
		setCreativeTab(CreativeTab.tab1);
	}

	@Override
	public String getLocalizedName() {
		return fluid.displayName;
	}

	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos,
			Entity entityIn) {
		if (worldIn.isRemote)
			return;
		if (entityIn instanceof EntityLivingBase && fluid.effects != null) {
			for (Effect e : fluid.effects) {
				e.giveEffect((EntityLivingBase) entityIn);
			}
		}
		if (ConfigHandler.temperatureBurn < fluid.temperature
				&& !entityIn.isImmuneToFire()) {
			entityIn.attackEntityFrom(DamageSource.inFire, 3.0F);
			entityIn.setFire(3 + worldIn.rand.nextInt(4));
		}
	}

	private static Material getMaterial(CustomFluid fluid) {
		String s = fluid.material.toLowerCase();
		if (s == null||s.equals("custom"))
			return CustomMaterial.instance;
		try {
			Material m = Material.water;
			Field field = m.getClass().getField(s.toLowerCase());
			field.setAccessible(true);
			Object value = field.get(m);
			return (Material) value;
		} catch (Exception e) {
			return CustomMaterial.instance;
		}
	}

	static class CustomMaterial extends MaterialLiquid {
		final static CustomMaterial instance = new CustomMaterial();

		public CustomMaterial() {
			super(MapColor.waterColor);
		}
	}

	@Override
	public boolean isFlammable(IBlockAccess world, BlockPos pos, EnumFacing face) {
		return fluid.flammable;
	}

	@Override
	public boolean isFireSource(World world, BlockPos pos, EnumFacing side) {
		return fluid.fireSource;
	}

	@Override
	public int getFlammability(IBlockAccess world, BlockPos pos, EnumFacing face) {
		return 0;
	}

	@Override
	public boolean canDisplace(IBlockAccess world, BlockPos pos) {
		if (world.getBlockState(pos).getBlock().getMaterial().isLiquid()) {
			return false;
		}
		return super.canDisplace(world, pos);
	}

	@Override
	public boolean displaceIfPossible(World world, BlockPos pos) {
		if (world.getBlockState(pos).getBlock().getMaterial().isLiquid()) {
			return false;
		}
		return super.displaceIfPossible(world, pos);
	}
}
