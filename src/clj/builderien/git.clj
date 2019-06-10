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

(ns builderien.git
  (:require [builderien.git.core :as core]))


(def release-ref "refs/heads/master")


(defmacro last-commit
  "Returns the SHA1 hash of the HEAD commit of Builderien's master branch."
  []
  (let [repo (core/builderien-repository)]
    `(str ~(.getName
            (.getObjectId
             (.exactRef repo release-ref))))))
