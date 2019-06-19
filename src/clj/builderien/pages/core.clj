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

(ns builderien.pages.core
  (:require
   [builderien.config.core :as c]))


(def default-component-suffix "-page-component")


(defn get-pages
  "Return all the pages and their configurations."
  []
  (c/get-config :builderien/pages))


(defn component-call-stack
  "Create the quoted expr of the given `component-name` by
  walking down the `args` and creating their exprs recursively.

  Each component must have an `args` vector. Each new component
  described by a hashmap of `{:options {} :args []}` in the `args`
  vector."
  [component-name opts args]
  {:pre [(vector? args)]}
  `[~(symbol component-name)
    ~opts
    ~@(map (fn [arg]
             (cond
               (map? arg)
               (component-call-stack (:fn arg)
                                     (or (:options arg) {})
                                     (or (:args arg) []))

               (keyword? arg) (component-call-stack arg {} [])
               :else arg))
           args)])

(comment
  ;; Must raise an error
  (component-call-stack :nameb.space/component  {}  nil)
  (component-call-stack :nameb.space/component  {}  [{:fn :srg.sad/asd :option {:a 1} :args [32 :asd]}]))


(defn page-layout
  [page-details]
  (let [layout (:builderien/layout page-details)]
    (map #(component-call-stack (:fn %)
                                (:options %)
                                (:args %))
         layout)))

(comment
  (page-layout (second (first (c/get-config :builderien/pages)))))


(defn page-component
  "Return a quoted form of defining a function for the given `page-name`
  and `page-details`.

  The function name would be like: `PAGE-NAME-page-component`."
  [[page-name page-details]]
  (let [layout (page-layout page-details)]
    `(defn ~(symbol (str (name page-name) default-component-suffix))
       []
       ~@layout)))

(comment
  (page-component (first (c/get-config :builderien/pages))))


(defn page-name->page-fn
  "Returns a mapping from page names to their component functions."
  []
  (reduce #(assoc %1 (first %2) (symbol (str (name (first %2)) default-component-suffix)))
          {}
          (get-pages)))


(defn reduce-page-component
  "A reducer function which reduces `page-components` with a page component
  of the given `page`."
  [page-components page]
  (conj page-components (page-component page)))

(comment
  (reduce reduce-page-component
          []
          (c/get-config :builderien/pages)))


(defn collect-component-namespace
  "Retun a collection of namespaces that have been used for the component
  and its children."
  [component-name args]
  {:pre [(vector? args)]}
  (reduce (fn [namespaces arg]
            (cond
              (map? arg)
              (concat namespaces
                      (collect-component-namespace
                       (:fn arg)
                       (or (:args arg) [])))

              (keyword? arg)
              (conj namespaces (namespace (symbol arg)))

              :else namespaces))

          [(namespace (symbol component-name))]
          args))

(defn extract-component-namespaces
  "Extract all the namespaces that have been used in the given page."
  [namespaces [page-name page-details]]
  (let [layout (:builderien/layout page-details)]
    (reduce (fn [acc x]
               (concat acc
                       (collect-component-namespace
                        (:fn x)
                        (or (:args x) []))))
            namespaces
            layout)))
