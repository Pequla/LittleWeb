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

### GET /api/user

Sample response:
```json
{
    "players": {
        "max": 30,
        "online": 4,
        "list": [
            {
                "name": "L_Yuae",
                "id": "69c154a4-0a6a-4578-b169-c4be379e1e8e"
            },
            {
                "name": "redshock_432",
                "id": "126b3c27-3d53-40e3-a531-dbd59cd3cc1c"
            },
            {
                "name": "wyvy64",
                "id": "caa84dd1-1142-41cc-b307-14f4abdc540e"
            },
            {
                "name": "Bundering",
                "id": "ff1b1e7c-0c1f-4142-b2df-d16a403c025d"
            }
        ]
    },
    "world": {
        "seed": "-4255308501429674615",
        "time": 836,
        "type": "minecraft:normal"
    },
    "plugins": [
        "CoreProtect",
        "LuckPerms",
        "LittleLink",
        "Vault",
        "LittleWeb",
        "LWC",
        "Essentials",
        "EssentialsSpawn",
        "EssentialsChat"
    ],
    "version": "git-Paper-14 (MC: 1.19)"
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