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

(ns builderien.core
  (:require
   [day8.re-frame.http-fx]
   [reagent.core :as reagent]
   [devtools.core :as devtools]
   [re-frame.core :as re-frame]
   [re-frisk.core :refer [enable-re-frisk!]]
   [builderien.events]
   [builderien.subs]
   [builderien.routes :as routes]
   [builderien.views :as views]
   [builderien.config :as config]))


(defn dev-setup
  "Setup the dev environment only if debug flag is on (`config/debug?`
  return true)."
  []
  (when config/debug?
    (enable-console-print!)
    (enable-re-frisk!)
    (devtools/install!)
    (println "dev mode")))


(defn mount-root
  "Mount the root component for the SPA application. Basically it finds the
  root element in the page and mount the `views/main` function in it."
  []
  (re-frame/clear-subscription-cache!)
  (reagent/render [views/main]
                  (.getElementById js/document "app")))


(defn ^:export init
  "This function is the entry point of builderien frontend application."
  []
  (dev-setup)
  (routes/app-routes)

  ;; Dispatch :initialize-db event to populate the db with default
  ;; values. Checkout `builderien.events` for more info.
  (re-frame/dispatch-sync [:initialize-db])
  (mount-root))


(defn ^:export reload!
  "Re-mount the root component after hot reloading the changes in dev
  environment. After we change any file shadow-cljs will compile the
  changes on reload them in the browser REPL. We need this hook to
  remount the root component in order to see the changes."
  []
  (println "Remounting the root component")
  (mount-root))


(comment
  ;; Note: You can evaluate each of the following expressions
  ;; in any file or in the repl.

  ;; Start the CLJS repl from CLJ REPL.
  (shadow.user.shadow/repl :app)

  ;; Compile the `:app` in development mode and watch for changes
  (shadow.user.shadow/watch :app)

  ;; Or the verbose option
  (shadow/watch :app {:verbose true})

  ;; If you just want to compile the frontend app.
  (shadow/compile :app)

  ;; If you want to release the frontend app. We might not have a release
  ;; for sometime
  (shadow/release :app)

  ;; Exit from CLJS repl.
  :cljs/quit)
