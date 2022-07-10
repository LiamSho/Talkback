# Talkback

A Minecraft Spigot Plugin to send pre-defined messages with command. Support PlaceholderAPI.

## Features

You can set pre-define messages which support PlaceholderAPI in the config file. And you can use command to send this pre-defined messages.

The message can be sent to the player who sent the command. Or to the entire server.

# Why I write this

I write this plugin to work with PlaceholderAPI so that I can get some server information by command, like TPS, RAM, etc.

I used to use EssentialX plugin with `/gc` command. But I am running a Minecraft 1.19 server which EssentialX plugin is not compatible with. And EssentialX is a huge plugin and I do not need most of its features.

## Install

1. Download the plugin JAR from [GitHub Release](https://github.com/LiamSho/Talkback/releases)
2. Put the JAR file in your plugins folder
3. Restart your server
4. You're ready to go

## Configuration

When you first start the server with this plugin, a `config.yml` will be created in the plugin's config folder.

The default config comes with a demo `Command Entry`. You can edit it or add more `Command Entry` in the `config.yml` file.

```yaml
info:                   # Command entry name, you can add more this kind of section in the config file
  command: info         # Command, same with the command entry name
  permission: ""        # The permission required to use, leave empty as no permission required
  target: self          # Send target. `self` for the command executor, `all` for the entire server
  message: |-           # The message body
    §3================ SERVER INFO ================§r
    §r
    §r  §6Version: §b%server_version%§r
    §r  §6Uptime: §b%server_uptime%§r
    §r
    §r  §6Players online: §b%playerlist_online,normal,yes,amount%/%server_max_players%§r
    §r
    §r  §6RAM (MB):§r
    §r    §6Used: §b%server_ram_used%§r
    §r    §6Total: §b%server_ram_total%§r
    §r    §6Free: §b%server_ram_free%§r
    §r    §6Max: §b%server_ram_max%§r
    §r
    §r  §6TPS: %server_tps%§r
    §r
    §r  §6Entities: §b%server_total_living_entities%/%server_total_entities%§r
    §r  §6Chunks: §b%server_total_chunks%§r
```

Be aware that the message in the demo is using placeholder API. You need to install `PlaceholderAPI` plugin and `server` and `playerlist` expansion.

You can try to run `/tb info` to see the result.

## Commands

1. `/tb <Entry Name>` - Send the pre-defined message
2. `/tbr` - Reload the config file
3. `/tbl` - List all available command entries

## License

This plugin is free and open source under the [Apache 2.0 license](./LICENSE).
