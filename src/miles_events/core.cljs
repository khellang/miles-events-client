(ns ^:figwheel-hooks miles-events.core
  (:require
    [miles-events.routes.core :as routes]
    [miles-events.logging :as logging]
    [miles-events.events :as events]
    [taoensso.timbre :as timbre]
    [miles-events.app :as app]
    [re-frame.core :as rf]
    [reagent.core :as r]
    [goog.dom :as dom]))

(def debug? ^boolean goog.DEBUG)

(def log-level (if debug? :debug :warn))

(defn dev-setup []
  (when debug?
    (timbre/info "Running in development mode...")))

(defn render []
  (r/render [app/render]
    (dom/getElement "app")))

(defn ^:after-load re-render []
  (rf/clear-subscription-cache!)
  (render))

(defonce start-up
  (do
    (logging/initialize {:level log-level})
    (rf/dispatch-sync [::events/initialize-db])
    (dev-setup)
    (routes/init)
    (render)))
