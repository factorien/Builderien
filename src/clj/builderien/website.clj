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

(ns builderien.website
  (:require [clojure.java.io :as io]
            [clojure.edn :as edn]
            [builderien.config.readers :as r]))

(defn website-name
  "Return the website name from BUIDERIEN_WEBSITE env variable"
  ([] (website-name "iranclojure.ir"))
  ([default]
   (or (System/getenv "BUIDERIEN_WEBSITE") default)))


(defmacro get-config
  "Return the value of the given config `key` by reading the proper
  configuration file of the current website (set in BUIDERIEN_WEBSITE)
  or if the key is missing it would return the value from `default.edn`
  "
  [key]
  (let [config-name (format "websites/%s.edn" (website-name))
        resource    (io/resource config-name)
        defaults    (edn/read-string {:readers r/edn-readers}
                                     (slurp (io/resource "websites/default.edn")))
        config      (merge defaults (edn/read-string {:readers r/edn-readers}
                                                     (slurp resource)))]
    (get config key)))

(defmacro require-index
  []
  (format "[builderien.views.indexes.%s :as %s]" (website-name) "home"))

(defmacro current-index
  []
  (let [component (format "[builderien.views.indexes.%s/index]"
                          (website-name "default-website"))]
    `~component))
