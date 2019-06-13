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

(ns builderien.subs
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :as re-frame]))

;; TODO: Move this subscription to a new namespace
;;       dedicated for routing subscriptions
(re-frame/reg-sub
 :builderien.db/active-page
 (fn [db _]
   (:builderien.db/active-page db)))


(re-frame/reg-sub
 :topics
 (fn [db _]
   (:topics db)))


(re-frame/reg-sub
 :loading?
 (fn [db _]
   (:loading db)))
