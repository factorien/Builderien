;; Webman - Static website generator
;; Copyright (C) 2018-2019  Webman's Developers

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

(ns webman.pages
  (:require
   [webman.pages.core :as p]))


(defmacro page-routes
  "Return the details of all the pages defined in the website configuration
  in the following format:

  {:page PAGE_NAME
   :title PAGE_TITLE
   :url PAGE_URL
   :view EVENT_KEY}

  * EVENT_KEY is the even key which is going to be used in cljs to set
  the current page in `db` using re-frame events
  * PAGE_URL should be a `reitit` compatible url string. For exampl
  "
  []
  `[~@(map (fn [[name page]]
             {:page name
              :title (:title page)
              :url (:url page)
              :view (:view page)})
           (p/get-pages))])


(defmacro required-namespaces-for-pages
  []
  `[some.asd :as ss])

(defmacro define-pages
  "Defines all the root components for all the pages in the website
  catalog based on the catelog description. Checkout `default.edn`
  for more information."
  []
  `(do ~@(reduce p/reduce-page-component [] (p/get-pages))))

(comment
  (println (macroexpand-1 '(define-pages)))

  (output is:)
  (do (clojure.core/defn index-page-component [] (clojure.core/apply webman.views.layouts.stack/stack-view {} (clojure.core/apply webman.views.dummy/title {}))) (clojure.core/defn about-page-component [])))
