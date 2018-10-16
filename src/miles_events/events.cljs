(ns miles-events.events
  (:require
    [re-frame.core :as rf]))

(defonce default-db
  {:active-view :home})

(rf/reg-event-db
  ::initialize-db
  (fn [_ _]
    default-db))

(rf/reg-event-fx
  ::login-success
  ;; TODO(khellang): Use cofx to get redirect path and redirect.
  (fn [{:keys [db]} [_ user]]
    {:db (assoc db :user user)}))