package net.mcreator.wobr;

import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.common.brewing.IBrewingRecipe;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;

import net.minecraft.potion.Potions;
import net.minecraft.potion.PotionUtils;
import net.minecraft.item.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;

import net.mcreator.wobr.item.StarofRisingDayItem;

@WobrModElements.ModElement.Tag
public class RisingPotionRecipe2BrewingRecipe extends WobrModElements.ModElement {
    public RisingPotionRecipe2BrewingRecipe(WobrModElements instance) {
        super(instance, 1747);
    }

    @Override
    public void init(FMLCommonSetupEvent event) {
        BrewingRecipeRegistry.addRecipe(new CustomBrewingRecipe());
    }

    public static class CustomBrewingRecipe implements IBrewingRecipe {
        @Override
        public boolean isInput(ItemStack input) {
            Item inputItem = input.getItem();
            return (inputItem == Items.POTION || inputItem == Items.SPLASH_POTION || inputItem == Items.LINGERING_POTION)
                    && PotionUtils.getPotionFromItem(input) == Potions.THICK;
        }

        @Override
        public boolean isIngredient(ItemStack ingredient) {
            return ingredient.getItem() == StarofRisingDayItem.block;
        }

        @Override
        public ItemStack getOutput(ItemStack input, ItemStack ingredient) {
            if (isInput(input) && isIngredient(ingredient)) {
                return PotionUtils.addPotionToItemStack(new ItemStack(input.getItem()), Potions.MUNDANE);
            }
            return ItemStack.EMPTY;
        }
    }
}