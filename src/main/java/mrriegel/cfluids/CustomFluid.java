package mrriegel.cfluids;

import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumRarity;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class CustomFluid {
	String name;
	String displayName;
	String rarity = "common";
	int luminosity;
	int density = 1000;
	int temperature = 300;
	int viscosity = 1000;
	boolean gas;
	List<String> biomes;
	List<Effect> effects;
	boolean flammable;
	String material;
	boolean generate;
	int lakeSize;
	int chunkChance;
	int color = 0x6495ED;
	Kind design = Kind.WATER;

	enum Kind {
		WATER, LAVA;
	}

	public CustomFluid(String name, String displayName, String rarity,
			int luminosity, int density, int temperature, int viscosity,
			boolean gas, List<String> biomes, List<Effect> effects) {
		this.name = name;
		this.displayName = displayName;
		this.rarity = rarity;
		this.luminosity = luminosity;
		this.density = density;
		this.temperature = temperature;
		this.viscosity = viscosity;
		this.gas = gas;
		this.biomes = biomes;
		this.effects = effects;
	}

	public Fluid toFluid() {
		EnumRarity r = EnumRarity.COMMON;
		for (EnumRarity t : EnumRarity.values())
			if (rarity.equalsIgnoreCase(t.rarityName)) {
				r = t;
				break;
			}
		return new Fluid(name, new ResourceLocation(name + "_still"),
				new ResourceLocation(name + "_flow")).setRarity(r)
				.setLuminosity(luminosity).setDensity(density)
				.setTemperature(temperature).setViscosity(viscosity)
				.setGaseous(gas);
	}

	@Override
	public String toString() {
		return "CustomFluid [name=" + name + ", displayName=" + displayName
				+ ", rarity=" + rarity + ", luminosity=" + luminosity
				+ ", density=" + density + ", temperature=" + temperature
				+ ", viscosity=" + viscosity + ", gas=" + gas + ", biomes="
				+ biomes + ", effects=" + effects + "]";
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
