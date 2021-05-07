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

(ns builderien.utils)


(defn release?
  "Retun true if `builderien.release` system property is set to true."
  []
  (System/getProperty "builderien.release"))


(defmacro todo
  "A macro that print out the given `msg` as a TODO in a nice way
  both in Clojure and Clojurescript."
  [msg]
  (when-not (release?)
    `(println (str "[" ~*ns* ":" ~(:line (meta &form))
                   "] TODO: " ~msg))))

(comment
  (macroexpand-1 '(todo "example")))