;; Builderien - Static website generator
;; Copyright (C) 2018-2019  Builderien's Developers

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

(ns builderien.views.layouts.stack
  (:require
   [reagent.core :as r]))


;; TODO: Find a better name, lay down the semantics first.
(defn stack-view
  "Stack view component displays its children in vertical rows."
  ^{:added "1.0.0"}
  [props & children]
  [:div
   (map-indexed (fn [index child]
                  ^{:key index}
                  [:div {:className :columns} child])
                children)])
