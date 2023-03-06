# Dimension Skills

A library mod that exposes KubeJS methods to cancel dimensional travel. While this is built for usage
with [Player Skills](https://github.com/impleri/player-skills), you do not need to leverage Player Skills to prevent
dimensional travel.

## KubeJS API

### On Teleport

For teleport events, we use the `DimensionEvents.onTeleport` event. It is cancellable and, if the event is cancelled, we
will cancel the teleport action.

```js
  // Log all teleport events
DimensionEvents.onTeleport(event => {
  console.info(`Player ${event.entity.name} trying to teleport to `${event.destination}`);
  });

  // Block travel to the End if the player does not have a skill
  DimensionEvents.onTeleport('minecraft:the_end', event => {
    if (event.entity.data.skills.cannot('skills:started_quest')) {
      event.cancel(); // can also use event.deny() for the same effect but clearer actions
    }
 });
 
 // Always prevent going to the nether
 DimensionEvents.onTeleport('minecraft:the_nether', event => event.deny());
```

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
