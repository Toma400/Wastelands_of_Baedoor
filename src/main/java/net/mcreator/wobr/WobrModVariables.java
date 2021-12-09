package net.mcreator.wobr;

import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.Capability;

import net.minecraft.world.storage.WorldSavedData;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.IWorld;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Direction;
import net.minecraft.network.PacketBuffer;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.client.Minecraft;

import java.util.function.Supplier;

import java.io.File;

public class WobrModVariables {
	public WobrModVariables(WobrModElements elements) {
		elements.addNetworkMessage(WorldSavedDataSyncMessage.class, WorldSavedDataSyncMessage::buffer, WorldSavedDataSyncMessage::new,
				WorldSavedDataSyncMessage::handler);
		elements.addNetworkMessage(PlayerVariablesSyncMessage.class, PlayerVariablesSyncMessage::buffer, PlayerVariablesSyncMessage::new,
				PlayerVariablesSyncMessage::handler);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::init);
	}

	private void init(FMLCommonSetupEvent event) {
		CapabilityManager.INSTANCE.register(PlayerVariables.class, new PlayerVariablesStorage(), PlayerVariables::new);
	}
	public static double Growth_Stadium = 0.0;
	public static boolean Light_Blocks_Used = false;
	public static double For_Random_Uses = 0;
	public static File config = new File("");
	@SubscribeEvent
	public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
		if (!event.getPlayer().world.isRemote) {
			WorldSavedData mapdata = MapVariables.get(event.getPlayer().world);
			WorldSavedData worlddata = WorldVariables.get(event.getPlayer().world);
			if (mapdata != null)
				WobrMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) event.getPlayer()),
						new WorldSavedDataSyncMessage(0, mapdata));
			if (worlddata != null)
				WobrMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) event.getPlayer()),
						new WorldSavedDataSyncMessage(1, worlddata));
		}
	}

	@SubscribeEvent
	public void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
		if (!event.getPlayer().world.isRemote) {
			WorldSavedData worlddata = WorldVariables.get(event.getPlayer().world);
			if (worlddata != null)
				WobrMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) event.getPlayer()),
						new WorldSavedDataSyncMessage(1, worlddata));
		}
	}
	public static class WorldVariables extends WorldSavedData {
		public static final String DATA_NAME = "wobr_worldvars";
		public WorldVariables() {
			super(DATA_NAME);
		}

		public WorldVariables(String s) {
			super(s);
		}

		@Override
		public void read(CompoundNBT nbt) {
		}

		@Override
		public CompoundNBT write(CompoundNBT nbt) {
			return nbt;
		}

		public void syncData(IWorld world) {
			this.markDirty();
			if (!world.getWorld().isRemote)
				WobrMod.PACKET_HANDLER.send(PacketDistributor.DIMENSION.with(world.getWorld().dimension::getType),
						new WorldSavedDataSyncMessage(1, this));
		}
		static WorldVariables clientSide = new WorldVariables();
		public static WorldVariables get(IWorld world) {
			if (world.getWorld() instanceof ServerWorld) {
				return ((ServerWorld) world.getWorld()).getSavedData().getOrCreate(WorldVariables::new, DATA_NAME);
			} else {
				return clientSide;
			}
		}
	}

	public static class MapVariables extends WorldSavedData {
		public static final String DATA_NAME = "wobr_mapvars";
		public boolean Merchant = false;
		public boolean KF_Av_Pigman = false;
		public boolean KF_Av_Villager = true;
		public double KF_Av_Distance = 50.0;
		public boolean KF_Str_Bandit_Airship = true;
		public boolean KF_Str_Mage_Airship = true;
		public boolean KF_Str_Trader_Airship = true;
		public boolean KF_Str_Military_Airship = true;
		public boolean KF_Str_Jungle_Airship = true;
		public boolean KF_Str_Airship_General = false;
		public boolean KF_Wp_Gun_Enabled = true;
		public double KF_Wp_Gun_Dmg = 1.0;
		public boolean KF_Drop_Glister = true;
		public boolean KF_Drop_Essence = true;
		public boolean KF_Ent_Wind_Spirit = true;
		public boolean KF_Ent_Orm_Raider = false;
		public boolean KF_Xp_Structures = false;
		public boolean KF_Xp_Developer = false;
		public boolean KF_Xp_Ticker = false;
		public boolean KF_Ent_Merchant = true;
		public double KF_Drop_Glister_A = 2.0;
		public double KF_Drop_Essence_A = 8.0;
		public double KF_Ent_Merchant_A = 25.0;
		public boolean KF_Is_Config_Here = false;
		public MapVariables() {
			super(DATA_NAME);
		}

		public MapVariables(String s) {
			super(s);
		}

		@Override
		public void read(CompoundNBT nbt) {
			Merchant = nbt.getBoolean("Merchant");
			KF_Av_Pigman = nbt.getBoolean("KF_Av_Pigman");
			KF_Av_Villager = nbt.getBoolean("KF_Av_Villager");
			KF_Av_Distance = nbt.getDouble("KF_Av_Distance");
			KF_Str_Bandit_Airship = nbt.getBoolean("KF_Str_Bandit_Airship");
			KF_Str_Mage_Airship = nbt.getBoolean("KF_Str_Mage_Airship");
			KF_Str_Trader_Airship = nbt.getBoolean("KF_Str_Trader_Airship");
			KF_Str_Military_Airship = nbt.getBoolean("KF_Str_Military_Airship");
			KF_Str_Jungle_Airship = nbt.getBoolean("KF_Str_Jungle_Airship");
			KF_Str_Airship_General = nbt.getBoolean("KF_Str_Airship_General");
			KF_Wp_Gun_Enabled = nbt.getBoolean("KF_Wp_Gun_Enabled");
			KF_Wp_Gun_Dmg = nbt.getDouble("KF_Wp_Gun_Dmg");
			KF_Drop_Glister = nbt.getBoolean("KF_Drop_Glister");
			KF_Drop_Essence = nbt.getBoolean("KF_Drop_Essence");
			KF_Ent_Wind_Spirit = nbt.getBoolean("KF_Ent_Wind_Spirit");
			KF_Ent_Orm_Raider = nbt.getBoolean("KF_Ent_Orm_Raider");
			KF_Xp_Structures = nbt.getBoolean("KF_Xp_Structures");
			KF_Xp_Developer = nbt.getBoolean("KF_Xp_Developer");
			KF_Xp_Ticker = nbt.getBoolean("KF_Xp_Ticker");
			KF_Ent_Merchant = nbt.getBoolean("KF_Ent_Merchant");
			KF_Drop_Glister_A = nbt.getDouble("KF_Drop_Glister_A");
			KF_Drop_Essence_A = nbt.getDouble("KF_Drop_Essence_A");
			KF_Ent_Merchant_A = nbt.getDouble("KF_Ent_Merchant_A");
			KF_Is_Config_Here = nbt.getBoolean("KF_Is_Config_Here");
		}

		@Override
		public CompoundNBT write(CompoundNBT nbt) {
			nbt.putBoolean("Merchant", Merchant);
			nbt.putBoolean("KF_Av_Pigman", KF_Av_Pigman);
			nbt.putBoolean("KF_Av_Villager", KF_Av_Villager);
			nbt.putDouble("KF_Av_Distance", KF_Av_Distance);
			nbt.putBoolean("KF_Str_Bandit_Airship", KF_Str_Bandit_Airship);
			nbt.putBoolean("KF_Str_Mage_Airship", KF_Str_Mage_Airship);
			nbt.putBoolean("KF_Str_Trader_Airship", KF_Str_Trader_Airship);
			nbt.putBoolean("KF_Str_Military_Airship", KF_Str_Military_Airship);
			nbt.putBoolean("KF_Str_Jungle_Airship", KF_Str_Jungle_Airship);
			nbt.putBoolean("KF_Str_Airship_General", KF_Str_Airship_General);
			nbt.putBoolean("KF_Wp_Gun_Enabled", KF_Wp_Gun_Enabled);
			nbt.putDouble("KF_Wp_Gun_Dmg", KF_Wp_Gun_Dmg);
			nbt.putBoolean("KF_Drop_Glister", KF_Drop_Glister);
			nbt.putBoolean("KF_Drop_Essence", KF_Drop_Essence);
			nbt.putBoolean("KF_Ent_Wind_Spirit", KF_Ent_Wind_Spirit);
			nbt.putBoolean("KF_Ent_Orm_Raider", KF_Ent_Orm_Raider);
			nbt.putBoolean("KF_Xp_Structures", KF_Xp_Structures);
			nbt.putBoolean("KF_Xp_Developer", KF_Xp_Developer);
			nbt.putBoolean("KF_Xp_Ticker", KF_Xp_Ticker);
			nbt.putBoolean("KF_Ent_Merchant", KF_Ent_Merchant);
			nbt.putDouble("KF_Drop_Glister_A", KF_Drop_Glister_A);
			nbt.putDouble("KF_Drop_Essence_A", KF_Drop_Essence_A);
			nbt.putDouble("KF_Ent_Merchant_A", KF_Ent_Merchant_A);
			nbt.putBoolean("KF_Is_Config_Here", KF_Is_Config_Here);
			return nbt;
		}

		public void syncData(IWorld world) {
			this.markDirty();
			if (!world.getWorld().isRemote)
				WobrMod.PACKET_HANDLER.send(PacketDistributor.ALL.noArg(), new WorldSavedDataSyncMessage(0, this));
		}
		static MapVariables clientSide = new MapVariables();
		public static MapVariables get(IWorld world) {
			if (world.getWorld() instanceof ServerWorld) {
				return world.getWorld().getServer().getWorld(DimensionType.OVERWORLD).getSavedData().getOrCreate(MapVariables::new, DATA_NAME);
			} else {
				return clientSide;
			}
		}
	}

	public static class WorldSavedDataSyncMessage {
		public int type;
		public WorldSavedData data;
		public WorldSavedDataSyncMessage(PacketBuffer buffer) {
			this.type = buffer.readInt();
			this.data = this.type == 0 ? new MapVariables() : new WorldVariables();
			this.data.read(buffer.readCompoundTag());
		}

		public WorldSavedDataSyncMessage(int type, WorldSavedData data) {
			this.type = type;
			this.data = data;
		}

		public static void buffer(WorldSavedDataSyncMessage message, PacketBuffer buffer) {
			buffer.writeInt(message.type);
			buffer.writeCompoundTag(message.data.write(new CompoundNBT()));
		}

		public static void handler(WorldSavedDataSyncMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				if (!context.getDirection().getReceptionSide().isServer()) {
					if (message.type == 0)
						MapVariables.clientSide = (MapVariables) message.data;
					else
						WorldVariables.clientSide = (WorldVariables) message.data;
				}
			});
			context.setPacketHandled(true);
		}
	}
	@CapabilityInject(PlayerVariables.class)
	public static Capability<PlayerVariables> PLAYER_VARIABLES_CAPABILITY = null;
	@SubscribeEvent
	public void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof PlayerEntity && !(event.getObject() instanceof FakePlayer))
			event.addCapability(new ResourceLocation("wobr", "player_variables"), new PlayerVariablesProvider());
	}
	private static class PlayerVariablesProvider implements ICapabilitySerializable<INBT> {
		private final LazyOptional<PlayerVariables> instance = LazyOptional.of(PLAYER_VARIABLES_CAPABILITY::getDefaultInstance);
		@Override
		public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
			return cap == PLAYER_VARIABLES_CAPABILITY ? instance.cast() : LazyOptional.empty();
		}

		@Override
		public INBT serializeNBT() {
			return PLAYER_VARIABLES_CAPABILITY.getStorage().writeNBT(PLAYER_VARIABLES_CAPABILITY, this.instance.orElseThrow(RuntimeException::new),
					null);
		}

		@Override
		public void deserializeNBT(INBT nbt) {
			PLAYER_VARIABLES_CAPABILITY.getStorage().readNBT(PLAYER_VARIABLES_CAPABILITY, this.instance.orElseThrow(RuntimeException::new), null,
					nbt);
		}
	}

	private static class PlayerVariablesStorage implements Capability.IStorage<PlayerVariables> {
		@Override
		public INBT writeNBT(Capability<PlayerVariables> capability, PlayerVariables instance, Direction side) {
			CompoundNBT nbt = new CompoundNBT();
			nbt.putBoolean("Message_Active", instance.Message_Active);
			nbt.putBoolean("Additional_Message_Active", instance.Additional_Message_Active);
			nbt.putDouble("Gun_Experience", instance.Gun_Experience);
			nbt.putDouble("Sabre_Experience", instance.Sabre_Experience);
			nbt.putDouble("Ormath_Reputation", instance.Ormath_Reputation);
			nbt.putDouble("Coins", instance.Coins);
			nbt.putDouble("Emeralds", instance.Emeralds);
			return nbt;
		}

		@Override
		public void readNBT(Capability<PlayerVariables> capability, PlayerVariables instance, Direction side, INBT inbt) {
			CompoundNBT nbt = (CompoundNBT) inbt;
			instance.Message_Active = nbt.getBoolean("Message_Active");
			instance.Additional_Message_Active = nbt.getBoolean("Additional_Message_Active");
			instance.Gun_Experience = nbt.getDouble("Gun_Experience");
			instance.Sabre_Experience = nbt.getDouble("Sabre_Experience");
			instance.Ormath_Reputation = nbt.getDouble("Ormath_Reputation");
			instance.Coins = nbt.getDouble("Coins");
			instance.Emeralds = nbt.getDouble("Emeralds");
		}
	}

	public static class PlayerVariables {
		public boolean Message_Active = false;
		public boolean Additional_Message_Active = false;
		public double Gun_Experience = 0;
		public double Sabre_Experience = 0;
		public double Ormath_Reputation = 0;
		public double Coins = 0;
		public double Emeralds = 0;
		public void syncPlayerVariables(Entity entity) {
			if (entity instanceof ServerPlayerEntity)
				WobrMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) entity), new PlayerVariablesSyncMessage(this));
		}
	}
	@SubscribeEvent
	public void onPlayerLoggedInSyncPlayerVariables(PlayerEvent.PlayerLoggedInEvent event) {
		if (!event.getPlayer().world.isRemote)
			((PlayerVariables) event.getPlayer().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()))
					.syncPlayerVariables(event.getPlayer());
	}

	@SubscribeEvent
	public void onPlayerRespawnedSyncPlayerVariables(PlayerEvent.PlayerRespawnEvent event) {
		if (!event.getPlayer().world.isRemote)
			((PlayerVariables) event.getPlayer().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()))
					.syncPlayerVariables(event.getPlayer());
	}

	@SubscribeEvent
	public void onPlayerChangedDimensionSyncPlayerVariables(PlayerEvent.PlayerChangedDimensionEvent event) {
		if (!event.getPlayer().world.isRemote)
			((PlayerVariables) event.getPlayer().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()))
					.syncPlayerVariables(event.getPlayer());
	}

	@SubscribeEvent
	public void clonePlayer(PlayerEvent.Clone event) {
		PlayerVariables original = ((PlayerVariables) event.getOriginal().getCapability(PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new PlayerVariables()));
		PlayerVariables clone = ((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()));
		clone.Message_Active = original.Message_Active;
		clone.Additional_Message_Active = original.Additional_Message_Active;
		clone.Gun_Experience = original.Gun_Experience;
		clone.Sabre_Experience = original.Sabre_Experience;
		clone.Ormath_Reputation = original.Ormath_Reputation;
		clone.Coins = original.Coins;
		clone.Emeralds = original.Emeralds;
		if (!event.isWasDeath()) {
		}
	}
	public static class PlayerVariablesSyncMessage {
		public PlayerVariables data;
		public PlayerVariablesSyncMessage(PacketBuffer buffer) {
			this.data = new PlayerVariables();
			new PlayerVariablesStorage().readNBT(null, this.data, null, buffer.readCompoundTag());
		}

		public PlayerVariablesSyncMessage(PlayerVariables data) {
			this.data = data;
		}

		public static void buffer(PlayerVariablesSyncMessage message, PacketBuffer buffer) {
			buffer.writeCompoundTag((CompoundNBT) new PlayerVariablesStorage().writeNBT(null, message.data, null));
		}

		public static void handler(PlayerVariablesSyncMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				if (!context.getDirection().getReceptionSide().isServer()) {
					PlayerVariables variables = ((PlayerVariables) Minecraft.getInstance().player.getCapability(PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new PlayerVariables()));
					variables.Message_Active = message.data.Message_Active;
					variables.Additional_Message_Active = message.data.Additional_Message_Active;
					variables.Gun_Experience = message.data.Gun_Experience;
					variables.Sabre_Experience = message.data.Sabre_Experience;
					variables.Ormath_Reputation = message.data.Ormath_Reputation;
					variables.Coins = message.data.Coins;
					variables.Emeralds = message.data.Emeralds;
				}
			});
			context.setPacketHandled(true);
		}
	}
}
