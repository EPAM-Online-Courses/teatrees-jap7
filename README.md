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
1. Clone this repository.
   ```
   $ git clone ssh://git@github.com/EPAM-Online-Courses/teatrees-jap7.git
   ```
1. Create a feature branch with a name following a convention:
   ```
   issueID-short-but-meaningful-description-written-in-kebab-case
   ```
   For example:
   ```
   $ git switch -c 999-implement-realtime-interaction
   ```
   (Don't put `#` in the name of the branch.)
1. Implement the feature **on that feature branch**.
   ```
   ## edit/create the necessary files
   $ git add file1 file2 ...
   $ git commit
   ```
1. Push the changes to a remote feature branch **with the same name**.
   ```
   $ git push origin issueID-your-feature-branch-name
   ```
1. Create a Pull Request, so that it can be merged to `master`. (Do not merge it yourself!)

**IMPORTANT!**

YOU WILL NOW BE WORKING ON THE **SHARED** REPO. NOT A FORK, BUT THIS REPO.

## Rules

1. Your implementation shall not break any of the existing functionalities.
1. Each single commit shall contain:
   
   * a consistent, working set of changes,
   * a set of tests that proves the changes actually work,
   * a documentation attached to public API,
   * a commit message reasonably explaining the changes.

1. You shall touch only those classes that are necessary for the purpose
   of resolving the issue. Too wide, especially unrelated changes will cause
   the PR to be **rejected**.
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
1. If you're done with your task, you can take another one or help other team members with their tasks.
1. Feel free to add missing tests and Javadocs for the existing functionality.
