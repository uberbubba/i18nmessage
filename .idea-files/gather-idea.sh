#!/bin/env bash
#
# gather-idea
#
# This script copies the active .idea and project iml files from the
# project back into the persistent .idea-files directory.
#
# This is intended to be manually run after maintenance on the project.
# Users will acquire the changes by pulling the persistent files.
#

# Copy the IntelliJ .idea and .iml files from the project
copy_idea()
{
    local dst=".idea-files/idea-shadow" src=".idea"

    # Copy the directory and almost everything in it.
    echo "=== Copying $src -> $dst"
    mkdir -p $dst
    tar cf - -C $src . --exclude='workspace.xml' --exclude='libraries' | tar xvf - -C $dst

    # Check for project templates
    dst=".idea-files/project-shadow"
    src=.
	echo "=== Copying $src -> $dst"
	mkdir -p $dst
	tar cf - --transform='s~iml$~iml.template~' $(find ${src} -name '*.iml') | tar xvf - -C $dst
}

# Find the top level of the project
# Assumes that this script resides in the project somewhere

find_idea()
{
    local here=$(cd ${0%/*}; pwd)
    while [ x"$here" != x"" ]; do
        [ -d ${here}/.idea ] && break
        here=${here%/*}
    done
    echo $here
}

# Prepare for the copy by going to the top level of the project
idea=$(find_idea)
[ x"$idea" == x"" ] && { echo "error: could not find .idea directory" >&2; exit 1; }
cd $idea

# Copy the files to the tracked location
copy_idea

# end
