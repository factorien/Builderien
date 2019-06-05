(ns webman.subs
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :as re-frame]))

(re-frame/reg-sub
 :active-page
 (fn [db _]
   (:active-page db)))

(re-frame/reg-sub
 :topics
 (fn [db _]
   (:topics db)))

(re-frame/reg-sub
 :loading?
 (fn [db _]
   (:loading db)))
