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

(ns builderien.config.core
  (:require [clojure.java.io :as io]
            [clojure.edn :as edn]
            [builderien.config.readers :as r]))


(defn website-name
  "Return the website name from BUIDERIEN_WEBSITE env variable"
  ([] (website-name "iranclojure.ir"))
  ([default]
   (or (System/getenv "BUIDERIEN_WEBSITE") default)))


(defn website-config
  "Return the configuration of the current website (WEBSITE_WEBMAN)
   by merging the defualt configuration and the website configuration."
  []
  (let [config-name (format "websites/%s.edn" (website-name))
        resource    (io/resource config-name)
        defaults    (edn/read-string {:readers r/edn-readers}
                                     (slurp (io/resource "websites/default.edn")))]
    (merge defaults (edn/read-string {:readers r/edn-readers}
                                     (slurp resource)))))

(defn get-config
  "Return the value of the given config `key` by reading the proper
  configuration file of the current website (set in BUIDERIEN_WEBSITE)
  or if the key is missing it would return the value from `default.edn`
  "
  [key]
  (get (website-config) key))

(defn get-page-config
  "Return the page configuration for the given `page-id`."
  [page-id]
  (get (get-config :builderien/pages) page-id))


(defn map-pages
  "Map over all the pages and invoke the given function `f` on each of them."
  [f]
  (map f (get-config :builderien/pages)))
