
package net.mcreator.wobr.gui.overlay;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.Minecraft;

import net.mcreator.wobr.procedures.ExperienceGUIProcedure;
import net.mcreator.wobr.WobrModVariables;
import net.mcreator.wobr.WobrModElements;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.platform.GlStateManager;

import com.google.common.collect.ImmutableMap;

@WobrModElements.ModElement.Tag
public class ExperienceOverlayOverlay extends WobrModElements.ModElement {
	public ExperienceOverlayOverlay(WobrModElements instance) {
		super(instance, 1138);
	}

	@Override
	public void initElements() {
		MinecraftForge.EVENT_BUS.register(this);
	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void eventHandler(RenderGameOverlayEvent.Post event) {
		if (event.getType() == RenderGameOverlayEvent.ElementType.HELMET) {
			int w = event.getWindow().getScaledWidth();
			int h = event.getWindow().getScaledHeight();
			int posX = w / 2;
			int posY = h / 2;
			World _world = null;
			double _x = 0;
			double _y = 0;
			double _z = 0;
			PlayerEntity entity = Minecraft.getInstance().player;
			if (entity != null) {
				_world = entity.world;
				_x = entity.getPosX();
				_y = entity.getPosY();
				_z = entity.getPosZ();
			}
			World world = _world;
			double x = _x;
			double y = _y;
			double z = _z;
			RenderSystem.disableDepthTest();
			RenderSystem.depthMask(false);
			RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA,
					GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
			RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
			RenderSystem.disableAlphaTest();
			if (ExperienceGUIProcedure.executeProcedure(ImmutableMap.of("world", world))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("wobr:textures/xp_gun.png"));
				Minecraft.getInstance().ingameGUI.blit(posX + -38, posY + -116, 0, 0, 10, 10, 10, 10);
				Minecraft
						.getInstance().fontRenderer
								.drawString(
										"" + ((entity.getCapability(WobrModVariables.PLAYER_VARIABLES_CAPABILITY, null)
												.orElse(new WobrModVariables.PlayerVariables())).Gun_Experience) + "",
										posX + -25, posY + -115, -9747702);
				Minecraft
						.getInstance().fontRenderer
								.drawString(
										"" + ((entity.getCapability(WobrModVariables.PLAYER_VARIABLES_CAPABILITY, null)
												.orElse(new WobrModVariables.PlayerVariables())).Sabre_Experience) + "",
										posX + 26, posY + -115, -12183425);
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("wobr:textures/xp_sabre.png"));
				Minecraft.getInstance().ingameGUI.blit(posX + 13, posY + -115, 0, 0, 10, 10, 10, 10);
			}
			RenderSystem.depthMask(true);
			RenderSystem.enableDepthTest();
			RenderSystem.enableAlphaTest();
			RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		}
	}
}
