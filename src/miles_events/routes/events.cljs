(ns miles-events.routes.events
  (:require
    [re-frame.core :as rf]))

(rf/reg-event-db
  ::set-active-view
  (fn [db [_ view]]
    (assoc db :active-view view)))