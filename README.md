# OptiCoresLib

**OptiCoresLib** is the shared library layer for the OptiCores ecosystem. It is a lightweight Fabric library intended to provide common infrastructure that can be reused by OptiCores and related OptiCores projects instead of duplicating the same code across multiple mods.

## Purpose

OptiCoresLib is designed to keep shared functionality separate from the main OptiCores mod.

The library provides a dedicated place for:

- Shared utilities and infrastructure
- Common client-side systems
- Reusable APIs used by OptiCores
- Compatibility-oriented code that should not be duplicated
- Future shared systems that need to be maintained independently from the main mod

The goal is to keep OptiCores modular, easier to maintain, and easier to extend.

## Supported Environment

| Component | Version |
|---|---|
| Minecraft | **1.21.1 – 1.21.11** |
| Mod Loader | **Fabric** |
| Fabric Loader | **0.16.0+** |
| Fabric API | **0.116.17+1.21.1** |
| Java | **21** |

> The current development project is built against Minecraft 1.21.1 while the mod metadata allows the 1.21.1–1.21.11 Minecraft range.

## Relationship with OptiCores

OptiCoresLib is a **supporting library**, not a replacement for OptiCores.

Its main purpose is to hold reusable systems that can be consumed by OptiCores without making the main mod responsible for every shared implementation.

This separation is intended to provide:

- Better modularity
- Less duplicated code
- Cleaner architecture
- Easier maintenance
- Safer future expansion of the OptiCores ecosystem

## Installation

OptiCoresLib is intended primarily to be installed as a dependency of mods that require it.

If you are developing or testing the library itself, clone the repository and import it as a Gradle/Fabric Loom project.

### Building from Source

On Windows:

```bash
./gradlew.bat build
```

On Linux/macOS:

```bash
./gradlew build
```

The built JAR files will be placed in:

```
build/libs/
```

## Development

The project uses:

- Java 21
- Fabric Loom
- Gradle
- Yarn mappings
- Fabric API

The source code is located under:

```
src/main/java/
```

Resources are located under:

```
src/main/resources/
```

## Compatibility

OptiCoresLib is designed for the Fabric ecosystem and declares compatibility with Minecraft **1.21.1 through 1.21.11**.

Because this is a library, compatibility depends not only on Minecraft and Fabric versions but also on the APIs used by the consuming mod.

## Project Status

OptiCoresLib is under active development.

The library architecture may expand as shared functionality is extracted from OptiCores and as additional OptiCores projects require reusable infrastructure.

APIs should therefore be considered subject to change until the library reaches a stable release.

## Contributing

Bug reports, improvements, and code contributions are welcome.

When contributing:

1. Keep shared functionality inside the library only when it is genuinely reusable.
2. Avoid adding OptiCores-specific gameplay or feature logic that belongs in the main mod.
3. Keep Minecraft/Fabric compatibility in mind.
4. Test changes against the supported environment before submitting them.

## License

OptiCoresLib is licensed under the **MIT License**.

See [LICENSE](LICENSE) for the complete license text.

## Links

- [OptiCoresLib Repository](https://github.com/anibalmolina-debug/OpticoresLib)
- [OptiCores](https://github.com/anibalmolina-debug/Opticores)
