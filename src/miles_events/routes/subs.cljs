(ns miles-events.routes.subs
  (:require
    [re-frame.core :as rf]))

(rf/reg-sub
  ::active-view
  (fn [db _]
    (:active-view db)))
