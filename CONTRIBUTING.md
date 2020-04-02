# Monopoly Contributing Guide
Suggestions for improvements are always welcome and can be discussed on our communication channels.

## General
In practice, the English language is always used in development, since it is possible that developers who do not speak German will join the project.
So please write all issues, code comments, pull request, commit messages etc. in English. Also, always use meaningful variable names and method names, which should also be in English. 

## Reporting Issues
* Please fill in all fields in the issue templates if possible, so that everyone can understand it as well as possible. But never delete the headings and comments.
* **Search** for similar bug reports, they _may_ have been answered.
* Do not change the labels (bug, enhancement), the assignees, etc. during the creation. After discussing the issues together, the next steps will be decided.
* See if the error is **reproduceable** with the latest version.
* **Never** comment "+1" or "me too!" on issues without leaving additional information, use the :+1: button in the top right instead.

## Pull Requests
* Always work on a new branch. Making changes on your fork's `dev` or `master` branch can cause problems.
* Every pull request should be submitted to the `dev` branch by default. Exceptions will be announced.
* Use a descriptive title no more than 64 characters long. This will be used as the commit message when your PR is merged. 
* Please reference in the PR description the issue # that the PR resolves, something like `Fixes #1234` or `Resolves #6458` (See [closing issues using keywords](https://help.github.com/articles/closing-issues-using-keywords/))

## Standard procedure

If you want to implement/fix a new issue, this is the basic procedure. Contact the group if there are any uncertainties.
We only work on issues that are included in the current sprint backlog. We decide at the beginning of each sprint what will go into the backlog. The sprint backlog can always be found in the `ToDo` column of the Kanban Board in the [Projects Tab](https://github.com/SS20-SE2-Monopoly-Team/monopoly/projects).

1. First check out the develop branch so that you can create a feature branch from it with the command `git checkout develop`.

2. Then fetch the latest changes from the repository using the `git pull` command.

3. Now open your issue on GitHub and assign it to yourself if it is not already assigned (at the top of the right sidebar).
Then move the issue from the Kanban board's `ToDo` column into the `Doing` column.

4. After that you can create a new branch on which you will implement your feature. Use the command `git branch feature/#1234-short-description` for this. Replace `#1234` with the ID of the issue you are implementing.
Add a short description in the place of `-short-description`, which uses dashes instead of spaces and is written in lower case.
You can have multiple feature branches at the same time, which you can work on independently if necessary.
Switch between them with `git checkout <branch>`.

5. Now check out the new feature branch with `git checkout <branch>`

6. Develop your feature in Android Studio now. Make sure you don't end up with a huge single commit at the end.
Create a separate commit for each subtask instead. Make sure that the program is runable when checking out each commit later.
Files added to the staging area with `git add` can be committed with `git commit -m "commit message"`.
You can test if the commit message is good by adding it to the end of this sentence: **If applied, this commit will...**
    
    Here are some example commit messages ([this](https://chris.beams.io/posts/git-commit/) is also a good post about this topic):
    - "Update README.md"
    - "Add method for computing the dice value"
    - "Change board colors"
    - "Create class for saving user data"

7. With `git push` you can backup your commits to the repository. Your colleagues can then access your branch. Pushing won't merge anything yet, so don't worry about it. There may be a error in the terminal if the remote branch doesn't exist yet. Just run the suggested command then.

8. Once you've implemented your feature and you think everything is ready and fits, you can create a pull request in the browser on GitHub.
This is a signal to everyone else that you have finished and we can review it and suggest changes if necessary. If everything is fine, your feature will then be merged into the develop branch, making it part of the app.
You do not have to worry about the Kanban Board, because everything is moved on automatically. 😇

**You are now so far done with your feature. Congratulations!**

