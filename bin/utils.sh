#! /bin/bash
# Builderien - Static website generator
# Copyright (C) 2018-2019  Factorien Team
#
# This program is free software; you can redistribute it and/or
# modify it under the terms of the GNU General Public License
# as published by the Free Software Foundation; either version 2
# of the License, or any later version.
#
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
#
# You should have received a copy of the GNU General Public License
# along with this program.
set -e

function check_for_currect_root {
    if [ ! -f `pwd`/deps.edn ]; then
        "Wrong directory! Switch to the root of project and try agiain."
        exit 1
    fi
}


function depend_on_os {
    if [ `uname` = "Linux" ]; then
        eval $1
    else
        eval $2
    fi
}
