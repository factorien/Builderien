(ns builderien.routes
  (:require-macros
   [builderien.pages :refer [page-routes]])
  (:require
   [reitit.core :as r]
   [reitit.frontend :as rtf]
   [reitit.frontend.history :as rtfh]
   [reitit.frontend.easy :as rtfe]
   [reitit.coercion.schema :as rsc]
   [re-frame.core :as re-frame]))

;; Sets the page routes based on the website edn file
;; for more information checkout `page-routes` macro and
;; `default.edn` configuration
(def routes
  (rtf/router
   (reduce (fn [routes page]
             (conj routes [(:url page) (:view page)]))
           ["/"]
           (page-routes))
   ;; TODO: Fix the warning of coercion
   {:data {:coercion rsc/coercion}}))

(defn app-routes
  []
  (rtfe/start! routes
               (fn [m]
                 (re-frame/dispatch [:set-active-page (:name (:data m))]))
               {:use-fragment false}))
