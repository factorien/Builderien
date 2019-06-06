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

(ns webman.pages.core
  (:require
   [webman.config.core :as c]))


(defn get-pages
  "Return all the pages and their configurations."
  []
  (c/get-config :webman/pages))


(defn component-call-stack
  "Create the quoted expr of the given `component-name` by
  walking down the `args` and creating their exprs recursively.

  Each component must have an `args` vector. Each new component
  described by a hashmap of `{:options {} :args []}` in the `args`
  vector."
  [component-name opts args]
  {:pre [(vector? args)]}
  `(apply
    ~(symbol component-name)
    ~opts
    ~@(map (fn [arg]
             (cond
               (map? arg) (let [component (first arg)]
                            (component-call-stack (first component)
                                                  (or (:options (second component)) {})
                                                  (or (:args (second component)) [])))
               (keyword? arg) (component-call-stack arg {} [])
               :else arg))
           args)))


(comment
  ;; Must raise an error
  (component-call-stack :nameb.space/component  {}  nil)

  (component-call-stack :nameb.space/component  {}  [{:srg.sad/asd {:option {:a 1} :args [32 :asd]}}]))


(defn page-layout
  [page-details]
  (let [layout (:webman/layout page-details)]
    (map #(component-call-stack (first %)
                                (:options (second %))
                                (:args (second %)))
         layout)))

(comment
  (page-layout (first (c/get-config :webman/pages))))

(defn page-component
  [[page-name page-details]]
  (let [layout (page-layout page-details)]
    `(defn ~(symbol (str (name page-name) "-page-component"))
       []
       ~@layout)))

(comment
  (page-component (first (c/get-config :webman/pages))))


(defn reduce-page-component
  "A reducer function which reduces `page-components` with a page component
  of the given `page`."
  [page-components page]
  (conj page-components (page-component page)))

(comment
  (reduce reduce-page-component
          []
          (c/get-config :webman/pages)))


(defn collect-component-namespace
  "Retun a collection of namespaces that have been used for the component
  and its children."
  [component-name args]
  {:pre [(vector? args)]}
  (reduce (fn [namespaces arg]
            (cond
              (map? arg) (let [component (first arg)]
                           (concat namespaces
                                 (component-call-stack-namespace
                                  (first component)
                                  (or (:args (second component)) []))))

              (keyword? arg) (conj namespaces (namespace (symbol arg)))
              :else namespaces))
          [(namespace (symbol component-name))]
          args))

(defn extract-component-namespaces
  "Extract all the namespaces that have been used in the given page."
  [namespaces [page-name page-details]]
  (let [layout (:webman/layout page-details)]
    (reduce (fn [acc x]
               (concat acc
                       (component-call-stack-namespace
                        (first x)
                        (or (:args (second x)) []))))
            namespaces
            layout)))
