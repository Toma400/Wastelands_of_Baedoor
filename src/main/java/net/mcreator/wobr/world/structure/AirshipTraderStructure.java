
package net.mcreator.wobr.world.structure;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Rotation;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Mirror;

import net.mcreator.wobr.procedures.AirshipTraderConditionProcedure;
import net.mcreator.wobr.WobrModElements;
import net.mcreator.wobr.Config;

import java.util.Random;

import com.google.common.collect.ImmutableMap;

@WobrModElements.ModElement.Tag
public class AirshipTraderStructure extends WobrModElements.ModElement {
	private static final Feature<NoFeatureConfig> feature = new Feature<NoFeatureConfig>(NoFeatureConfig::deserialize) {
		@Override
		public boolean place(IWorld world, ChunkGenerator generator, Random random, BlockPos pos, NoFeatureConfig config) {
			int ci = (pos.getX() >> 4) << 4;
			int ck = (pos.getZ() >> 4) << 4;
			DimensionType dimensionType = world.getDimension().getType();
			boolean dimensionCriteria = false;
			if (dimensionType == DimensionType.OVERWORLD)
				dimensionCriteria = true;
			if (dimensionType == DimensionType.THE_END)
				dimensionCriteria = true;
			if (!dimensionCriteria)
				return false;
			if ((random.nextInt(1000000) + 1) <= Config.AIRSHIP_MERCHANT_CHANCE.get()) { // config
				int count = random.nextInt(1) + 1;
				for (int a = 0; a < count; a++) {
					int i = ci + random.nextInt(16);
					int k = ck + random.nextInt(16);
					int j = world.getHeight(Heightmap.Type.WORLD_SURFACE_WG, i, k);
					j += random.nextInt(50) + 16;
					Rotation rotation = Rotation.values()[random.nextInt(3)];
					Mirror mirror = Mirror.values()[random.nextInt(2)];
					BlockPos spawnTo = new BlockPos(i + 0, j + 3, k + 0);
					int x = spawnTo.getX();
					int y = spawnTo.getY();
					int z = spawnTo.getZ();
					if (!AirshipTraderConditionProcedure.executeProcedure(ImmutableMap.of("world", world)))
						continue;
					Template template = ((ServerWorld) world.getWorld()).getSaveHandler().getStructureTemplateManager()
							.getTemplateDefaulted(new ResourceLocation("wobr", "spawn_entity_airship_trader"));
					if (template == null)
						return false;
					template.addBlocksToWorld(world, spawnTo, new PlacementSettings().setRotation(rotation).setRandom(random).setMirror(mirror)
							.addProcessor(BlockIgnoreStructureProcessor.AIR_AND_STRUCTURE_BLOCK).setChunk(null).setIgnoreEntities(false));
				}
			}
			return true;
		}
	};
	public AirshipTraderStructure(WobrModElements instance) {
		super(instance, 1597);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@SubscribeEvent
	public void registerFeature(RegistryEvent.Register<Feature<?>> event) {
		event.getRegistry().register(feature.setRegistryName("airship_trader"));
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
			biome.addFeature(GenerationStage.Decoration.RAW_GENERATION, feature.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG)
					.withPlacement(Placement.NOPE.configure(IPlacementConfig.NO_PLACEMENT_CONFIG)));
		}
	}
}
