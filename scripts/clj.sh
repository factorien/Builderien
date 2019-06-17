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

export CLJ_PATH=$PROJECT_ROOT/bin/clj/
export CLJ_BIN=$CLJ_PATH/bin/clj


function install_clj {
    # Installs CLJ cli tool local to the project
    mkdir -p $PROJECT_ROOT/tmp/
    rm -f "$PROJECT_ROOT/tmp/install-clj.sh"
    curl https://download.clojure.org/install/linux-install-1.10.1.447.sh -o "$PROJECT_ROOT/tmp/install-clj.sh"
    chmod +x $PROJECT_ROOT/tmp/install-clj.sh
    $PROJECT_ROOT/tmp/install-clj.sh -p $CLJ_PATH
    echo "DONE"
}


function run_clj {
    echo "Exechuting '$CLJ_BIN $@'"
    eval "$CLJ_BIN $@"
}
