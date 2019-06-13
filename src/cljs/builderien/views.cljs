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

(ns builderien.views
  (:require
   [re-frame.core     :as re-frame]
   [builderien.i18n   :refer [t]]
   [builderien.config :as config]
   ;; We generate this namespace to only include those UI namespaces
   ;; which is used in the configuration file (build configuration edn file)
   [builderien.views.bindings])

  (:require-macros
   [builderien.website :refer [get-config]]
   [builderien.pages :refer [define-pages]]))


;; This is a macro defined in `builderien.pages` ns in clojure side
;; that parses the build configuration and creates a set root components
;; for each page in the build configuration in the following format:
;; `PAGENAME-page-component`. In each root component it defines a tree
;; of UI components based on the build configuration.
(define-pages page->component)


;; TODO: Fix this component to be configurable with a decent default
(defn not-found
  []
  [:div
   [:h1 "404"]
   [:a {:href "/"} "Home"]])


(defn main
  []
  (let [active-page (re-frame/subscribe [:builderien.db/active-page])
        active-page-component (get page->component @active-page)]
    (when config/debug?
      (println "Active Page: " @active-page))

    (if (= @active-page :builderien.db/empty)
      ;; Initial empty state
      [:span]
      (if-not (nil? active-page-component)
        [active-page-component]
        ;; TODO: Make 404 page configurable via edn configuration
        [not-found]))))
