(ns webman.routes
  (:require-macros
   [webman.config :refer [map-pages page-routes]])
  (:require
   [reitit.core :as r]
   [reitit.frontend :as rtf]
   [reitit.frontend.history :as rtfh]
   [reitit.frontend.easy :as rtfe]
   [reitit.coercion.schema :as rsc]
   [re-frame.core :as re-frame]))

(def routes
  (rtf/router
   (reduce (fn [routes page]
             (conj routes [(:url page) (:view page)]))
           ["/"]
           (page-routes))
   {:data {:coercion rsc/coercion}}))

(defn app-routes
  []
  (rtfe/start! routes
               (fn [m]
                 (re-frame/dispatch [:set-active-page (:name (:data m))]))
               {:use-fragment false}))
