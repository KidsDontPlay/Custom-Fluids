package mrriegel.cfluids;

import java.util.List;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumRarity;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

public class CustomFluid {
	String name;
	String displayName;
	int luminosity;
	int density = 1000;
	int temperature = 300;
	int viscosity = 1000;
	boolean gas;
	List<String> biomes;
	List<Effect> effects;
	boolean flammable;
	boolean fireSource;
	String material;
	boolean generate;
	int lakeSize;
	int chunkChance;
	int color = 0x6495ED;
	Design design = Design.WATER;

	enum Design {
		WATER, LAVA;

		ResourceLocation getStill() {
			if (this == WATER)
				return new ResourceLocation(CustomFluids.MODID
						+ ":fluid/liquid");
			else if (this == LAVA)
				return new ResourceLocation(CustomFluids.MODID
						+ ":fluid/molten_metal");
			return new ResourceLocation(CustomFluids.MODID
					+ ":fluid/liquid");
		}

		ResourceLocation getFlowing() {
			if (this == WATER)
				return new ResourceLocation(CustomFluids.MODID
						+ ":fluid/liquid_flow");
			else if (this == LAVA)
				return new ResourceLocation(CustomFluids.MODID
						+ ":fluid/molten_metal_flow");
			return new ResourceLocation(CustomFluids.MODID
					+ ":fluid/liquid_flow");
		}
	}


	public CustomFluid(String name, String displayName, int luminosity,
			int density, int temperature, int viscosity, boolean gas,
			List<String> biomes, List<Effect> effects, boolean flammable,
			boolean fireSource, String material, boolean generate,
			int lakeSize, int chunkChance, int color, Design design) {
		this.name = name;
		this.displayName = displayName;
		this.luminosity = luminosity;
		this.density = density;
		this.temperature = temperature;
		this.viscosity = viscosity;
		this.gas = gas;
		this.biomes = biomes;
		this.effects = effects;
		this.flammable = flammable;
		this.fireSource = fireSource;
		this.material = material;
		this.generate = generate;
		this.lakeSize = lakeSize;
		this.chunkChance = chunkChance;
		this.color = color;
		this.design = design;
	}



	public Fluid toFluid() {
		return new Fluid(name, null, null) {
			@Override
			public String getLocalizedName(FluidStack stack) {
				return displayName;
			}

			@Override
			public int getColor() {
				int c = color;
				if (((c >> 24) & 0xFF) == 0) {
					c |= 0xFF << 24;
				}
				return c;
			}

			@Override
			public ResourceLocation getStill() {
				return design.getStill();
			}

			@Override
			public ResourceLocation getFlowing() {
				return design.getFlowing();
			}

		}.setLuminosity(luminosity).setDensity(density)
				.setTemperature(temperature).setViscosity(viscosity)
				.setGaseous(gas);
	}

	

	@Override
	public String toString() {
		Minecraft.getMinecraft().displayGuiScreen(guiScreenIn);
		Keyboard
		return "CustomFluid [name=" + name + ", displayName=" + displayName
				+ ", luminosity=" + luminosity + ", density=" + density
				+ ", temperature=" + temperature + ", viscosity=" + viscosity
				+ ", gas=" + gas + ", biomes=" + biomes + ", effects="
				+ effects + ", flammable=" + flammable + ", fireSource="
				+ fireSource + ", material=" + material + ", generate="
				+ generate + ", lakeSize=" + lakeSize + ", chunkChance="
				+ chunkChance + ", color=" + color + ", design=" + design + "]";
	}



	public static class Effect {
		String name;
		int strength;
		int duration;

		public Effect(String name, int strength, int duration) {
			super();
			this.name = name;
			this.strength = strength;
			this.duration = duration;
		}

		public void giveEffect(EntityLivingBase player) {
			if (strength < 1 || duration < 1)
				return;
			Potion p = Potion.getPotionFromResourceLocation(name);
			if (p == null)
				return;
			PotionEffect e = new PotionEffect(p.id, duration * 20, strength - 1);
			player.addPotionEffect(e);
		}
	}
}
