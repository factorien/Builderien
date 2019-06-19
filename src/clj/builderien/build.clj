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

(ns builderien.build
  (:require
   [clojure.java.io :as io]
   [builderien.pages :as p]
   [builderien.build.constants :as c]))


(defn in-cljs-directory
  "Return the absolute `path` in the cljs classpath."
  [path]
  (-> (io/file (System/getProperty "user.dir") "src" "cljs" path)
     (.getPath)))

(defn ns-form
  "Return a string ns form with all the namespaces that hav been used
  in the current website. (Via layouts and clojurescript only)."
  []
  (let [namespaces (distinct (map (fn [x] (format "   [%s]" x))
                                  (p/extract-namespaces)))]
    (clojure.string/replace c/binding-ns-form-template
                            #"NS"
                            (clojure.string/join "\n" namespaces))))



(defn create-bindings
  "Create the `builderien/src/cljs/webman/views/bindings.cljs` that requires
  all the namespaces being used in the configuration of the current
  website."
  []
  (spit (in-cljs-directory "builderien/views/bindings.cljs")
        (str c/copy-right-header
             "\n"
             c/binding-warning-header
             (ns-form))))

(comment
  (create-bindings))
