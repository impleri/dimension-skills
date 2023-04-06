# Dimension Skills

A library mod that exposes KubeJS methods to restrict how players travel through dimensions. Built around
[Player Skills](https://github.com/impleri/player-skills).

[![CurseForge](https://cf.way2muchnoise.eu/short_714781.svg)](https://curseforge.com/minecraft/mc-mods/dimension-skills)
[![Modrinth](https://img.shields.io/modrinth/dt/block-skills?color=bcdeb7&label=%20&logo=modrinth&logoColor=096765&style=plastic)](https://modrinth.com/mod/dimension-skills)
[![MIT license](https://img.shields.io/github/license/impleri/dimension-skills?color=bcdeb7&label=Source&logo=github&style=flat)](https://github.com/impleri/dimension-skills)
[![Discord](https://img.shields.io/discord/1093178610950623233?color=096765&label=Community&logo=discord&logoColor=bcdeb7&style=plastic)](https://discord.com/invite/avxJgbaUmG)
[![Maven](https://img.shields.io/maven-metadata/v?color=096765&label=%20&logo=gradle&logoColor=bcdeb7&metadataUrl=https%3A%2F%2Fmaven.impleri.org%2Fminecraft%2Fnet%2Fimpleri%2Fdimension-skills-1.19.2%2Fmaven-metadata.xml&style=flat)](https://github.com/impleri/dimension-skills#developers)

### xSkills Mods

[Player Skills](https://github.com/impleri/player-skills)
| [Block Skills](https://github.com/impleri/block-skills)
| [Dimension Skills](https://github.com/impleri/dimension-skills)
| [Fluid Skills](https://github.com/impleri/fluid-skills)
| [Item Skills](https://github.com/impleri/item-skills)
| [Mob Skills](https://github.com/impleri/mob-skills)

## Concepts

This mod leans extensively on Player Skills by creating and consuming the Skill-based Restrictions. Out of the box, this
mod can restrict whether a player can travel to a dimension or redirect to which dimension the player travels.

## KubeJS API

When creating restrictions, you have two avenues: `replace` the destination with a different dimension or `modify` how a
player can access the destination dimension. Replacements will trump other modifications. One example where you may want
to use both is in cascading progress: when the player starts, the dimension is completely inaccessible. After meeting
some criteria, the player must attempt to access the dimension with the correct criteria (i.e. from the correct
dimension or biome). Or maybe the portal redirects the player to a prerequisite dimension first. Finally, the dimension
can be fully accessible.

### Register

We use the `DimensionSkillEvents.register` ***server*** event to register dimension restrictions. If the player
***matches*** the criteria, the following restrictions are applied. This can cascade with other restrictions, so any
restrictions which replaces a dimension will trump any which only add restrictions to the dimension. Also, any
restrictions which deny the ability will trump any which allow it. We also expose these methods to indicate what
restrictions are in place for when a player meets that condition. By default, no restrictions are set, so be sure to set
actual
restrictions. [See Player Skills documentation for the shared API](https://github.com/impleri/player-skills#kubejs-restrictions-api).

#### Replacement methods

- `replaceWith(dimension: string)` - Name of replacement dimension

#### Allow Restriction Methods

- `nothing()` - shorthand to apply all "allow" restrictions
- `accessible()` - the dimension is accessible

#### Deny Restriction Methods

- `everything()` - shorthand to apply the below "deny" abilities
- `inacessible()` - the dimension cannot be accessed

### Examples

```js
DimensionSkillEvents.register((event) => {
  // Player cannot access the nether until they have gained the access_nether skill
  event.restrict("minecraft:the_nether", (restrict) =>
    restrict.inaccessible().if(player => player.cannot('access_nether'))
  );

  // Players whose dimensions_accessed skill is less than 5 will be redirected to The End rather than the Nether
  event.restrict("the_nether", (restrict) =>
    restrict.replaceWith("the_end").if(player => player.cannot('dimensions_accessed', 5))
  );

  // Players whose dimensions_accessed skill is less than 5 will be redirected to The End rather than the Ad Astra dimensions
  event.restrict("#ad_astra", (restrict) =>
    restrict.replaceWith("the_end").if(player => player.cannot('dimensions_accessed', 5))
  );

  // Player cannot access the overworld from the Nether if they have not gained the beat_some_boss skill. This does
  // not prevent travel to other dimensions from the Nether or even travel to the Nether 
  event.restrict("minecraft:overworld", (restrict) =>
    restrict.inaccessible().inDimension("the_nether").unless(player => player.can("beat_some_boss"))
  );
});
```

### Caveat

A known error is that redirecting travel from the End to another dimension will crash the server with
an `Entity colliding with block` error.

## Developers

Add the following to your `build.gradle`. I depend
on [Architectury API](https://github.com/architectury/architectury-api), [KubeJS](https://github.com/KubeJS-Mods/KubeJS),
and [PlayerSkills](https://github.com/impleri/player-skills), so you'll need those as well.

```groovy
dependencies {
    // Common should always be included 
    modImplementation "net.impleri:dimension-skills-${minecraft_version}:${dimensionskills_version}"
    // Plus forge
    modApi "net.impleri:dimension-skills-${minecraft_version}-forge:${dimensionskills_version}"
    // Or fabric
    modApi "net.impleri:dimension-skills-${minecraft_version}-fabric:${dimensionskills_version}"
}
repositories {
    maven {
        url = "https://maven.impleri.org/minecraft"
        name = "Impleri Mods"
        content {
            includeGroup "net.impleri"
        }
    }
}
```

## Modpacks

Want to use this in a modpack? Great! This was designed with modpack developers in mind. No need to ask.
