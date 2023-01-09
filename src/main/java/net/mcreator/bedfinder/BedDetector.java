/**
 * The code of this mod element is always locked.
 *
 * You can register new events in this class too.
 *
 * If you want to make a plain independent class, create it using
 * Project Browser -> New... and make sure to make the class
 * outside net.mcreator.bedfinder as this package is managed by MCreator.
 *
 * If you change workspace package, modid or prefix, you will need
 * to manually adapt this file to these changes or remake it.
 *
 * This class will be added in the mod root package.
*/
package net.mcreator.bedfinder;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.Position;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockPos;
import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraft.world.level.block.Block;
import net.minecraft.client.resources.model.Material;
import net.minecraft.world.level.block.Blocks;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BedDetector {
	Minecraft mc;

	public BedDetector() {
		this.mc = Minecraft.getInstance();
		MinecraftForge.EVENT_BUS.register(this);
	}

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		new BedDetector();
		System.out.println("TEST!!!!!!!!!!!!!!!!");
	}

	public boolean checkAreaForBed(Level l, Player p, int px, int py, int pz) {
		for (int j = 0; j < 320; j++) {
			for (int i = -5; i < 5; ++i) {
				if (getBlockAt(l, p, px + i, pz - 5, j) == Blocks.RED_BED) { System.out.println("BED AT" + px + i + ", " + (pz - 5) + ", " + j); }
				if (getBlockAt(l, p, px - 5, pz + i, j) == Blocks.RED_BED) { System.out.println("BED AT" + (px - 5) + ", " + pz + i + ", " + j); }
				if (getBlockAt(l, p, px + i, pz + 5, j) == Blocks.RED_BED) { System.out.println("BED AT" + px + i + ", " + pz + 5 + ", " + j); }
				if (getBlockAt(l, p, px + 5, pz + i, j) == Blocks.RED_BED) { System.out.println("BED AT" + px + 5 + ", " + pz + i + ", " + j); }
				
			}
		}
		return true;
	}

	@SubscribeEvent
	public void getBlockHandler(EntityItemPickupEvent event) {
		Player p = event.getEntity();
	 	Level l = p.getLevel();
	 	int px = p.getBlockX();
	 	int py = p.getBlockY();
	 	int pz = p.getBlockZ();
		System.out.println("ITEM PICKED UP!!!!!!");
		//getBlockAt(l, p, 10, 52, 10);
		checkAreaForBed(l, p, px, py, pz);
		if (getBlockAt(l, p, px, py, pz) == Blocks.STONE) { System.out.println("STONE!"); }
	}

	public Block getBlockAt(Level level, Player player, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);
		//System.out.println(level.getBlockState(pos).getBlock());
		
		return level.getBlockState(pos).getBlock();
	}
}
