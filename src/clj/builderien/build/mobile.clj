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

(ns builderien.build.mobile
  (:require
   [cheshire.core :as json]
   [builderien.config :as config]
   [builderien.build.constants :as c]))


(defn create-manifest
  ([]
   (create-manifest "" c/default-mobile-manifest))

  ([manifest-data]
   ;; Yeah i know joining using str is not great, but
   ;; it's good enough for this use case.
   (create-manifest manifest-data
                    (str (config/target-directory) "/manifest.json")))

  ([manifest-data path]
   (spit path
         (json/generate-string (merge c/default-mobile-manifest
                                      manifest-data)))))
