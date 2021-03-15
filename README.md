# ChallengingTimer

![Build](https://github.com/pirocraft/challenging-timer/workflows/Build/badge.svg)
[![Version](https://img.shields.io/jetbrains/plugin/v/com.github.pirocraft.challengingtimer.svg)](https://plugins.jetbrains.com/plugin/com.github.pirocraft.challengingtimer)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/com.github.pirocraft.challengingtimer.svg)](https://plugins.jetbrains.com/plugin/com.github.pirocraft.challengingtimer)

<!-- Plugin description -->
Use the challenging timer to practice TDD baby-steps during your kata, 
challenging yourself to make small changes that keep your tests green after each period.

Launch the 3:00 timer, before the end you should have done only one thing :
- Think and define the next step
- Create a test and make it pass
- Refactor and keep your tests green

If one of your test failed at the end of the timer, you should rollback and try again with a smaller step.  
If you have finish to think before the end of the timer, you can restart the timer with a double click.

You can use the timer as a regular one, changing the duration in your settings.
<!-- Plugin description end -->

## Features

Check [challenging-timer.feature](./src/test/resources/feature/challenging-timer.feature) for details

## Installation

- Using IDE built-in plugin system:
  
  <kbd>Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>Marketplace</kbd> > <kbd>Search for "challenging-timer"</kbd> >
  <kbd>Install Plugin</kbd>
  
- Manually:

  Download the [latest release](https://github.com/pirocraft/challenging-timer/releases/latest) and install it manually using
  <kbd>Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>

## Contribution

Feel free to contribute to this plugin :
- Suggesting new features that you are interested to use with an issue, or a PR with a cucumber .feature file
- Pushing new features or fixes in PR

---
Plugin based on the [IntelliJ Platform Plugin Template][template].

[template]: https://github.com/JetBrains/intellij-platform-plugin-template
