;; Builderien - Static website generator
;; Copyright (C) 2018-2019  Factorien Team

;; This program is free software; you can redistribute it and/or
;; modify it under the terms of the GNU General Public License
;; as published by the Free Software Foundation; either version 2
;; of the License, or any later version.

;; This program is distributed in the hope that it will be useful,
;; but WITHOUT ANY WARRANTY; without even the implied warranty of
;; MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
;; GNU General Public License for more details.

;; You should have received a copy of the GNU General Public License
;; along with this program.

(ns builderien.core)

(comment
  ;; Note: You can evaluate each of the following expressions
  ;; in any file or in the repl.

  ;; Start the CLJS repl from CLJ REPL.
  (shadow.cljs.devtools.api/repl :app)

  ;; Compile the `:app` in development mode and watch for changes
  (shadow.cljs.devtools.api/watch :app)

  ;; Or the verbose option
  (shadow.cljs.devtools.api/watch :app {:verbose true})

  ;; If you just want to compile the frontend app.
  (shadow.cljs.devtools.api/compile :app)

  ;; If you want to release the frontend app. We might not have a release
  ;; for sometime
  (shadow.cljs.devtools.api/release :app)

  ;; Exit from CLJS repl.
  :cljs/quit)
