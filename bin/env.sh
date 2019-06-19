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


PROJECT_ROOT=`pwd`

[[ ":$PATH:" != *":$PROJECT_ROOT/bin/clj/bin/:"* ]] && export PATH="$PROJECT_ROOT/bin/clj/bin/:${PATH}"

alias lumo=$PROJECT_ROOT/node_modules/lumo-cljs/download/dist/lumo
alias shadow-cljs=$PROJECT_ROOT/node_modules/shadow-cljs/cli/runner.js

function is-active() {
    echo "Yes"
}
