(ns miles-events.routes.core
  (:require
    [miles-events.routes.events :as events]
    [re-frame.core :as rf]
    [pushy.core :as pushy]
    [bidi.bidi :as bidi]))

(defonce routes ["/" {""      ::home
                      "about" ::about}])

(defn- parse-url [url]
  (bidi/match-route routes url))

(defn- dispatch-route [matched-route]
  (let [view (keyword (name (:handler matched-route)))]
    (rf/dispatch [::events/set-active-view view])))

(defn init []
  (pushy/start! (pushy/pushy dispatch-route parse-url)))

(def url-for (partial bidi/path-for routes))