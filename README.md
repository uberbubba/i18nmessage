# i18nmessage
This project is not really about i18nmessage testing, though it has some code for that. Feel free to look at that Java and Groovy code if you wish.  

The i18nmessage project is an example of how to isolate the raft of changes that IntelliJ IDEA makes to its project (.iml) files, and configuration (.idea/*.xml) files.  In a multi-developer environment, these changes can drive you nuts with all the changes that git flags.  Most of these changes are unimportant, but sometimes they are helpful, such as when IDEA neglects to mark a groovy directory as a test source, or if you have added a module to Maven and IDEA needs to know about it. 

The strategy is to:
1. Do not track the standard IDEA files (.idea, *.iml) in git
2. Keep tracked copies of "approved" .idea project files in the project tree.  Update these as needed.
3. Use githooks on post-checkout and post-merge phases to copy the tracked copies into the untracked locations.

## Prerequisites
This method requires a git and IntelliJ installation.  You must have administrative rights on your machine.

## Installation
Installation consists of convincing git to use the 'post-checkout' and 'post-merge' hooks, setting up a '.idea-files' directory to hold your tracked IDEA project files, and telling git to ignore the untracked IDEA files.

### Install git hooks
Copy the 'git-templates' directory into your user directory (or $HOME, if you have set that up).  It should go into the same directory where your .gitconfig lives.  Following UNIX convention, call it '.git-templates'.

    git-templates    ->   ~/.git-templates

At the command line, set the location of the git templates directory:

    git config --global init.templateDir '~/.git-templates'

Be sure that the 'post-checkout' and 'post-merge' hooks have executable permissions!
 
### Set up tracked IDEA files
If your project is not already enabled for this feature you will need to create a directory structure to hold the tracked files.  If you are already tracking the IDEA files, and you wish to keep them to track history, then you will need to 'git mv' the files rather than just copying them.  Otherwise, the 'gather-idea.sh' script will create the directories and copy in the IDEA files.

If you cannot run a bash script, you can manually set up your tracked IDEA directory.  Follow this structure at the top level directory of your project:

    .idea-files/idea-shadow
    .idea-files/project-shadow
    
Copy (or git mv) all the files and directories from .idea to .idea-files/idea-shadow except for 'workspace.xml' and the 'libraries' directory.  

Copy (or git mv) all the .iml files from the project into the .idea-files/project-shadow, maintaining their relative locations in the project, and renaming them '*.iml.template'.  Example,

    yourproj.iml                ->  .idea-files/project-shadow/yourproj.iml.template
    yourmodule/yourmodule.iml   ->  .idea-files/project-shadow/yourmodule/yourmodule.iml.template
    
Obviously, it's easier with the script, and if you don't care about git history.  git-bash should be able to run this script, as does Cygwin.

### Ignore IDEA files
Merge the .gitignore file from this project into yours.

## Execution
The hooks are executed after checkouts after having updated the worktree, and after merges, including pulls.  See https://git-scm.com/docs/githooks.  After successful execution, there will be a '.post-checkout.log' file in the project for audit purposes.  

## Maintenance
When you wish to have an IDEA file change propagate to all developers, run the 'gather-idea.sh' script to copy your project IDEA files into the tracked directory.  Commit and push.  When the other developers pull the change, the new files will be copied into their projects.
