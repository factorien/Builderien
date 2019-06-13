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

(ns builderien.routes
  (:require-macros
   [builderien.pages :refer [page-routes]])
  (:require
   ;; TODO: Do we need all these reitit namespaces ?
   [reitit.core :as r]
   [reitit.frontend :as rtf]
   [reitit.frontend.history :as rtfh]
   [reitit.frontend.easy :as rtfe]
   [reitit.coercion.schema :as rsc]
   [re-frame.core :as re-frame]))


(defn route-reducer
  "Reduce the given `route-data` to the given `routes`."
  [routes route-data]
  (conj routes [(:url route-data) (:view route-data)]))


;; Sets the page routes based on the website edn file
;; for more information checkout `page-routes` macro and
;; `default.edn` configuration
(def routes
  (rtf/router
   (reduce route-reducer ["/"] (page-routes))
   ;; TODO: Fix the warning of coercion
   {:data {:coercion rsc/coercion}}))


(defn app-routes
  "Setup the reitit routes and the event handler for the routes."
  []
  (rtfe/start! routes
               (fn [route]
                 ;; TODO: Fix this function to be able to use `reitit` routes
                 ;;       at their fullest.
                 (re-frame/dispatch [:builderien.db/set-active-page (:name (:data route))]))
               {:use-fragment false}))
