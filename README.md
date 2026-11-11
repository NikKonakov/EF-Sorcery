# Epic Fight: Sorcery

An addon for [Epic Fight](https://modrinth.com/mod/epic-fight) that adds a **Sorcerer** subclass: mana-infused attacks, spell-augmented combos and a fighting style built around magic.

> [!NOTE]
> **Early development (v0.1.0).** The project was recently rebuilt from scratch on NeoForge 1.21.1, so there is very little to play yet. Expect things to change and break.

## What is it?

Epic Fight turns Minecraft combat into an action-game system with animated attacks, combos, dodges and weapon skills. **Epic Fight: Sorcery** builds on that system instead of replacing it. The Sorcerer's abilities use Epic Fight's own skill slots and animation system, so magic becomes part of the melee fight rather than a separate spellbook.

## Goals

- **Mana infusion.** Channel mana into your weapon and attacks through a dedicated *Mana Infuse* skill slot.
- **Spell-augmented combos.** Weave spells into Epic Fight's combo chains.
- **A magic-driven fighting style.** Give the Sorcerer its own *identity* skill that defines how the class plays.
- **Feel native to Epic Fight.** Build on Epic Fight's skill, animation and event APIs so the addon fits alongside the base mod and other addons.
- **Learn by building.** This is also a personal project for getting to know NeoForge and the Epic Fight API in depth.

## Roadmap

- [x] NeoForge 1.21.1 project with Epic Fight as a required dependency
- [x] Custom skill slots: `SORCERY_MANA_INFUSE` and `SORCERY_IDENTITY`
- [ ] Mana as a player resource
- [ ] First Mana Infuse skill
- [ ] Sorcerer identity skill
- [ ] Custom animations and spell-augmented combos

## Requirements

| Dependency | Version |
|---|---|
| Minecraft | 1.21.1 |
| NeoForge | 21.1.219 or newer |
| Epic Fight | 21.15 or newer (NeoForge build for 1.21.1) |
| Java (to build) | JDK 21 |

## Installation

There are no public releases yet. To try the addon, [build it from source](#building-from-source) and put the jar from `build/libs/` into your `mods` folder together with Epic Fight.

## Building from source

```bash
git clone https://github.com/NikKonakov/EF-Sorcery.git
cd EF-Sorcery
./gradlew build
```

On Windows, run `gradlew.bat build` instead. Gradle downloads NeoForge, Minecraft and Epic Fight on the first build, so it takes a while.

### Development runs

| Command | What it does |
|---|---|
| `./gradlew runClient` | Starts a development client with the addon and Epic Fight loaded |
| `./gradlew runServer` | Starts a development dedicated server |
| `./gradlew runData` | Runs the data generators into `src/generated/resources/` |

All version numbers live in `gradle.properties`. The dev runtime also includes a few performance mods (Sodium, Lithium, FerriteCore and others) to make testing smoother; the addon itself depends only on Epic Fight.

## Project layout

```
src/main/java/com/sorcery/epicfightsorcery/
├── EpicFightSorcery.java        # mod entry point (@Mod)
└── SorcerySkillSlots.java       # custom Epic Fight skill slots
src/main/resources/
├── META-INF/neoforge.mods.toml  # mod metadata and dependencies
└── pack.mcmeta
build.gradle.kts                 # ModDevGradle build script
gradle.properties                # mod id, version and dependency versions
```

## History

The first version of this project (December 2024 to April 2025) targeted Forge 1.20.1 and was inspired by *Jujutsu Kaisen*: it had Cursed Energy and Black Flash mechanics, Divergent Fist skills with custom animations, and a Cursed Fists weapon. In July 2026 the project was restarted from scratch on NeoForge 1.21.1 with a cleaner structure. The old code is still in the git history, in the commits before *Restart the addon from scratch on NeoForge 1.21.1*.

## License

All Rights Reserved, as declared in `neoforge.mods.toml`.

## Credits

Built on [Epic Fight](https://modrinth.com/mod/epic-fight) and [NeoForge](https://neoforged.net/).
