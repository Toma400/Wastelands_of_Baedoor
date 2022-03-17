
package net.mcreator.wobr.gui;

import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.IContainerFactory;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.common.MinecraftForge;
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

import net.mcreator.wobr.procedures.OrmathTrade6Procedure;
import net.mcreator.wobr.procedures.OrmathTrade5Procedure;
import net.mcreator.wobr.procedures.OrmathTrade2Procedure;
import net.mcreator.wobr.procedures.OrmathTrade1Procedure;
import net.mcreator.wobr.procedures.CoinIteratorProcedure;
import net.mcreator.wobr.WobrModVariables;
import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.WobrMod;

import java.util.function.Supplier;
import java.util.Map;
import java.util.HashMap;

import com.mojang.blaze3d.systems.RenderSystem;

@WobrModElements.ModElement.Tag
public class VillagerTradeGUI1Gui extends WobrModElements.ModElement {
	public static HashMap guistate = new HashMap();
	private static ContainerType<GuiContainerMod> containerType = null;
	public VillagerTradeGUI1Gui(WobrModElements instance) {
		super(instance, 1219);
		elements.addNetworkMessage(ButtonPressedMessage.class, ButtonPressedMessage::buffer, ButtonPressedMessage::new,
				ButtonPressedMessage::handler);
		elements.addNetworkMessage(GUISlotChangedMessage.class, GUISlotChangedMessage::buffer, GUISlotChangedMessage::new,
				GUISlotChangedMessage::handler);
		containerType = new ContainerType<>(new GuiContainerModFactory());
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
		MinecraftForge.EVENT_BUS.register(this);
	}

	@OnlyIn(Dist.CLIENT)
	public void initElements() {
		DeferredWorkQueue.runLater(() -> ScreenManager.registerFactory(containerType, GuiWindow::new));
	}

	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent event) {
		PlayerEntity entity = event.player;
		if (event.phase == TickEvent.Phase.END && entity.openContainer instanceof GuiContainerMod) {
			World world = entity.world;
			double x = entity.getPosX();
			double y = entity.getPosY();
			double z = entity.getPosZ();
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				$_dependencies.put("world", world);
				CoinIteratorProcedure.executeProcedure($_dependencies);
			}
		}
	}

	@SubscribeEvent
	public void registerContainer(RegistryEvent.Register<ContainerType<?>> event) {
		event.getRegistry().register(containerType.setRegistryName("villager_trade_gui_1"));
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
			this.xSize = 176;
			this.ySize = 166;
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
			Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("wobr:textures/ormath_trading_background.png"));
			this.blit(this.guiLeft + 0, this.guiTop + 0, 0, 0, 176, 166, 176, 166);
			Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("wobr:textures/baedoor_funt_2.png"));
			this.blit(this.guiLeft + 127, this.guiTop + 41, 0, 0, 16, 16, 16, 16);
			Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("wobr:textures/trade_chicken.png"));
			this.blit(this.guiLeft + 42, this.guiTop + 40, 0, 0, 16, 16, 16, 16);
			Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("wobr:textures/trade_cooked_tropical_fish.png"));
			this.blit(this.guiLeft + 42, this.guiTop + 64, 0, 0, 16, 16, 16, 16);
			Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("wobr:textures/baedoor_funt_2.png"));
			this.blit(this.guiLeft + 127, this.guiTop + 64, 0, 0, 16, 16, 16, 16);
			Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("wobr:textures/trade_clay_ball.png"));
			this.blit(this.guiLeft + 127, this.guiTop + 86, 0, 0, 16, 16, 16, 16);
			Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("wobr:textures/baedoor_funt_2.png"));
			this.blit(this.guiLeft + 42, this.guiTop + 87, 0, 0, 16, 16, 16, 16);
			Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("wobr:textures/trade_jungle.png"));
			this.blit(this.guiLeft + 127, this.guiTop + 110, 0, 0, 16, 16, 16, 16);
			Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("wobr:textures/baedoor_funt_2.png"));
			this.blit(this.guiLeft + 42, this.guiTop + 109, 0, 0, 16, 16, 16, 16);
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
			this.font.drawString("1", 115, 44, -16737895);
			this.font.drawString("16", 30, 43, -16737895);
			this.font.drawString("8", 34, 67, -16737895);
			this.font.drawString("1", 116, 67, -16737895);
			this.font.drawString("32", 113, 90, -16737895);
			this.font.drawString("1", 36, 90, -16737895);
			this.font.drawString("4", 116, 112, -16737895);
			this.font.drawString("1", 36, 112, -16737895);
			this.font.drawString("" + ((entity.getCapability(WobrModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new WobrModVariables.PlayerVariables())).Ormath_Reputation) + "", 75, 150, -9347962);
			this.font.drawString("" + ((entity.getCapability(WobrModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new WobrModVariables.PlayerVariables())).Coins) + "", 79, 20, -6057201);
			this.font.drawString("Reputation", 62, 137, -7572572);
			this.font.drawString("Coins", 75, 8, -6057201);
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
			this.addButton(new Button(this.guiLeft + 76, this.guiTop + 38, 23, 20, "->", e -> {
				if (true) {
					WobrMod.PACKET_HANDLER.sendToServer(new ButtonPressedMessage(0, x, y, z));
					handleButtonAction(entity, 0, x, y, z);
				}
			}));
			this.addButton(new Button(this.guiLeft + 76, this.guiTop + 62, 23, 20, "->", e -> {
				if (true) {
					WobrMod.PACKET_HANDLER.sendToServer(new ButtonPressedMessage(1, x, y, z));
					handleButtonAction(entity, 1, x, y, z);
				}
			}));
			this.addButton(new Button(this.guiLeft + 76, this.guiTop + 85, 23, 20, "->", e -> {
				if (true) {
					WobrMod.PACKET_HANDLER.sendToServer(new ButtonPressedMessage(2, x, y, z));
					handleButtonAction(entity, 2, x, y, z);
				}
			}));
			this.addButton(new Button(this.guiLeft + 76, this.guiTop + 108, 23, 20, "->", e -> {
				if (true) {
					WobrMod.PACKET_HANDLER.sendToServer(new ButtonPressedMessage(3, x, y, z));
					handleButtonAction(entity, 3, x, y, z);
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
				OrmathTrade1Procedure.executeProcedure($_dependencies);
			}
		}
		if (buttonID == 1) {
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				$_dependencies.put("x", x);
				$_dependencies.put("y", y);
				$_dependencies.put("z", z);
				$_dependencies.put("world", world);
				OrmathTrade2Procedure.executeProcedure($_dependencies);
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
				OrmathTrade5Procedure.executeProcedure($_dependencies);
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
				OrmathTrade6Procedure.executeProcedure($_dependencies);
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
