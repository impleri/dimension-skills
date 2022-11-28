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

## Modpacks

Want to use this in a modpack? Great! This was designed with modpack developers in mind. No need to ask.
