# ClientCatcher-Fabric
[![WorkFlow Status](https://img.shields.io/github/actions/workflow/status/rotgruengelb/ClientCatcher-Fabric/build.yml)](https://github.com/rotgruengelb/ClientCatcher-Fabric/actions/workflows/build.yml)
[![Version](https://img.shields.io/github/v/release/rotgruengelb/ClientCatcher-Fabric?color=FFF0&style=flat-square)](https://modrinth.com/mod/clientcatcher)
[![Discord](https://img.shields.io/discord/899740810956910683?color=7289da&label=Discord)](https://discord.gg/-)

///// [![Banner] {Awaiting aproval from orignal ClientCatcher Plugin to use the banner}

A **Server-side** fabric Mod to get the client with which a player has connected to your server.

## Info
Looking for the original for **Velocity**? [ClientCatcher velocity plugin on Modrinth](https://modrinth.com/plugin/clientcatcher).

This Mod aims to have the same/similar Functionality as the Velocity Plugin. See [Roadmap](https://github.com/rotgruengelb/ClientCatcher-Fabric#Roadmap).

The initial code is based on the work of [C10udburst](https://github.com/C10udburst)s 1.18.2 Fabric [clientbrand](https://github.com/C10udburst/clientbrand) mod.

This mod is not fully effective, because modded clients can hide/change their client branding when entering the server or impersonate vanilla (or other) client brands.
The Mod can detect several clients such as Forge, Fabric, LiteLoader¹, Lunar¹, Vanilla, and others.
> ¹Not tested!

## Installation
- Install the latest version of the [Fabric API](https://modrinth.com/mod/fabric-api) for the correct Version.
- Download ClientCatcher-Fabric from [Modrinth](https://modrinth.com/mod/clientcatcher-fabric).
- Drag and drop on your servers mods folder.
- Start the server.

## Commands

#### Client `/brand <player>`
##### Permission: `Function Permission Level`
Shows you a player's client.
