(ns builderien.events
  (:require [re-frame.core :as re-frame]
            [ajax.core     :as ajax]
            [builderien.db     :as db]
            [builderien.config :as config]))


;; This even is responsible to set the initial value of the database defined in `db.cljs`
(re-frame/reg-event-db
 ::db/initialize-db
 (fn  [_ _]
   db/default-db))

;; This event sets the active page (page key)
;; the page key should exists in the `page->component` map
;; in `views.cljs`
(re-frame/reg-event-db
 ::db/set-active-page
 (fn [db [_ active-page]]
   (assoc db ::db/active-page active-page)))

(re-frame/reg-event-fx
  :fetch-topics
  (fn [{:keys [db]} _]
    {:db   (assoc db :loading true)
     :http-xhrio {:method          :get
                  :uri             (config/latest-posts-url)
                  :params          {:api_key "4b971bf2488e0901402faa2dc238e1a42c0eac4aa6e28108114823273929eeea"
                                    :api_username "builderien"}
                  :timeout         8000
                  :response-format (ajax/json-response-format {:keywords? true})
                  :on-success      [:populate-topics]
                  :on-failure      [:failed-to-fetch]}}))

(defn  process-topic
  "Find the user details for the owner in the users list and
  embbed it in the topic map."
  [acc topic]
  (let [topics   (or (:topics acc) [])
        owner    (first (:posters topic))
        user     (first (filter #(= (:id %) (:user_id owner)) (:users acc)))]
    (assoc acc
           :topics
           (conj topics (assoc topic :user user)))))

(re-frame/reg-event-db
 :populate-topics
 (fn [db [_ result]]
   (let [topics (js->clj result)
         posts  (reduce process-topic
                        topics
                        (:topics (:topic_list topics)))]
     (assoc db
            :topics (:topics posts)
            :loading false))))

(re-frame/reg-event-db
 :failed-to-fetch
 (fn [db [_ errors]]
   (assoc db :errors errors)))
