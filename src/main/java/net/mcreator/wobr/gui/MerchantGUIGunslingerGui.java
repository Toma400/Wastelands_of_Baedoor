
package net.mcreator.wobr.gui;

import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.IContainerFactory;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.network.PacketBuffer;
import net.minecraft.inventory.container.Slot;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Container;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.Minecraft;

import net.mcreator.wobr.procedures.GunMerchantTrade6Procedure;
import net.mcreator.wobr.procedures.GunMerchantTrade5Procedure;
import net.mcreator.wobr.procedures.GunMerchantTrade4Procedure;
import net.mcreator.wobr.procedures.GunMerchantTrade3Procedure;
import net.mcreator.wobr.procedures.GunMerchantTrade2Procedure;
import net.mcreator.wobr.procedures.GunMerchantTrade1Procedure;
import net.mcreator.wobr.procedures.ElytraShowingProcedure;
import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;

import java.util.function.Supplier;
import java.util.Map;
import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

import com.google.common.collect.ImmutableMap;

@WobrModElements.ModElement.Tag
public class MerchantGUIGunslingerGui extends WobrModElements.ModElement {
	public static HashMap guistate = new HashMap();
	private static ContainerType<GuiContainerMod> containerType = null;
	public MerchantGUIGunslingerGui(WobrModElements instance) {
		super(instance, 1551);
		elements.addNetworkMessage(ButtonPressedMessage.class, ButtonPressedMessage::buffer, ButtonPressedMessage::new,
				ButtonPressedMessage::handler);
		elements.addNetworkMessage(GUISlotChangedMessage.class, GUISlotChangedMessage::buffer, GUISlotChangedMessage::new,
				GUISlotChangedMessage::handler);
		containerType = new ContainerType<>(new GuiContainerModFactory());
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@OnlyIn(Dist.CLIENT)
	public void initElements() {
		DeferredWorkQueue.runLater(() -> ScreenManager.registerFactory(containerType, GuiWindow::new));
	}

	@SubscribeEvent
	public void registerContainer(RegistryEvent.Register<ContainerType<?>> event) {
		event.getRegistry().register(containerType.setRegistryName("merchant_gui_gunslinger"));
	}
	public static class GuiContainerModFactory implements IContainerFactory {
		public GuiContainerMod create(int id, PlayerInventory inv, PacketBuffer extraData) {
			return new GuiContainerMod(id, inv, extraData);
		}
	}

	public static class GuiContainerMod extends Container implements Supplier<Map<Integer, Slot>> {
		private World world;
		private PlayerEntity entity;
		private int x, y, z;
		private IItemHandler internal;
		private Map<Integer, Slot> customSlots = new HashMap<>();
		private boolean bound = false;
		public GuiContainerMod(int id, PlayerInventory inv, PacketBuffer extraData) {
			super(containerType, id);
			this.entity = inv.player;
			this.world = inv.player.world;
			this.internal = new ItemStackHandler(0);
			BlockPos pos = null;
			if (extraData != null) {
				pos = extraData.readBlockPos();
				this.x = pos.getX();
				this.y = pos.getY();
				this.z = pos.getZ();
			}
		}

		public Map<Integer, Slot> get() {
			return customSlots;
		}

		@Override
		public boolean canInteractWith(PlayerEntity player) {
			return true;
		}
	}

	@OnlyIn(Dist.CLIENT)
	public static class GuiWindow extends ContainerScreen<GuiContainerMod> {
		private World world;
		private int x, y, z;
		private PlayerEntity entity;
		public GuiWindow(GuiContainerMod container, PlayerInventory inventory, ITextComponent text) {
			super(container, inventory, text);
			this.world = container.world;
			this.x = container.x;
			this.y = container.y;
			this.z = container.z;
			this.entity = container.entity;
			this.xSize = 150;
			this.ySize = 230;
		}

		@Override
		public void render(int mouseX, int mouseY, float partialTicks) {
			this.renderBackground();
			super.render(mouseX, mouseY, partialTicks);
			this.renderHoveredToolTip(mouseX, mouseY);
		}

		@Override
		protected void drawGuiContainerBackgroundLayer(float partialTicks, int gx, int gy) {
			RenderSystem.color4f(1, 1, 1, 1);
			RenderSystem.enableBlend();
			RenderSystem.defaultBlendFunc();
			Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("wobr:textures/merchant_trading_background.png"));
			this.blit(this.guiLeft + 0, this.guiTop + 63, 0, 0, 150, 150, 150, 150);
			Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("wobr:textures/scheme_crocodile_revolver.png"));
			this.blit(this.guiLeft + 16, this.guiTop + 128, 0, 0, 16, 16, 16, 16);
			Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("wobr:textures/scheme_sand_wanderer.png"));
			this.blit(this.guiLeft + 80, this.guiTop + 80, 0, 0, 16, 16, 16, 16);
			Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("wobr:textures/light_block.png"));
			this.blit(this.guiLeft + 80, this.guiTop + 81, 0, 0, 16, 16, 16, 16);
			Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("wobr:textures/scheme_long_revolver.png"));
			this.blit(this.guiLeft + 16, this.guiTop + 80, 0, 0, 16, 16, 16, 16);
			Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("wobr:textures/scheme_windsweeper.png"));
			this.blit(this.guiLeft + 16, this.guiTop + 104, 0, 0, 16, 16, 16, 16);
			Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("wobr:textures/light_block.png"));
			this.blit(this.guiLeft + 80, this.guiTop + 129, 0, 0, 16, 16, 16, 16);
			Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("wobr:textures/light_block.png"));
			this.blit(this.guiLeft + 80, this.guiTop + 105, 0, 0, 16, 16, 16, 16);
			Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("wobr:textures/light_block.png"));
			this.blit(this.guiLeft + 16, this.guiTop + 129, 0, 0, 16, 16, 16, 16);
			if (ElytraShowingProcedure.executeProcedure(ImmutableMap.of("entity", entity))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("wobr:textures/elytra.png"));
				this.blit(this.guiLeft + 80, this.guiTop + 176, 0, 0, 16, 16, 16, 16);
			}
			Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("wobr:textures/ancient_debris.png"));
			this.blit(this.guiLeft + 16, this.guiTop + 176, 0, 0, 16, 16, 16, 16);
			Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("wobr:textures/merchant_trading_background_price.png"));
			this.blit(this.guiLeft + 0, this.guiTop + 0, 0, 0, 150, 59, 150, 59);
			Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("wobr:textures/diamond.png"));
			this.blit(this.guiLeft + 15, this.guiTop + 20, 0, 0, 16, 16, 16, 16);
			Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("wobr:textures/diamond.png"));
			this.blit(this.guiLeft + 111, this.guiTop + 20, 0, 0, 16, 16, 16, 16);
			RenderSystem.disableBlend();
		}

		@Override
		public boolean keyPressed(int key, int b, int c) {
			if (key == 256) {
				this.minecraft.player.closeScreen();
				return true;
			}
			return super.keyPressed(key, b, c);
		}

		@Override
		public void tick() {
			super.tick();
		}

		@Override
		protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
			this.font.drawString("5", 54, 83, -16734040);
			this.font.drawString("7", 54, 107, -16734040);
			this.font.drawString("12", 51, 131, -16734040);
			this.font.drawString("2", 123, 83, -16734040);
			this.font.drawString("27", 51, 179, -16734040);
			this.font.drawString("9(1*)", 113, 179, -16734040);
			this.font.drawString("x 1", 40, 24, -16734040);
			this.font.drawString("9 x", 87, 24, -16734040);
		}

		@Override
		public void removed() {
			super.removed();
			Minecraft.getInstance().keyboardListener.enableRepeatEvents(false);
		}

		@Override
		public void init(Minecraft minecraft, int width, int height) {
			super.init(minecraft, width, height);
			minecraft.keyboardListener.enableRepeatEvents(true);
			this.addButton(new Button(this.guiLeft + 39, this.guiTop + 126, 7, 20, "�", e -> {
				if (true) {
					WobrMod.PACKET_HANDLER.sendToServer(new ButtonPressedMessage(0, x, y, z));
					handleButtonAction(entity, 0, x, y, z);
				}
			}));
			this.addButton(new Button(this.guiLeft + 39, this.guiTop + 150, 7, 20, "�", e -> {
				if (true) {
					WobrMod.PACKET_HANDLER.sendToServer(new ButtonPressedMessage(1, x, y, z));
					handleButtonAction(entity, 1, x, y, z);
				}
			}));
			this.addButton(new Button(this.guiLeft + 39, this.guiTop + 102, 7, 20, "�", e -> {
				if (true) {
					WobrMod.PACKET_HANDLER.sendToServer(new ButtonPressedMessage(2, x, y, z));
					handleButtonAction(entity, 2, x, y, z);
				}
			}));
			this.addButton(new Button(this.guiLeft + 39, this.guiTop + 174, 7, 20, "�", e -> {
				if (true) {
					WobrMod.PACKET_HANDLER.sendToServer(new ButtonPressedMessage(3, x, y, z));
					handleButtonAction(entity, 3, x, y, z);
				}
			}));
			this.addButton(new Button(this.guiLeft + 39, this.guiTop + 78, 7, 20, "�", e -> {
				if (true) {
					WobrMod.PACKET_HANDLER.sendToServer(new ButtonPressedMessage(4, x, y, z));
					handleButtonAction(entity, 4, x, y, z);
				}
			}));
			this.addButton(new Button(this.guiLeft + 103, this.guiTop + 78, 7, 20, "�", e -> {
				if (true) {
					WobrMod.PACKET_HANDLER.sendToServer(new ButtonPressedMessage(5, x, y, z));
					handleButtonAction(entity, 5, x, y, z);
				}
			}));
			this.addButton(new Button(this.guiLeft + 103, this.guiTop + 102, 7, 20, "�", e -> {
				if (true) {
					WobrMod.PACKET_HANDLER.sendToServer(new ButtonPressedMessage(6, x, y, z));
					handleButtonAction(entity, 6, x, y, z);
				}
			}));
			this.addButton(new Button(this.guiLeft + 103, this.guiTop + 126, 7, 20, "�", e -> {
				if (true) {
					WobrMod.PACKET_HANDLER.sendToServer(new ButtonPressedMessage(7, x, y, z));
					handleButtonAction(entity, 7, x, y, z);
				}
			}));
			this.addButton(new Button(this.guiLeft + 103, this.guiTop + 150, 7, 20, "�", e -> {
				if (true) {
					WobrMod.PACKET_HANDLER.sendToServer(new ButtonPressedMessage(8, x, y, z));
					handleButtonAction(entity, 8, x, y, z);
				}
			}));
			this.addButton(new Button(this.guiLeft + 103, this.guiTop + 174, 7, 20, "�", e -> {
				if (true) {
					WobrMod.PACKET_HANDLER.sendToServer(new ButtonPressedMessage(9, x, y, z));
					handleButtonAction(entity, 9, x, y, z);
				}
			}));
		}
	}

	public static class ButtonPressedMessage {
		int buttonID, x, y, z;
		public ButtonPressedMessage(PacketBuffer buffer) {
			this.buttonID = buffer.readInt();
			this.x = buffer.readInt();
			this.y = buffer.readInt();
			this.z = buffer.readInt();
		}

		public ButtonPressedMessage(int buttonID, int x, int y, int z) {
			this.buttonID = buttonID;
			this.x = x;
			this.y = y;
			this.z = z;
		}

		public static void buffer(ButtonPressedMessage message, PacketBuffer buffer) {
			buffer.writeInt(message.buttonID);
			buffer.writeInt(message.x);
			buffer.writeInt(message.y);
			buffer.writeInt(message.z);
		}

		public static void handler(ButtonPressedMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				PlayerEntity entity = context.getSender();
				int buttonID = message.buttonID;
				int x = message.x;
				int y = message.y;
				int z = message.z;
				handleButtonAction(entity, buttonID, x, y, z);
			});
			context.setPacketHandled(true);
		}
	}

	public static class GUISlotChangedMessage {
		int slotID, x, y, z, changeType, meta;
		public GUISlotChangedMessage(int slotID, int x, int y, int z, int changeType, int meta) {
			this.slotID = slotID;
			this.x = x;
			this.y = y;
			this.z = z;
			this.changeType = changeType;
			this.meta = meta;
		}

		public GUISlotChangedMessage(PacketBuffer buffer) {
			this.slotID = buffer.readInt();
			this.x = buffer.readInt();
			this.y = buffer.readInt();
			this.z = buffer.readInt();
			this.changeType = buffer.readInt();
			this.meta = buffer.readInt();
		}

		public static void buffer(GUISlotChangedMessage message, PacketBuffer buffer) {
			buffer.writeInt(message.slotID);
			buffer.writeInt(message.x);
			buffer.writeInt(message.y);
			buffer.writeInt(message.z);
			buffer.writeInt(message.changeType);
			buffer.writeInt(message.meta);
		}

		public static void handler(GUISlotChangedMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				PlayerEntity entity = context.getSender();
				int slotID = message.slotID;
				int changeType = message.changeType;
				int meta = message.meta;
				int x = message.x;
				int y = message.y;
				int z = message.z;
				handleSlotAction(entity, slotID, changeType, meta, x, y, z);
			});
			context.setPacketHandled(true);
		}
	}
	private static void handleButtonAction(PlayerEntity entity, int buttonID, int x, int y, int z) {
		World world = entity.world;
		// security measure to prevent arbitrary chunk generation
		if (!world.isBlockLoaded(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				GunMerchantTrade3Procedure.executeProcedure($_dependencies);
			}
		}
		if (buttonID == 2) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				GunMerchantTrade2Procedure.executeProcedure($_dependencies);
			}
		}
		if (buttonID == 3) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				GunMerchantTrade5Procedure.executeProcedure($_dependencies);
			}
		}
		if (buttonID == 4) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				GunMerchantTrade1Procedure.executeProcedure($_dependencies);
			}
		}
		if (buttonID == 5) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				GunMerchantTrade4Procedure.executeProcedure($_dependencies);
			}
		}
		if (buttonID == 9) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				GunMerchantTrade6Procedure.executeProcedure($_dependencies);
			}
		}
	}

	private static void handleSlotAction(PlayerEntity entity, int slotID, int changeType, int meta, int x, int y, int z) {
		World world = entity.world;
		// security measure to prevent arbitrary chunk generation
		if (!world.isBlockLoaded(new BlockPos(x, y, z)))
			return;
	}
}
