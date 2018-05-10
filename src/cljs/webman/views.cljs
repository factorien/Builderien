(ns webman.views
  (:require [re-frame.core :as re-frame]
            [webman.views.home :as home]))

(defn- panels [panel-name]
  (case panel-name
    :home-panel [home/index]
    [:div]))

(defn show-panel [panel-name]
  [panels panel-name])

(defn main-panel []
  (let [active-panel (re-frame/subscribe [:active-panel])]
    (fn []
      [show-panel @active-panel])))
