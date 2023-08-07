package net.rotgruengelb.clientcatcher;

import com.mojang.authlib.GameProfile;

import net.fabricmc.api.DedicatedServerModInitializer;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

import net.minecraft.command.argument.GameProfileArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.network.packet.c2s.play.CustomPayloadC2SPacket;
import net.minecraft.text.*;
import net.minecraft.util.Formatting;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.UUID;

import static net.minecraft.server.command.CommandManager.*;
import static com.mojang.brigadier.Command.SINGLE_SUCCESS;

public class ClientCatcher implements DedicatedServerModInitializer {
	public static final String MODID = "clientcatcher";
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	public static HashMap<UUID, String> clientBrands = new HashMap<>();

	@Override
	public void onInitializeServer() {

		ServerPlayNetworking.registerGlobalReceiver(CustomPayloadC2SPacket.BRAND, (server, player, handler, buf, responseSender) -> {
			try {
				var brand = buf.readString(1024).strip().trim();
				if (!brand.isBlank()) {
					clientBrands.put(player.getUuid(), brand);
					return;
				}
				LOGGER.info("{} brand: {}", player, brand);
			} catch (Exception error) {
				LOGGER.error("Error", error);
			}
			if (player != null) {
				clientBrands.put(player.getUuid(), "unknown");
			}
		});

		ServerPlayConnectionEvents.DISCONNECT.register((handler, server) -> {
			try {
				clientBrands.remove(handler.player.getUuid());
			} catch (Exception error) {
				LOGGER.error("Error", error);
			}
		});

		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
			if (environment.dedicated) {
				dispatcher.register(literal("brand").requires((source) -> source.hasPermissionLevel(source.getServer().getFunctionPermissionLevel())).then(argument("player", GameProfileArgumentType.gameProfile()).executes(ctx -> {
					for (GameProfile profile : GameProfileArgumentType.getProfileArgument(ctx, "player")) {
						if (profile == null) continue;
						String brand = clientBrands.get(profile.getId());
						if (brand == null) brand = "[brand not found]";
						var brandText = Text.literal(brand);
						brandText.setStyle(brandText.getStyle()
								.withColor(brand.equals("vanilla") ? Formatting.GREEN : Formatting.YELLOW)
								.withHoverEvent(new HoverEvent(
										HoverEvent.Action.SHOW_TEXT,
										Text.literal("WARNING: Modded clients can spoof/change their client brand!")
								)));
						var profileText = Text.literal(profile.getName());
						profileText.setStyle(profileText.getStyle()
								.withHoverEvent(new HoverEvent(
										HoverEvent.Action.SHOW_ENTITY, new HoverEvent.EntityContent(EntityType.PLAYER, profile.getId(), Text.literal(profile.getName())))
								));

						Text response = Text.literal("").append(profileText).append(Text.of(" brand is: ")).append(brandText);
						ctx.getSource().sendFeedback(() -> response, true);
					}

					return SINGLE_SUCCESS;
				})));
			}
		});
	}
}
