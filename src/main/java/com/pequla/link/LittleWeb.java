package com.pequla.link;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pequla.link.model.*;
import com.pequla.link.service.DataService;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import spark.Spark;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public final class LittleWeb extends JavaPlugin {

    @Override
    public void onEnable() {
        PluginManager manager = getServer().getPluginManager();
        LittleLink plugin = (LittleLink) manager.getPlugin("LittleLink");
        if (plugin == null) {
            getLogger().warning("Plugin LittleLink not found");
            manager.disablePlugin(this);
            return;
        }

        // Save config from resources to server directory
        saveDefaultConfig();

        // Retrieving the object mapper instance from main plugin
        ObjectMapper mapper = DataService.getInstance().getMapper();

        // Universal Spark settings
        getLogger().info("Loading internal server");
        Spark.port(getConfig().getInt("api.port"));
        Spark.ipAddress(getConfig().getString("api.address"));
        Spark.after((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Methods", "*");
            response.type("application/json");
        });

        Spark.get("/api/status", (request, response) -> {
            HashSet<PlayerData> list = new HashSet<>();
            getServer().getOnlinePlayers().forEach(player -> list.add(PlayerData.builder()
                    .name(player.getName())
                    .id(player.getUniqueId().toString())
                    .build())
            );

            List<String> plugins = Arrays.stream(getServer().getPluginManager().getPlugins())
                    .map(Plugin::getName)
                    .collect(Collectors.toList());

            World world = getServer().getWorlds().get(0);
            return mapper.writeValueAsString(ServerStatus.builder()
                    .players(PlayerStatus.builder()
                            .online(list.size())
                            .max(getServer().getMaxPlayers())
                            .list(list)
                            .build())
                    .plugins(plugins)
                    .world(WorldData.builder()
                            .seed(String.valueOf(world.getSeed()))
                            .time(world.getTime())
                            .type(getServer().getWorldType())
                            .build())
                    .version(getServer().getVersion())
                    .build());
        });

        Spark.get("/api/user", (request, response) -> {
            String uuid = request.queryParams("uuid");
            if (uuid == null) {
                response.status(400);
                return generateError("Required param uuid not found");
            }

            UUID converted;
            try {
                converted = UUID.fromString(uuid);
            } catch (IllegalArgumentException ae) {
                // Adding dashes to uuid string
                try {
                    converted = UUID.fromString(uuid.replaceFirst(
                            "(\\p{XDigit}{8})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}+)", "$1-$2-$3-$4-$5"
                    ));
                } catch (Exception ex) {
                    // Bad uuid
                    response.status(400);
                    return generateError("Invalid uuid");
                }
            }

            // Generating response
            DataModel model = plugin.getPlayerData().get(converted);
            if (model == null) {
                // Not found
                response.status(404);
                return generateError("User not found");
            }
            return mapper.writeValueAsString(model);
        });
    }

    @Override
    public void onDisable() {
        getLogger().info("Closing down internal server");
        Spark.stop();
    }

    public static String generateError(String error) throws JsonProcessingException {
        ObjectMapper mapper = DataService.getInstance().getMapper();
        return mapper.writeValueAsString(WebError.builder()
                .message(error)
                .timestamp(System.currentTimeMillis())
                .build());
    }
}