(ns miles-events.events
  (:require
    [re-frame.core :as rf]))

(defonce default-db
  {:active-view :home})

(rf/reg-event-db
  ::initialize-db
  interceptors
  (fn [_ _]
    default-db))