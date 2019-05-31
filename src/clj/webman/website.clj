(ns webman.website
  (:require [clojure.java.io :as io]
            [clojure.edn :as edn])
  (:import
   [org.eclipse.jgit.api Git]
   [java.io File]))





(defn repository
  [path]
  (.getRepository (Git/open (File. (str path "/.git")))))


(defmacro last-commit
  []
  (let [repo (repository (System/getProperty "user.dir"))]
    `(str ~(.getName (.getObjectId (.exactRef repo "refs/heads/master"))))))

(defn asset
  "Reader function for `#asset` edn tag which append a random querystring
  to the assets url."
  [url]
  (format "%s?%s" url (int (* 100000000 (rand)))))

;; EDN Readers
(def readers
  {'asset asset})


(defn website-name
  "Return the website name from WEBMAN_WEBSITE env variable"
  ([] (website-name "iranclojure.ir"))
  ([default]
   (or (System/getenv "WEBMAN_WEBSITE") default)))

(defmacro get-config
  "Return the value of the given config `key` by reading the proper
  configuration file of the current website (set in WEBMAN_WEBSITE)
  or if the key is missing it would return the value from `default.edn`
  "
  [key]
  (let [config-name (format "websites/%s.edn" (website-name))
        resource    (io/resource config-name)
        defaults    (edn/read-string {:readers readers}
                                     (slurp (io/resource "websites/default.edn")))
        config      (merge defaults (edn/read-string {:readers readers}
                                                     (slurp resource)))]
    (get config key)))

(defmacro require-index
  []
  (format "[webman.views.indexes.%s :as %s]" (website-name) "home"))

(defmacro current-index
  []
  (let [component (format "[webman.views.indexes.%s/index]"
                          (website-name "default-website"))]
    `~component))

(defmacro for-current-website
  [panel & body]
  (let [component (format "[webman.views.indexes.%s/index]" (website-name "default-website"))]
    `(case ~panel
       :home-panel ~component
       ~@body)))
