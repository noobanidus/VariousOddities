package com.lying.variousoddities.init;

import java.util.ArrayList;
import java.util.List;

import com.lying.variousoddities.item.ItemHeldFlag;
import com.lying.variousoddities.item.ItemMossBottle;
import com.lying.variousoddities.item.ItemSpellContainer;
import com.lying.variousoddities.item.VOItemGroup;
import com.lying.variousoddities.reference.Reference;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = Reference.ModInfo.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class VOItems
{
	private static final List<Item> ITEMS = new ArrayList<>();
	private static final List<BlockItem> BLOCK_ITEMS = new ArrayList<>();
	
	// Mob module
	public static final Item SCALE_KOBOLD	= register("kobold_scale", new Item(new Item.Properties().group(VOItemGroup.LOOT)));
	public static final Item MOSS_BOTTLE	= register("moss_bottle", new ItemMossBottle(new Item.Properties().group(VOItemGroup.LOOT)));
	
	// Block items
	public static final BlockItem DRAFTING_TABLE	= registerBlock("drafting_table", VOBlocks.TABLE_DRAFTING, VOItemGroup.LOOT);
	public static final BlockItem EGG_KOBOLD		= registerBlock("kobold_egg", VOBlocks.EGG_KOBOLD);
	public static final BlockItem EGG_KOBOLD_INERT	= registerBlock("inert_kobold_egg", VOBlocks.EGG_KOBOLD_INERT);
	public static final BlockItem MOSS_BLOCK		= registerBlock("moss_block", VOBlocks.MOSS_BLOCK);
	public static final BlockItem LAYER_SCALE		= registerBlock("scale_layer", VOBlocks.LAYER_SCALE);
	
	public static Item register(String nameIn, Item itemIn)
	{
		itemIn.setRegistryName(Reference.ModInfo.MOD_PREFIX+nameIn);
		ITEMS.add(itemIn);
		return itemIn;
	}
	
	public static BlockItem registerBlock(String nameIn, Block blockIn)
	{
		return registerBlock(nameIn, blockIn, VOItemGroup.BLOCKS);
	}
	
	public static BlockItem registerBlock(String nameIn, Block blockIn, ItemGroup group)
	{
		return registerBlock(nameIn, new BlockItem(blockIn, new Item.Properties().group(group)));
	}
	
	public static BlockItem registerBlock(String nameIn, BlockItem itemIn)
	{
		itemIn.setRegistryName(Reference.ModInfo.MOD_PREFIX+nameIn);
		BLOCK_ITEMS.add(itemIn);
		return itemIn;
	}
	
    @SubscribeEvent
    public static void onItemsRegistry(final RegistryEvent.Register<Item> itemRegistryEvent)
    {
    	IForgeRegistry<Item> registry = itemRegistryEvent.getRegistry();
    	ItemHeldFlag.registerSubItems();
    	ItemSpellContainer.registerSubItems(registry);
    	
    	registry.registerAll(ITEMS.toArray(new Item[0]));
    	registry.registerAll(BLOCK_ITEMS.toArray(new Item[0]));
    }
}
