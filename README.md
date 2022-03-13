# TeaTrees: A Java/Git exercise

## Summary

This repository contains a basic implementation of a Tetris-like game.
It currently lacks many features of the original game, some of which
are described in the issues.

**IMPORTANT**: Currently, the game is entirely automated - there's no
realtime interaction with the user. Instead, the moves are generated
randomly. While you may be tempted to do a research on how to read a single
key from the keyboard, it's not an easy thing in Java (usually involving
native methods), so probably there are better things to do first ;-)

## Task

Your task is to:

1. Pick an issue that doesn't have an implementor yet. An implementor here
   is indicated in a comment, like "I'm working on this task.". 
1. **Fork the repository** (button in the upper right corner of the main page).
1. **On the fork**, create a feature branch with a name following a convention:
   ```
   issueID-short-but-meaningful-description-written-in-kebab-case
   ```
   For example:
   ```
   999-implement-realtime-interaction
   ```
   (Don't put `#` in the name of the branch.)
1. Implement the feature **on your fork**.
1. Push the changes to a remote feature branch **on your fork**.
1. Create a Pull Request to merge it to `EPAM-Online-Courses/tetris:master`.

**IMPORTANT!**

YOU WILL NOW BE WORKING ON THE FORKED REPO, NOT ON THIS ONE, BUT ON YOUR GITHUB
COPY OF IT, CALLED THE FORK. WE REPEAT, YOU WILL NOW BE WORKING ON THE FORK.

Commands and task steps to do ON THE FORK. It's to be done ON THE FORK, ok?
NOT HERE, NOT IN YOUR LOCAL REPOSITORY.

## Rules

1. Your implementation shall not break any of the existing functionalities.
1. Each single commit shall contain:
   
   * a consistent, working set of changes,
   * a set of tests that prove the changes actually work,
   * a documentation attached to public API,
   * a commit message reasonably explaining the changes.

1. You shall touch only those classes that are necessary for the purpose
   of resolving the issue. Too wide, especially unrelated changes will cause
   the PR to be rejected.
1. PR will also be rejected if:
   * there are unresolved conflicts,
   * there are no tests,
   * acceptance criteria are not satisfied,
   * the code quality is insufficient,
   * the existing functionality is broken (also that not written by the author of PR).
1. PRs will be accepted during the review session, once we see they're really doing what
   they need to be doing - that is, they can be merged with XYZ branch, safely, tests run,
   code compiles, everything works, etc.
1. If there are no more tasks to take, you have the following options:
   1. Improve the already accepted solution, and create another PR.
   1. Search for a bug, and if such is found, report an issue and create a PR that fixes it.
   1. Propose a new functionality (as an issue with a user story and criteria of acceptance).
   1. Do nothing - the task considered not done.
1. If you're done with your task, you can take another one (and get additional points).
1. Feel free to add missing tests and Javadocs for the existing functionality.

## Grading

1. Every successfully merged PR is worth 10 points.
1. Every new, passing and meaningful test is worth 1 point.
1. Every added and meaningful Javadoc is worth 1 point.
