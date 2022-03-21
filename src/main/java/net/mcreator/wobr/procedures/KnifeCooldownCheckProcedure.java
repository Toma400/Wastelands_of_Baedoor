package net.mcreator.wobr.procedures;

import net.minecraft.item.ItemStack;
import net.minecraft.entity.Entity;

import net.mcreator.wobr.WobrModElements;

import java.util.Map;
import java.util.HashMap;

@WobrModElements.ModElement.Tag
public class KnifeCooldownCheckProcedure extends WobrModElements.ModElement {
	public KnifeCooldownCheckProcedure(WobrModElements instance) {
		super(instance, 853);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				System.err.println("Failed to load dependency entity for procedure KnifeCooldownCheck!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				System.err.println("Failed to load dependency itemstack for procedure KnifeCooldownCheck!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		if ((((itemstack).getOrCreateTag().getBoolean("Cooldown")) == (true))) {
			entity.getPersistentData().putString("Message", "     Knife isn't ready to sneak attack yet");
		} else {
			entity.getPersistentData().putString("Message", "         Knife ready for sneak attack!");
		}
		{
			Map<String, Object> $_dependencies = new HashMap<>();
			$_dependencies.put("entity", entity);
			MessageManagerProcedure.executeProcedure($_dependencies);
		}
	}
}
