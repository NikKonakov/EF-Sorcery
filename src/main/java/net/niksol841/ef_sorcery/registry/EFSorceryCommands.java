package net.niksol841.ef_sorcery.registry;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import net.niksol841.ef_sorcery.world.capabilities.player.CursedEnergyPlayerCapability;
import net.niksol841.ef_sorcery.world.capabilities.player.CursedEnergyPlayerCapabilityProvider;

public class EFSorceryCommands {

    private static final SuggestionProvider<CommandSourceStack> PLAYER_SUGGESTION = (ctx, builder) -> {
        PlayerList playerList = ctx.getSource().getServer().getPlayerList();
        return SharedSuggestionProvider.suggest((Iterable<String>) playerList.getPlayers().stream().map(ServerPlayer::getName), builder);
    };

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("EFSorcery")
                .then(Commands.literal("isSorcerer")
                        .executes(ctx -> getSorcererStatus(ctx.getSource(), ctx.getSource().getPlayer().getName().getString()))
                        .then(Commands.argument("playerName", StringArgumentType.string())
                                .suggests(PLAYER_SUGGESTION)
                                .executes(ctx -> getSorcererStatus(ctx.getSource(), StringArgumentType.getString(ctx, "playerName")))
                        )
                )
                .then(Commands.literal("setSorcerer")
                        .then(Commands.argument("value", BoolArgumentType.bool())
                                .executes(ctx -> setSorcererStatus(ctx.getSource(), BoolArgumentType.getBool(ctx, "value")))
                                .then(Commands.argument("playerName", StringArgumentType.string())
                                        .suggests(PLAYER_SUGGESTION)
                                        .executes(ctx -> setSorcererStatus(ctx.getSource(), BoolArgumentType.getBool(ctx, "value"), StringArgumentType.getString(ctx, "playerName"))))
                        )
                )
                .then(Commands.literal("getCE")
                        .executes(ctx -> getCE(ctx.getSource()))
                        .then(Commands.argument("playerName", StringArgumentType.string())
                                .executes(ctx -> getCE(ctx.getSource(), StringArgumentType.getString(ctx, "playerName")))
                        )
                )
                .then(Commands.literal("setCE")
                        .then(Commands.argument("value", IntegerArgumentType.integer())
                                .executes(ctx -> setCE(ctx.getSource(), IntegerArgumentType.getInteger(ctx,"value")))
                                .then(Commands.argument("playerName", StringArgumentType.string())
                                        .executes(ctx -> setCE(ctx.getSource(), IntegerArgumentType.getInteger(ctx,"value"), StringArgumentType.getString(ctx, "playerName")))
                                )
                        )
                )
                .then(Commands.literal("getRegenRate")
                        .executes(ctx -> getRegenRate(ctx.getSource(), ctx.getSource().getPlayer().getName().getString()))
                        .then(Commands.argument("playerName", StringArgumentType.string())
                                .executes(ctx -> getRegenRate(ctx.getSource(), StringArgumentType.getString(ctx, "playerName")))
                        )
                )
                .then(Commands.literal("setRegenRate")
                        .then(Commands.argument("value", IntegerArgumentType.integer())
                                .executes(ctx -> setRegenRate(ctx.getSource(), IntegerArgumentType.getInteger(ctx, "value")))
                                .then(Commands.argument("playerName", StringArgumentType.string())
                                        .executes(ctx -> setRegenRate(ctx.getSource(), IntegerArgumentType.getInteger(ctx, "value"), StringArgumentType.getString(ctx, "playerName")))
                                )
                        )
                )
        );
    }

    private static int setRegenRate(CommandSourceStack command, int value,String playerName){
        PlayerList playerList = command.getServer().getPlayerList();
        ServerPlayer serverPlayer = playerList.getPlayerByName(playerName);
        if (serverPlayer != null) {
            CursedEnergyPlayerCapability cap = serverPlayer.getCapability(CursedEnergyPlayerCapabilityProvider.PLAYER_CURSED_ENERGY_CAPABILITY).resolve().get();
            int prevVal = cap.getCERegenRate();
            cap.setCERegenRate(value);
            command.source.sendSystemMessage(Component.literal("Command has been executed"));
            serverPlayer.sendSystemMessage(Component.literal("Your CE regen rate has been changed from "+prevVal+" to "+cap.getCERegenRate()));
            return 1;
        }else{
            command.source.sendSystemMessage(Component.translatable("commands.error.execution.failed"));
            command.source.sendSystemMessage(Component.translatable("commands.error.reason.player_not_found"));
            command.source.sendSystemMessage(Component.literal("PlayerName: "+playerName));
            return 0;
        }
    }

    private static int setRegenRate(CommandSourceStack command, int value){
        return setRegenRate(command, value, command.getPlayer().getName().getString());
    }

    private static int getRegenRate(CommandSourceStack command, String playerName){
        PlayerList playerList = command.getServer().getPlayerList();
        ServerPlayer serverPlayer = playerList.getPlayerByName(playerName);
        if (serverPlayer!=null){
            CursedEnergyPlayerCapability cap = serverPlayer.getCapability(CursedEnergyPlayerCapabilityProvider.PLAYER_CURSED_ENERGY_CAPABILITY).resolve().get();
            command.source.sendSystemMessage(Component.literal("Command executed successfully"));
            serverPlayer.sendSystemMessage(Component.literal("Your CE regen rate: "+cap.getCERegenRate()));
            return 1;
        }else {
            command.source.sendSystemMessage(Component.literal("Command Failed. Player not found"));
            return 0;
        }
    }

    private static int setCE(CommandSourceStack command, int value){
       return setCE(command, value, command.getPlayer().getName().getString());
    }

    private static int setCE(CommandSourceStack command,int value, String playerName){
        PlayerList playerList = command.getServer().getPlayerList();
        ServerPlayer serverPlayer = playerList.getPlayerByName(playerName);
        if(serverPlayer!=null){
            CursedEnergyPlayerCapability cap = serverPlayer.getCapability(CursedEnergyPlayerCapabilityProvider.PLAYER_CURSED_ENERGY_CAPABILITY).resolve().get();
            cap.setCE(value);
            command.source.sendSystemMessage(Component.literal("Command has been executed successfully"));
            serverPlayer.sendSystemMessage(Component.literal("Your CE has been set: " + cap.getCE()));
            return 1;
        }else{
            command.source.sendSystemMessage(Component.literal("Command Failed"));
            return 0;
        }
    }

    private static int getSorcererStatus(CommandSourceStack command, String playerName){
        PlayerList playerList = command.getServer().getPlayerList();
        ServerPlayer serverPlayer = playerList.getPlayerByName(playerName);
        if(serverPlayer != null){
            serverPlayer.sendSystemMessage(Component.literal("Sorcerer: "+serverPlayer.getCapability(CursedEnergyPlayerCapabilityProvider.PLAYER_CURSED_ENERGY_CAPABILITY).resolve().get().isCursedUser()));
            return 1;
        }
        return 0;
    }

    private static int setSorcererStatus(CommandSourceStack command, boolean value){
        return EFSorceryCommands.setSorcererStatus(command, value, command.getPlayer().getName().getString());
    };

    private static int setSorcererStatus(CommandSourceStack command, boolean value, String playerName){
        PlayerList playerList = command.getServer().getPlayerList();
        ServerPlayer serverPlayer = playerList.getPlayerByName(playerName);
        if(serverPlayer != null){
            serverPlayer.getCapability(CursedEnergyPlayerCapabilityProvider.PLAYER_CURSED_ENERGY_CAPABILITY).resolve().get().setIsCursedUser(value);
            command.source.sendSystemMessage(Component.literal("Sorcerer status set to "+value+" for "+playerName));
            return 1;
        }else{
            command.source.sendSystemMessage(Component.literal("Player not found"));
            return 0;
        }
    }

    private static int getCE(CommandSourceStack command){
        return getCE(command, command.getPlayer().getName().getString());
    }

    private static int getCE(CommandSourceStack command, String playerName){
        PlayerList playerList = command.getServer().getPlayerList();
        ServerPlayer serverPlayer = playerList.getPlayerByName(playerName);
        if (serverPlayer != null){
            command.source.sendSystemMessage(Component.literal("Your current CE: "+serverPlayer.getCapability(CursedEnergyPlayerCapabilityProvider.PLAYER_CURSED_ENERGY_CAPABILITY).resolve().get().getCE()));
            return  1;
        }else {
            command.source.sendSystemMessage(Component.literal("Player not found"));
            return 0;
        }
    }
}
