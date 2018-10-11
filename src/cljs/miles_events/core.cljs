(ns ^:figwheel-hooks miles-events.core
  (:require
    [miles-events.routes.core :as routes]
    [miles-events.events :as events]
    [miles-events.app :as app]
    [re-frame.core :as rf]
    [reagent.core :as r]
    [goog.dom :as dom]))

(def debug? ^boolean goog.DEBUG)

(defn dev-setup []
  (when debug?
    (enable-console-print!)
    (println "Running in development mode...")))

(defn mount []
  (r/render [app/render]
    (dom/getElement "app")))

(defn ^:after-load re-render []
  (rf/clear-subscription-cache!)
  (mount))

(defonce start-up
  (do
    (rf/dispatch-sync [::events/initialize-db])
    (dev-setup)
    (routes/init)
    (mount)
    true))
