package jeckelstoragemod.content;

import jeckelcorelibrary.api.managers.IContentManager;
import jeckelcorelibrary.core.tabs.MappedCreativeTab;
import jeckelcorelibrary.utils.GameRegUtil;
import jeckelstoragemod.content.barrel.BlockBarrelGem;
import jeckelstoragemod.content.barrel.BlockBarrelMetal;
import jeckelstoragemod.content.barrel.BlockBarrelRock;
import jeckelstoragemod.content.barrel.BlockBarrelWood;
import jeckelstoragemod.content.barrel.ItemBlockBarrel;
import jeckelstoragemod.content.barrel.TileBarrel;
import jeckelstoragemod.content.crate.BlockCrateGem;
import jeckelstoragemod.content.crate.BlockCrateMetal;
import jeckelstoragemod.content.crate.BlockCrateRock;
import jeckelstoragemod.content.crate.BlockCrateWood;
import jeckelstoragemod.content.crate.ItemBlockCrate;
import jeckelstoragemod.content.crate.TileCrate;
import jeckelstoragemod.content.items.ItemNuggetIron;
import jeckelstoragemod.content.items.ItemPartHoop;
import jeckelstoragemod.content.items.ItemPartNails;
import jeckelstoragemod.content.trunk.ItemBlockTrunk;
import jeckelstoragemod.content.trunk.armor.BlockTrunkArmor;
import jeckelstoragemod.content.trunk.armor.MessageTrunkArmor;
import jeckelstoragemod.content.trunk.armor.TileTrunkArmor;
import jeckelstoragemod.core.Refs;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ContentManager implements IContentManager
{
	public static class ModBlocks
	{
		public static Block barrel_wood;
		public static Block barrel_rock;
		public static Block barrel_metal;
		public static Block barrel_gem;

		public static Block crate_wood;
		public static Block crate_rock;
		public static Block crate_metal;
		public static Block crate_gem;

		public static Block trunk_armor;
	}

	public static class ModItems
	{
		public static ItemNuggetIron nugget_iron;
		public static ItemPartHoop part_hoop;
		public static ItemPartNails part_nails;
	}

	@Override public void pre()
	{
		ModItems.nugget_iron = new ItemNuggetIron();
		GameRegUtil.item(ModItems.nugget_iron);
		OreDictionary.registerOre("nuggetIron", new ItemStack(ModItems.nugget_iron, 1, 0));

		ModItems.part_hoop = new ItemPartHoop();
		GameRegUtil.item(ModItems.part_hoop);

		ModItems.part_nails = new ItemPartNails();
		GameRegUtil.item(ModItems.part_nails);

		// Barrels

		ModBlocks.barrel_wood = new BlockBarrelWood();
		GameRegUtil.block(ModBlocks.barrel_wood, ItemBlockBarrel.class, TileBarrel.class);

		ModBlocks.barrel_rock = new BlockBarrelRock();
		GameRegUtil.block(ModBlocks.barrel_rock, ItemBlockBarrel.class, TileBarrel.class);

		ModBlocks.barrel_metal = new BlockBarrelMetal();
		GameRegUtil.block(ModBlocks.barrel_metal, ItemBlockBarrel.class, TileBarrel.class);

		ModBlocks.barrel_gem = new BlockBarrelGem();
		GameRegUtil.block(ModBlocks.barrel_gem, ItemBlockBarrel.class, TileBarrel.class);

		// Crates

		ModBlocks.crate_wood = new BlockCrateWood();
		GameRegUtil.block(ModBlocks.crate_wood, ItemBlockCrate.class, TileCrate.class);

		ModBlocks.crate_rock = new BlockCrateRock();
		GameRegUtil.block(ModBlocks.crate_rock, ItemBlockCrate.class, TileCrate.class);

		ModBlocks.crate_metal = new BlockCrateMetal();
		GameRegUtil.block(ModBlocks.crate_metal, ItemBlockCrate.class, TileCrate.class);

		ModBlocks.crate_gem = new BlockCrateGem();
		GameRegUtil.block(ModBlocks.crate_gem, ItemBlockCrate.class, TileCrate.class);

		// Trunks

		ModBlocks.trunk_armor = new BlockTrunkArmor();
		GameRegUtil.block(ModBlocks.trunk_armor, ItemBlockTrunk.class, TileTrunkArmor.class);


		MappedCreativeTab tab = new MappedCreativeTab(Refs.ModId);
		tab.addBlock(Refs.ModId, ModBlocks.crate_wood);
		tab.addBlock(Refs.ModId, ModBlocks.crate_rock);
		tab.addBlock(Refs.ModId, ModBlocks.crate_metal);
		tab.addBlock(Refs.ModId, ModBlocks.crate_gem);
		tab.addBlock(Refs.ModId, ModBlocks.barrel_wood);
		tab.addBlock(Refs.ModId, ModBlocks.barrel_rock);
		tab.addBlock(Refs.ModId, ModBlocks.barrel_metal);
		tab.addBlock(Refs.ModId, ModBlocks.barrel_gem);
		tab.addBlock(Refs.ModId, ModBlocks.trunk_armor);
		tab.addItem(Refs.ModId, ModItems.part_hoop);
		tab.addItem(Refs.ModId, ModItems.part_nails);
		tab.addItem(Refs.ModId, ModItems.nugget_iron);
		tab.setIconItemStack(new ItemStack(ModBlocks.crate_wood, 1, 0));
	}

	@Override public void initialize()
	{
		GameRegUtil.recipeShaped(new ItemStack(ModItems.nugget_iron, 9, 0), "?", '?', new ItemStack(Items.iron_ingot));
		GameRegUtil.recipeShaped(new ItemStack(Items.iron_ingot), "???", "???", "???", '?', "nuggetIron");

		GameRegUtil.recipeShaped(new ItemStack(ModItems.part_hoop, 6, 0), "???", " # ", "???", '?', "nuggetIron", '#', new ItemStack(Items.redstone));

		GameRegUtil.recipeShaped(new ItemStack(ModItems.part_nails, 4, 0), " ? ", "?#?", '?', "nuggetIron", '#', new ItemStack(Items.redstone));

		int count;

		// Barrels

		count = BlockBarrelWood.SUBNAMES.length;
		for (int meta = 0; meta < count; meta++)
		{
			registerBarrelRecipe(new ItemStack(ModBlocks.barrel_wood, 1, meta), new ItemStack(Blocks.planks, 1, meta));
		}

		count = BlockBarrelRock.SUBNAMES.length;
		for (int meta = 0; meta < count; meta++)
		{
			Object source = BlockBarrelRock.SUBSOURCES[meta];
			if (source == null) { continue; }
			registerBarrelRecipe(new ItemStack(ModBlocks.barrel_rock, 1, meta), source);
		}

		count = BlockBarrelMetal.SUBNAMES.length;
		for (int meta = 0; meta < count; meta++)
		{
			Object source = BlockBarrelMetal.SUBSOURCES[meta];
			if (source == null) { continue; }
			registerBarrelRecipe(new ItemStack(ModBlocks.barrel_metal, 1, meta), source);
		}

		count = BlockBarrelGem.SUBNAMES.length;
		for (int meta = 0; meta < count; meta++)
		{
			Object source = BlockBarrelGem.SUBSOURCES[meta];
			if (source == null) { continue; }
			registerBarrelRecipe(new ItemStack(ModBlocks.barrel_gem, 1, meta), source);
		}

		// Crates

		count = BlockCrateWood.SUBNAMES.length;
		for (int meta = 0; meta < count; meta++)
		{
			registerCrateRecipe(new ItemStack(ModBlocks.crate_wood, 1, meta), new ItemStack(Blocks.planks, 1, meta));
		}

		count = BlockCrateRock.SUBNAMES.length;
		for (int meta = 0; meta < count; meta++)
		{
			Object source = BlockCrateRock.SUBSOURCES[meta];
			if (source == null) { continue; }
			registerCrateRecipe(new ItemStack(ModBlocks.crate_rock, 1, meta), source);
		}

		count = BlockCrateMetal.SUBNAMES.length;
		for (int meta = 0; meta < count; meta++)
		{
			Object source = BlockCrateMetal.SUBSOURCES[meta];
			if (source == null) { continue; }
			registerCrateRecipe(new ItemStack(ModBlocks.crate_metal, 1, meta), source);
		}

		count = BlockCrateGem.SUBNAMES.length;
		for (int meta = 0; meta < count; meta++)
		{
			Object source = BlockCrateGem.SUBSOURCES[meta];
			if (source == null) { continue; }
			registerCrateRecipe(new ItemStack(ModBlocks.crate_gem, 1, meta), source);
		}

		// Trunks

		MessageTrunkArmor.register();
		registerTrunkRecipe(new ItemStack(ModBlocks.trunk_armor), new ItemStack(Items.dye, 1, 0));
	}

	@Override public void post()
	{
	}











	public static Object[] buildBarrelRecipeMatrix(Object stack)
	{
		return new Object[]
		{
			"???",
			"CHG",
			"???",
			'?', stack,
			'C', new ItemStack(Blocks.hardened_clay),
			'G', new ItemStack(Blocks.glass),
			'H', new ItemStack(ContentManager.ModItems.part_hoop)
		};
	}

	public static void registerBarrelRecipe(ItemStack output, Object input)
	{
		GameRegUtil.recipeShaped(output, buildBarrelRecipeMatrix(input));
	}










	public static Object[] buildCrateRecipeMatrix(Object input)
	{
		return new Object[]
		{
			"???",
			"?#?",
			"???",
			'?', input,
			'#', new ItemStack(ContentManager.ModItems.part_nails, 1, 0)
		};
	}

	public static void registerCrateRecipe(ItemStack output, Object input)
	{
		GameRegUtil.recipeShaped(output, buildCrateRecipeMatrix(input));
	}























	public static Object[] buildTrunkRecipeMatrix(Object input)
	{
		return new Object[]
		{
			"?#?",
			"#C#",
			"?#?",
			'?', input,
			'#', "nuggetIron",
			'C', new ItemStack(Blocks.chest)
		};
	}

	public static void registerTrunkRecipe(ItemStack output, Object input)
	{
		GameRegUtil.recipeShaped(output, buildTrunkRecipeMatrix(input));
	}
}
