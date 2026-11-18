# EF:Sorcery roadmap

The step-by-step plan behind the short roadmap in the [README](README.md): five milestones, three releases, and every milestone ends in something you can show.

**Next up:** step 0.3, then 0.5 to finish M0.

## How we work

- **Start a session:** take the first unticked step below and read its line once.
- **End a session:** commit, tick the step's box in the same commit, and update the "Next up" line.
- **Stuck for 2 sessions:** split the step or ask for a review.
- **New ideas** go to the [parking lot](#parking-lot), not into the current milestone.
- **Using Claude:** ask it to explain, review and hunt bugs; type the code yourself.
- **A milestone is done when** every box is ticked, build and tests are green, the demo clip is in the README and this file is updated.

## Design decisions

Agreed on 18 Nov 2026.

| Topic | Decision | Why |
|---|---|---|
| Core resource | A cursed energy (CE) bar; every feature spends or builds it | One clear system to learn, build and show |
| Main style | Bare-handed combat, built on Epic Fight's own fist moves first | The core fantasy; reuse before drawing custom animations |
| Weapons | Sorcerers keep weapons, with a damage penalty set in the config | A hard ban breaks axes as tools, and canon sorcerers use weapons |
| Black Flash | The first one happens by itself in a hard fight; after that it is a timing check | In canon it can't be learned, only experienced |
| Focus | Not a separate feature: it is "the zone" that widens the Black Flash timing window | One loop instead of two features, and no third HUD bar |
| Theme | Jujutsu Kaisen terms everywhere: cursed energy, Black Flash, the zone; no "mana" | Stay true to the source |

## Milestones

| Milestone | Ships | Skill practiced | Size | Status |
|---|---|---|---|---|
| [M0 Foundation](#m0-foundation) | A clean repo on Epic Fight 21.17.3.1 | Git hygiene | ~2 sessions | In progress (4 of 6) |
| [M1 Cursed energy core](#m1-cursed-energy-core) | CE bar, reinforcement, weapon penalty | Unit tests (JUnit 5) | ~10 sessions | Planned |
| [M2 Unarmed combat](#m2-unarmed-combat), **v0.1** | Fist style, CE-infused hits, 1 to 2 techniques | Reading a large codebase; releases | ~10 to 14 sessions | Planned |
| [M3 Black Flash and Focus](#m3-black-flash-and-focus), **v0.2** | Unlock by experience, timing check, the zone | Client-server authority | ~10 to 14 sessions | Planned |
| [M4 Skill tree](#m4-skill-tree), **v0.3** | An EF:Sorcery page in Epic Fight: Skill Tree | Data-driven design, optional dependencies | ~5 sessions | Planned |

We stay on Minecraft 1.21.1 with Epic Fight pinned to 21.17.3.1 through v0.1, then decide whether to port to 26.x. Epic Fight is bumped only at the start of a milestone. Sizes are rough estimates: **S** = 1 session, **M** = 2 to 4, **L** = 5 or more.

## M0 Foundation

A clean repo on the newest Epic Fight.

- **Goal:** the repo builds on Epic Fight 21.17.3.1, looks professional, and the code speaks cursed energy, not mana.
- **Done when:** `./gradlew build` is green on the new Epic Fight and the README matches this roadmap.

Already in place: a NeoForge 1.21.1 project with Epic Fight as a required dependency, the custom skill slots `SORCERY_MANA_INFUSE` and `SORCERY_IDENTITY`, and a first Epic Fight event hook that reports hit damage and spawns crit particles.

- [x] **0.1** Fill `.gitignore` and stop tracking the IDE folder `.idea/` (`2617ab0`, `8e10651`).
- [x] **0.2** Set the real author and `issueTrackerURL` (`https://github.com/NikKonakov/EF-Sorcery/issues`) in `neoforge.mods.toml`.
- [ ] **0.3** Move the dev-only mods (Sodium, Lithium, FerriteCore, ImmediatelyFast, Entity Culling) from `implementation` to `runtimeOnly`. Epic Fight: Skill Tree stays until M4 makes it an optional dependency.
- [x] **0.4** Pin `epicfightVersion=21.17.3.1-mc1.21.1-neoforge` and run the client (`6b09e93`).
- [ ] **0.5** Rename "mana" to cursed energy in the skill slots, the README and the mod description.
- [x] **0.6** Replace the README checklist with M0 to M4 and a link to this roadmap.

## M1 Cursed energy core

A bar that saves, syncs and gets spent.

- **Goal:** a sorcerer has a CE bar that survives reloads and death, regenerates, shows on screen and powers reinforcement; weapons carry a penalty.
- **Demo clip:** turn on reinforcement, the bar drains while punches hit harder; die, respawn, still a sorcerer.
- **Done when:** tests are green, and CE survives save, reload and death on a dedicated server (`runServer`).

**Steps** (one session each):

- [ ] **1.1** Write a `CursedEnergy` class in plain Java: current, max, regen per second, spend, sorcerer flag. No Minecraft imports.
- [ ] **1.2** Add JUnit 5 to the build. Test spend, the 0 and max limits, regen per second and a save-load round trip, which catches the old load bug.
- [ ] **1.3** Store it on the player with a Data Attachment (a codec plus `copyOnDeath`). Add `/sorcery path` and `/sorcery ce` commands to poke it. Check that the sorcerer flag survives death.
- [ ] **1.4** Regenerate once per second in the player tick (not the level tick), only for sorcerers.
- [ ] **1.5** Sync to the client: a `CustomPacketPayload` with a `StreamCodec`, sent with `PacketDistributor.sendToPlayer` when CE changes. NeoForge 1.21.1 does not sync attachments by itself.
- [ ] **1.6** Draw the CE bar on the HUD and hide it for non-sorcerers.
- [ ] **1.7** Reinforcement key: the client sends a toggle payload, and the server checks it before acting.
- [ ] **1.8** Reinforcement effect: drains CE every second, adds an attack-damage modifier, switches off at 0 CE.
- [ ] **1.9** Weapon penalty: in `LivingIncomingDamageEvent`, cut the damage a sorcerer deals with anything Epic Fight treats as a weapon. Empty hands are never penalized. Start at -30% and tune by playing.
- [ ] **1.10** Move the numbers into a config file (`ModConfigSpec`): max CE, regen, drain, penalty. Record the demo clip and update the README.

Bugs in the first version's code (commit `11f7412`) not to repeat:

- CE is saved under the key `cursed_energy` but loaded from `cursed_energy_capacity`, so it resets to 0 after a reload.
- The respawn copy skips the sorcerer flag, so a sorcerer stops being one after death.
- Regeneration runs in the level tick, once per loaded dimension each second: 3 times in vanilla.
- The Black Flash "debuff" adds +10% health and speed when it ends, instead of a penalty.

## M2 Unarmed combat

Fists, techniques, and the first release, v0.1.

- **Goal:** bare-handed sorcerer combat that feels different from vanilla Epic Fight, with one or two CE techniques, shipped as v0.1.
- **Demo clip:** 20 to 30 seconds against a few mobs: a combo with CE-infused hits, a dash strike, particles on impact.
- **Release gate v0.1:** a git tag, `CHANGELOG.md`, the demo clip in the README, the jar on GitHub Releases.

**Steps** (coarse until M1 is done, then split into one-session steps):

- [ ] **2.1** Study Epic Fight's fist set (`FIST_AUTO1` to `FIST_AUTO3`, `FIST_DASH`, `FIST_AIR_SLASH`) and the 21.17 weapon API. Pick a route: an empty-hand moveset only sorcerers get, or a Cursed Fists item like the first version. (S)
- [ ] **2.2** Wire up the sorcerer fist style with Epic Fight's fist animations. (M)
- [ ] **2.3** CE-infused hits: while reinforcement is on, hits spend CE, deal bonus damage and spawn particles with `ServerLevel.sendParticles`. The hit-particle test in `EpicFightSorcery.java` is a head start. (S)
- [ ] **2.4** Port one old animation, `divergent_fist_strong_strike`, through the new `AnimationAccessor` API to prove the asset pipeline. (S to M)
- [ ] **2.5** Technique 1, dash strike: an Epic Fight skill that costs CE. (M)
- [ ] **2.6** Technique 2, heavy punch, on the ported animation. Optional for v0.1. (M)
- [ ] **2.7** A way to become a sorcerer without commands: a consumable item for now, a choice screen later. (S)
- [ ] **2.8** Balance pass: 3 test fights, then move the numbers into the config. (S)
- [ ] **2.9** Release v0.1.0. (S)

After v0.1: decide whether to stay on Minecraft 1.21.1 or port to 26.x.

## M3 Black Flash and Focus

The signature mechanic, released as v0.2.

- **Goal:** Black Flash unlocks by experience and then becomes a timing check; Focus is "the zone" that widens the window and makes chains possible.
- **The loop:** a Black Flash spikes Focus, Focus widens the timing window, and a wider window makes the next Black Flash likelier. Getting hit breaks the loop.
- **Demo clip:** the first, accidental Black Flash with its unlock message, then a chain of two or three.
- **Release gate v0.2:** tag, changelog, clip.

**Steps:**

- [ ] **3.1** One-page design note: window size, unlock odds, Focus gain and loss, chain cap. Starting guess for the window: about 100 ms, which is 2 server ticks. (S)
- [ ] **3.2** Unlock: a small chance on a CE-infused hit when your HP is low or the target is strong; save an "experienced" flag on the player. (S to M)
- [ ] **3.3** Timing check: the client sends the CE key press, and the server checks it against the hit phase of the current attack animation, with a small tolerance for lag. (M to L)
- [ ] **3.4** The payoff: a damage multiplier, black-red particles, a sound, a short screen shake. Reuse the old `black_flash` damage type. (M)
- [ ] **3.5** Focus: rises on clean hits and dodges, falls when you get hit, widens the window. Show it as an aura, not a third bar. (M)
- [ ] **3.6** Chains: a Black Flash spikes Focus, so the next one comes easier; cap the chain length. (S)
- [ ] **3.7** Release v0.2.0. (S)

## M4 Skill tree

An EF:Sorcery page in Epic Fight: Skill Tree, released as v0.3.

- **Goal:** our techniques unlock through an EF:Sorcery page in the Epic Fight: Skill Tree addon, defined in JSON, with no custom tree screen.
- **Start when:** at least 5 of our Epic Fight skills exist. Before that, the page has nothing to unlock.
- **Release gate v0.3:** tag, changelog, clip.
- **Fallback:** if the page can't do what we need, our own tree screen comes back from the parking lot.

**Steps:**

- [ ] **4.1** Paper design: 10 to 15 nodes, each one of our Epic Fight skills; mark the ones that change the fighting style. (S)
- [ ] **4.2** Make Epic Fight: Skill Tree an optional dependency: `type = "optional"` in `neoforge.mods.toml`, compiled against but not bundled. (S)
- [ ] **4.3** Generate the page and its nodes with the addon's `SkillTreeProvider` data generator, which writes JSON to `data/epicfight_sorcery/epicskills/tree/` and `data/epicfight_sorcery/epicskills/entry/`. (M)
- [ ] **4.4** Show the page only to sorcerers through its `conditions` field (an entity predicate), and check that the mod still runs without the addon. (S)
- [ ] **4.5** Release v0.3.0. (S)

## Parking lot

Nothing here is cut; each idea waits for the milestone that makes it cheap.

| Idea | Why it waits | Pick it up when |
|---|---|---|
| Cursed techniques combined with normal attacks | Each technique is its own system | After v0.3, starting with one pilot technique |
| Binding Vow: give up weapons for stronger CE | Needs the weapon penalty to exist and be balanced | After the M2 balance pass |
| Path choice screen at first join | A consumable item is enough for v0.1 | Before publishing on Modrinth |
| New animations made in Blender | Epic Fight's fist set and the 10 old animations cover v0.1 | When a technique needs a motion nobody has |
| Our own skill tree screen | A page in Epic Fight: Skill Tree covers v0.3 | If that page can't do what we need |
| Port to Minecraft 26.x | Epic Fight for 26.x is not released yet | When Epic Fight for 26.x ships |
