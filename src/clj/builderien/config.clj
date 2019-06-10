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

(ns builderien.config
  (:require [builderien.config.core :as c]))


(defmacro get-config
  "Return the value of the given config `key` by reading the proper
  configuration file of the current website (set in WEBMAN_WEBSITE)
  or if the key is missing it would return the value from `default.edn`
  "
  [key]
  (c/get-config key))
