(ns miles-events.routes.events
  (:require
    [re-frame.core :as rf]))

(rf/reg-event-fx
  ::set-active-view
  (fn [{:keys [db]} [_ view]]
    (let [set-view (assoc db :active-view view)]
      (case view
        ;; TODO(khellang): Fetch data required for each view etc.
        :home {:db set-view}
        {:db set-view}))))