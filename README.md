# LittleWeb
Spigot plugin that exposes a REST API for developers to consume

Requires plugin [LittleLink](https://github.com/Pequla/LittleLink/releases/latest)

## Configuration

```yml
api:
  port: 8888
  address: localhost
  ```

- `port` where the HTTP Jetty Server will bind too, must be greater than 1024 since minecraft servers **shouldn't be run as root**
- `address` where the HTTP Jetty Server will listen to. If you want to expose the api without a reverse proxy you can do so by setting it to `0.0.0.0`

## API Endpoints

Plugin provides currently only 2 endpoints

### GET /api/status

Sample response:
```json
{
  "players": {
    "max": 30,
    "online": 3,
    "list": [
      {
        "name": "jb_himawari",
        "displayName:": "Himawari",
        "id": "12b7b9c9-7611-4679-bae8-94ed55a6b7a4"
      },
      {
        "name": "Twan_MC",
        "displayName:": "Tawan",
        "id": "eaf3de81-e7e9-4395-a0b1-456735b63aa7"
      },
      {
        "name": "Rekcah_",
        "displayName:": "Elle",
        "id": "5be97b0c-657f-4fb9-baa8-b463b3040c28"
      }
    ]
  },
  "world": {
    "seed": "-4255308501429674615",
    "time": 2832,
    "type": "minecraft:normal"
  },
  "plugins": [
    {
      "name": "CoreProtect",
      "version": "21.2",
      "authors": [
        "Intelli"
      ],
      "description": "Provides block protection for your server.\n",
      "website": "http://coreprotect.net"
    },
    {
      "name": "LuckPerms",
      "version": "5.4.26",
      "authors": [
        "Luck"
      ],
      "description": "A permissions plugin",
      "website": "https://luckperms.net"
    },
    {
      "name": "CustomBeaconRange",
      "version": "0.1",
      "authors": [
        "Soussou"
      ],
      "description": "A plugin to customize beacon effect range",
      "website": null
    },
    {
      "name": "LittleLink",
      "version": "1.5",
      "authors": [
        "Pequla"
      ],
      "description": "Allows for simple whitelisting on the minecraft server",
      "website": "https://pequla.com"
    },
    {
      "name": "LittleWeb",
      "version": "1.1",
      "authors": [
        "Pequla"
      ],
      "description": "Exposes a public REST API from data found in LittleLink",
      "website": "https://github.com/Pequla/LittleWeb"
    },
    {
      "name": "LittleHooks",
      "version": "1.0",
      "authors": [
        "Pequla"
      ],
      "description": "Discord webhook integration for your minecraft server",
      "website": "https://pequla.com"
    },
    {
      "name": "spark",
      "version": "1.9.9",
      "authors": [
        "Luck"
      ],
      "description": "spark is a performance profiling plugin/mod for Minecraft clients, servers and proxies.",
      "website": "https://spark.lucko.me/"
    },
    {
      "name": "Vault",
      "version": "1.7.3-b131",
      "authors": [
        "cereal",
        "Sleaker",
        "mung3r"
      ],
      "description": "Vault is a Permissions & Economy API to allow plugins to more easily hook into these systems without needing to hook each individual system themselves.",
      "website": "https://dev.bukkit.org/projects/vault"
    },
    {
      "name": "Essentials",
      "version": "2.19.5-dev+23-6816eb4",
      "authors": [
        "Zenexer",
        "ementalo",
        "Aelux",
        "Brettflan",
        "KimKandor",
        "snowleo",
        "ceulemans",
        "Xeology",
        "KHobbits",
        "md_5",
        "Iaccidentally",
        "drtshock",
        "vemacs",
        "SupaHam",
        "mdcfe",
        "JRoy",
        "pop4959"
      ],
      "description": "Provides an essential, core set of commands for Bukkit.",
      "website": "https://essentialsx.net/"
    },
    {
      "name": "EssentialsChat",
      "version": "2.19.5-dev+23-6816eb4",
      "authors": [
        "Zenexer",
        "ementalo",
        "Aelux",
        "Brettflan",
        "KimKandor",
        "snowleo",
        "ceulemans",
        "Xeology",
        "KHobbits",
        "md_5",
        "Okamosy",
        "Iaccidentally",
        "mdcfe",
        "JRoy",
        "triagonal"
      ],
      "description": "Provides chat control features for Essentials.  Requires Permissions.",
      "website": "https://essentialsx.net/"
    },
    {
      "name": "LWC",
      "version": "2.2.7-ad0f824",
      "authors": [
        "Hidendra",
        "pop4959",
        "Me_Goes_RAWR"
      ],
      "description": "Inventory protection & management utilizing SQLite or MySQL as its backend Other blocks can also be protected individually, if configured.\n",
      "website": "https://www.spigotmc.org/resources/lwc-extended.69551/"
    },
    {
      "name": "EssentialsSpawn",
      "version": "2.19.5-dev+23-6816eb4",
      "authors": [
        "Zenexer",
        "ementalo",
        "Aelux",
        "Brettflan",
        "KimKandor",
        "snowleo",
        "ceulemans",
        "Xeology",
        "KHobbits",
        "SupaHam",
        "mdcfe",
        "DoNotSpamPls",
        "JRoy"
      ],
      "description": "Provides spawn control commands, utilizing Essentials.",
      "website": "https://essentialsx.net/"
    },
    {
      "name": "Harbor",
      "version": "1.6.3",
      "authors": [
        "TechToolbox (@nkomarn)"
      ],
      "description": "Harbor redefines how sleep works in your server, making it easier for all the online players to get in bed quickly and skip through the night!",
      "website": "https://nkomarn.xyz"
    }
  ],
  "version": "git-Paper-41 (MC: 1.19)"
}
```

> When there are no players online the `list` will be returned as an empty array

### GET /api/user

> The `uuid` query parameter is mandatory. So the example url should be `/api/user?uuid=69c154a4-0a6a-4578-b169-c4be379e1e8e`

Sample response:
```json
{
    "id": "407635624787443712",
    "name": "LYuae#5987",
    "nickname": "Yuae %",
    "avatar": "https://cdn.discordapp.com/avatars/407635624787443712/8d80be04a42c5622aa5ebe358c68ea67.png"
}
```

### Exceptions and Status codes

If there is an error you can receive the following status codes:

- `404 NOT FOUND` - The required object was not found
- `400 BAD REQUEST` - There was an error processing your request's arguments
- `500 SERVER ERROR` - Something went wrong internally, you should probably restart the minecraft server

Sample response:
```json
{
    "message": "User not found",
    "timestamp": 1655245110448
}
```