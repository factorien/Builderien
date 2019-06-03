(ns webman.views
  (:require
   [re-frame.core        :as re-frame]
   [webman.views.home    :as home]
   [webman.views.footer  :as footer]
   [webman.i18n          :refer [t]]
   [webman.views.nav     :as nav])
  (:require-macros [webman.website :refer [get-config]]))


;; (defn- panels [panel-name]
;;   (for-current-website panel-name
;;                        :about [:h1 "about"]
;;                        [:div]))


(defn- pages [panel]
  (case panel
    :webman.pages/index (get-config :home-fn)
    [:div [:h1 "404"]]))

(defn render-page
  [panel-name]
  (println "333333333")
  (println panel-name)
  [pages panel-name])


(defn hero
  []
  [:section {:className "hero is-medium is-light"}
   [:div {:className :hero-body}
    [:div {:className "container"}
     [:div {:className "columns"}
      [:div {:className "column is-half"}
       [:div {:className "has-text-right"}
        [:h2 {:className :title}
         (get-config :welcome-title)]
        [:p {:className :subtitle1}
         (get-config :welcome-message)]]]

      [:div {:className "column is-half has-text-centered"}
       [:img {:className :logo :src (get-config :logo)}]]]]]])


(defn main-panel []
  (let [active-page (re-frame/subscribe [:active-page])]
    (fn []
      [:div
       [nav/bar]
       [hero]
       [:div {:className "container"}
        [render-page @active-page]]
       [footer/bar]])))
